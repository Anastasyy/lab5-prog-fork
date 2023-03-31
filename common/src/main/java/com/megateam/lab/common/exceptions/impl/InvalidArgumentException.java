package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.CommandArgumentsException;

public class InvalidArgumentException extends CommandArgumentsException {
  public InvalidArgumentException(String message) {
    super(message);
  }
}
