package com.ms001.bank.controller;

import com.ms001.bank.dto.BranchDTO;
import com.ms001.bank.dto.UserDTO;
import com.ms001.bank.dto.request.BranchRequestDTO;
import com.ms001.bank.dto.request.UserCreateRequestDTO;
import com.ms001.bank.dto.request.UserUpdateRequestDTO;
import com.ms001.bank.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUserDtos = userService.getAllUsers();
        return new ResponseEntity<>(allUserDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        UserDTO userDTO = userService.createUser(userCreateRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDTO updateRequestDTO) {
        UserDTO userDTO = userService.updateUser(updateRequestDTO,id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserByid(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
