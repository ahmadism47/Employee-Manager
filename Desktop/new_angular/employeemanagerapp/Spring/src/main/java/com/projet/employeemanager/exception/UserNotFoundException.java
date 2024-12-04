package com.projet.employeemanager.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {

        super(s);
    }
}
