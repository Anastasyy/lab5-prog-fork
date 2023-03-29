package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.EnvException;

public class VariableAccessDeniedException extends EnvException {
  public VariableAccessDeniedException(String message) {
    super(message);
  }
}
