package com.ms001.bank.exception;

public class BranchNotFoundException extends RuntimeException{
    public BranchNotFoundException(String message){
        super(message);
    }
}
