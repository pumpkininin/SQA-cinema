package com.edu.hanu.cinematicketsystem.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException() {
    super("User not found");
  }
}
