package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseUnmarshallingException extends DatabaseException {
  public DatabaseUnmarshallingException(String message) {
    super(message);
  }
}
