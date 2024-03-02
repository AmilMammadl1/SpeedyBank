package com.ms001.bank.entity;

import com.ms001.bank.constant.SupportedServicesType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "atm_supported_services", joinColumns = @JoinColumn(name = "atm_id"))
    @Column(name = "supported_service")
    private List<SupportedServicesType> supportedServices;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public ATM(String location, Bank bank) {
        this.location = location;
        this.bank = bank;
        // Set default supported services as needed
        this.supportedServices = List.of(
                SupportedServicesType.CASH_WITHDRAWAL,
                SupportedServicesType.CASH_DEPOSIT,
                SupportedServicesType.BALANCE_INQUIRY,
                SupportedServicesType.ACCOUNT_TRANSFER,
                SupportedServicesType.MINI_STATEMENT,
                SupportedServicesType.PIN_CHANGE,
                SupportedServicesType.BILL_PAYMENTS,
                SupportedServicesType.MOBILE_RECHARGE,
                SupportedServicesType.CHEQUE_DEPOSIT,
                SupportedServicesType.CARDLESS_TRANSACTIONS,
                SupportedServicesType.CURRENCY_EXCHANGE,
                SupportedServicesType.RECEIPT_PRINTING,
                SupportedServicesType.LANGUAGE_SELECTION,
                SupportedServicesType.ACCESSIBILITY_FEATURES,
                SupportedServicesType.EMERGENCY_CASH
        );
    }
}
