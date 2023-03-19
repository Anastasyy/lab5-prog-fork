package com.megateam.lab.common.exceptions;

public abstract class ValidationException extends Exception {
  public ValidationException(String message) {
    super(message);
  }
}
