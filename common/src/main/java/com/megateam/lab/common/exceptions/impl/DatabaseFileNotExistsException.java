package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseFileNotExistsException extends DatabaseException {
  public DatabaseFileNotExistsException(String message) {
    super(message);
  }
}
