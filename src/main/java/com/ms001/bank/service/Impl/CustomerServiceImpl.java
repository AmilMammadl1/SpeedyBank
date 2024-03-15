package com.ms001.bank.service.Impl;

import com.ms001.bank.constant.Role;
import com.ms001.bank.dto.request.CustomerSignInRequest;
import com.ms001.bank.dto.request.RefreshTokenRequest;
import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.dto.response.JwtAuthenticationResponse;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.exception.CustomerNotFoundException;
import com.ms001.bank.mapper.CustomerMapper;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.CustomerService;
import com.ms001.bank.service.jwt.JWTService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private BankRepository bankRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public List<CustomerResponseDTO> getAllUsers() {
        List<Customer> allCustomer = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = allCustomer.stream()
                .map(customer -> customerMapper.mapCustomerEntityToEmployeeResponseDTO(customer))
                .collect(Collectors.toList());
        return customerResponseDTOS;
    }

    @Override
    public CustomerResponseDTO getUserById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        CustomerResponseDTO customerResponseDTO = customerMapper.mapCustomerEntityToEmployeeResponseDTO(customer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO updateUser(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customer.setFirstName(customerUpdateRequestDTO.getFirstName());
        customer.setLastName(customerUpdateRequestDTO.getLastName());
        customer.setFatherName(customerUpdateRequestDTO.getFatherName());
        customer.setPhoneNumber(customerUpdateRequestDTO.getPhoneNumber());
        Customer updatedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.mapCustomerEntityToEmployeeResponseDTO(updatedCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO signUp(CustomerCreateRequestDTO customerCreateRequestDTO) {
        String bankName = customerCreateRequestDTO.getBankName();
        Bank bank = bankRepository.findById(bankName)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + bankName));
        Customer customer = customerMapper.mapCustomerCreateRequestDTOToCustomerEntity(customerCreateRequestDTO);
        customer.setBank(bank);
        customer.setPasswrd(passwordEncoder.encode(customerCreateRequestDTO.getPassword()));
        customer.setRole(Role.USER);
        Customer createdCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.mapCustomerEntityToEmployeeResponseDTO(createdCustomer);
        return customerResponseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }

    //UserDetailsService is an interface provided by Spring Security, which is used to retrieve user-related data (such as user details and authorities) during the authentication process
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            //loadUserByUsername method returns an instance of UserDetails, which is another interface in Spring Security. UserDetails represents a user's core information (such as username, password, and authorities) and is used by Spring Security to perform authentication and authorization.
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return customerRepository.findByFinCode(username)
                        .orElseThrow(() -> new CustomerNotFoundException("Customer not found with fincode: " + username));
            }
        };
    }

    public JwtAuthenticationResponse signIn(CustomerSignInRequest customerSignInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerSignInRequest.getFinCode(),
                customerSignInRequest.getPassword()));
        var customer = customerRepository.findByFinCode(customerSignInRequest.getFinCode())
                .orElseThrow(() -> new IllegalArgumentException("Username or password is invalid"));
        var jwt = jwtService.generateToken(customer);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), customer);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String customeFincode = jwtService.extractUsername(refreshTokenRequest.getToken());
        Customer customer = customerRepository.findByFinCode(customeFincode).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), customer)) {
            var jwt = jwtService.generateToken(customer);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
