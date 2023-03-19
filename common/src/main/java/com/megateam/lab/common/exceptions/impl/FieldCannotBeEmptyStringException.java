package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ValidationException;

public class FieldCannotBeEmptyStringException extends ValidationException {
  public FieldCannotBeEmptyStringException(String message) {
    super(message);
  }
}
