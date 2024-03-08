package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.response.CustomerResponseDTO;
import com.ms001.bank.dto.request.CustomerCreateRequestDTO;
import com.ms001.bank.dto.request.CustomerUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.Customer;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.CustomerRepository;
import com.ms001.bank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;
    private BankRepository bankRepository;

    @Override
    public List<CustomerResponseDTO> getAllUsers() {
        List<Customer> allCustomer = customerRepository.findAll();
        List<CustomerResponseDTO> collect = allCustomer.stream()
                .map(user -> modelMapper.map(user, CustomerResponseDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CustomerResponseDTO getUserById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customer, CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO updateUser(CustomerUpdateRequestDTO customerUpdateRequestDTO, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(customerUpdateRequestDTO.getFirstName());
        customer.setLastName(customerUpdateRequestDTO.getLastName());
        customer.setFatherName(customerUpdateRequestDTO.getFatherName());
        customer.setPhoneNumber(customerUpdateRequestDTO.getPhoneNumber());
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = modelMapper.map(savedCustomer, CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO createUser(CustomerCreateRequestDTO customerCreateRequestDTO) {
        String bankName = customerCreateRequestDTO.getBankName();
        Bank bank = bankRepository.findById(bankName).orElseThrow();
        Customer customer = new Customer(
                customerCreateRequestDTO.getFirstName(),
                customerCreateRequestDTO.getLastName(),
                customerCreateRequestDTO.getFatherName(),
                customerCreateRequestDTO.getPhoneNumber(),
                bank);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = modelMapper.map(savedCustomer, CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.deleteById(id);
    }
}
