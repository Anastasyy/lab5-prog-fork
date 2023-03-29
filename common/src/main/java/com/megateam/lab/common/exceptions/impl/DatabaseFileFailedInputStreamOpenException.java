package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseFileFailedInputStreamOpenException extends DatabaseException {
  public DatabaseFileFailedInputStreamOpenException(String message) {
    super(message);
  }
}
