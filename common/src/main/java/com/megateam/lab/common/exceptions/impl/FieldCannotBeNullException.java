package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ValidationException;

public class FieldCannotBeNullException extends ValidationException {
  public FieldCannotBeNullException(String message) {
    super(message);
  }
}
