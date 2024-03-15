package com.ms001.bank.config;

import com.ms001.bank.service.CustomerService;
import com.ms001.bank.service.EmployeeService;
import com.ms001.bank.service.jwt.JWTService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain //containe other filters that need to be applied on the request
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authoriation"); //get Authoriation header from request
        final String jwt; // store the our jwt in String
        final String username; // store the customer's fincode in String

        //check authHeader is empty or not
        if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);//This method is responsible for invoking the next filter in the chain or, if there are no more filters, invoking the servlet that handles the incoming request.
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        //SecurityContextHolder.getContext().getAuthentication() == null, it means that there is no authenticated user currently in the security context
        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =  null;
            try {
                userDetails = customerService.userDetailsService().loadUserByUsername(username);
            } catch (Exception exception) {
                // If the user is not found in the customer service, try employee service
                try {
                    userDetails = employeeService.userDetailsService().loadUserByUsername(username);
                } catch (Exception employeeNotFoundException) {
                    // Handle the case when the user is not found in both customer and employee services
                    // You might want to log this event or handle it based on your application's requirements
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                    return;
                }
            }
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); //The SecurityContext is used to store security-related information for the current thread of execution. This information typically includes details about the current principal (user), their authentication status, and potentially other security-related attributes.
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( //UsernamePasswordAuthenticationToken is created upon successful login.
                        userDetails, null, userDetails.getAuthorities()
                );
                //WebAuthenticationDetailsSource is responsible for creating instances of WebAuthenticationDetails, which contain details about the web request being authenticated. It typically includes information like the remote address, the session ID, and the request URL.
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//attach custom details to the authentication token.
                securityContext.setAuthentication(token);//you're effectively establishing the identity of the current user within the security context.
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
