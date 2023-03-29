package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.DatabaseException;

public class DatabaseFilePermissionException extends DatabaseException {
  public DatabaseFilePermissionException(String message) {
    super(message);
  }
}
