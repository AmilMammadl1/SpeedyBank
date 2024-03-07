package com.ms001.bank.exception;

public class ATMNotFoundException extends RuntimeException{
    public ATMNotFoundException(String message){
        super(message);
    }
}
