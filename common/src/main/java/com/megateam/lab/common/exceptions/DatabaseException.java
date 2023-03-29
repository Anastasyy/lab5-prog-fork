package com.megateam.lab.common.exceptions;

public abstract class DatabaseException extends Exception {
  public DatabaseException(String message) {
    super(message);
  }
}
