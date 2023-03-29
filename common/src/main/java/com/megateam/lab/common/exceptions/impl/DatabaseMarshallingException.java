package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseMarshallingException extends DatabaseException {
  public DatabaseMarshallingException(String message) {
    super(message);
  }
}
