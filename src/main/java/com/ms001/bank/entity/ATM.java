package com.ms001.bank.entity;

import com.ms001.bank.constant.SupportedServicesType;
import jakarta.persistence.*;

@Entity
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String status;
    @Enumerated(EnumType.STRING)
    private SupportedServicesType supportedServices;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
}
