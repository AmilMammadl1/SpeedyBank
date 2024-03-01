package com.ms001.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Branch> branches = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<ATM> atms = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Deparment> departments = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

}