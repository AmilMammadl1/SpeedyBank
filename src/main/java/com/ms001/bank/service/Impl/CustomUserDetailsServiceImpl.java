package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.Role;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.entity.Employee;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.repository.EmployeeRepository;
import com.ms001.bank.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findByFinCode(username);
        if (customer.isPresent()) {
            Customer foundCustomer = customer.get();
            Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(Role.USER.name()));
            return new User(foundCustomer.getFinCode(), foundCustomer.getPassword(), authorities);
        } else {
            Optional<Employee> employee = employeeRepository.findByEmail(username);
            if (employee.isPresent()) {
                Employee foundEmployee = employee.get();
                Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(Role.DEVELOPER.name()));
                return new User(foundEmployee.getEmail(), foundEmployee.getPassword(), authorities);
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }
    }
}
