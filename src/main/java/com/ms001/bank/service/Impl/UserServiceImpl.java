package com.ms001.bank.service.Impl;

import com.ms001.bank.dto.UserDTO;
import com.ms001.bank.dto.request.UserCreateRequestDTO;
import com.ms001.bank.dto.request.UserUpdateRequestDTO;
import com.ms001.bank.entity.Bank;
import com.ms001.bank.entity.User;
import com.ms001.bank.repository.BankRepository;
import com.ms001.bank.repository.UserRepository;
import com.ms001.bank.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private BankRepository bankRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        List<UserDTO> collect = allUser.stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(userUpdateRequestDTO.getFirstName());
        user.setLastName(userUpdateRequestDTO.getLastName());
        user.setFatherName(userUpdateRequestDTO.getFatherName());
        user.setPhoneNumber(userUpdateRequestDTO.getPhoneNumber());
        User savedUser = userRepository.save(user);
        UserDTO userDTO = modelMapper.map(savedUser, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
        String bankName = userCreateRequestDTO.getBankName();
        Bank bank = bankRepository.findById(bankName).orElseThrow();
        User user = new User(
                userCreateRequestDTO.getFirstName(),
                userCreateRequestDTO.getLastName(),
                userCreateRequestDTO.getFatherName(),
                userCreateRequestDTO.getPhoneNumber(),
                bank);
        User savedUser = userRepository.save(user);
        UserDTO userDTO = modelMapper.map(savedUser, UserDTO.class);
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
}
