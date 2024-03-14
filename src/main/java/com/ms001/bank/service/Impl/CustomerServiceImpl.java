package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.exception.BankNotFoundException;
import com.ms001.bank.exception.CustomerNotFoundException;
import com.ms001.bank.mapper.CustomerMapper;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private BankRepository bankRepository;

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
    public CustomerResponseDTO createUser(CustomerCreateRequestDTO customerCreateRequestDTO) {
        String bankName = customerCreateRequestDTO.getBankName();
        Bank bank = bankRepository.findById(bankName)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with name: " + bankName));
        Customer customer = customerMapper.mapCustomerCreateRequestDTOToCustomerEntity(customerCreateRequestDTO);
        customer.setBank(bank);
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
}
