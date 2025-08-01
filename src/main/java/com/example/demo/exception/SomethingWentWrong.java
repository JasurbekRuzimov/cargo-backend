package com.example.demo.exception;

public class SomethingWentWrong extends RuntimeException {
  public SomethingWentWrong(String message) {
    super(message);
  }
}
