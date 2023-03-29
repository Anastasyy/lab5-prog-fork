package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ValidationException;

public class OutOfAllowedBoundException extends ValidationException {
  public OutOfAllowedBoundException(String message) {
    super(message);
  }
}
