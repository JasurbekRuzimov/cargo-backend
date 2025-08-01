package com.example.demo.exception;

public class CouldNotRead extends RuntimeException {
  public CouldNotRead(String message) {
    super(message);
  }
}
