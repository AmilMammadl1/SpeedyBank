package com.ms001.bank.exception;

public class BankNotFoundException extends RuntimeException{
    public BankNotFoundException(String message){
        super(message);
    }
}
