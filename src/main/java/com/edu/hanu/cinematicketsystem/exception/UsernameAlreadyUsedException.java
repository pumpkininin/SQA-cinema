package com.edu.hanu.cinematicketsystem.exception;

public class UsernameAlreadyUsedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super("Username already used!");
    }
}
