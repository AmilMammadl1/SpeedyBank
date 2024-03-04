package com.ms001.bank.service;

import com.ms001.bank.dto.EmployeeDTO;
import com.ms001.bank.dto.UserDTO;
import com.ms001.bank.dto.request.EmployeeRequestDTO;
import com.ms001.bank.dto.request.UserCreateRequestDTO;
import com.ms001.bank.dto.request.UserUpdateRequestDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO, Long id);
    UserDTO createUser(UserCreateRequestDTO userCreateRequestDTO);
    void deleteUser(Long id);
}
