package com.ms001.bank.constant;

public enum CurrencyCode {
    USD("US Dollar"),
    EUR("Euro"),
    GBP("British Pound"),
    JPY("Japanese Yen");

    private final String fullName;

    CurrencyCode(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
    }
