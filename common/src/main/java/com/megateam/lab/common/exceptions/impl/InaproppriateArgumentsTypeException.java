package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.CommandArgumentsException;

public class InaproppriateArgumentsTypeException extends CommandArgumentsException {
  public InaproppriateArgumentsTypeException(String message) {
    super(message);
  }
}
