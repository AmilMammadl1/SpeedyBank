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
    @Column(unique = true)  // Ensure uniqueness for the name column
    private String name;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Branch> branches = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<ATM> atms = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

}