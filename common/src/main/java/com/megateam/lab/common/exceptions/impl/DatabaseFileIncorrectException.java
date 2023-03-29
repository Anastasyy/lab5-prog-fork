package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseFileIncorrectException extends DatabaseException {
  public DatabaseFileIncorrectException(String message) {
    super(message);
  }
}
