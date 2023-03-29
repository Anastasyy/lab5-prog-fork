package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.EnvException;

public class VariableNotDefinedException extends EnvException {
  public VariableNotDefinedException(String message) {
    super(message);
  }
}
