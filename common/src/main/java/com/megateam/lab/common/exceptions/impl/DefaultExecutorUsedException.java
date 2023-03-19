package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ExecutionException;

public class DefaultExecutorUsedException extends ExecutionException {
  public DefaultExecutorUsedException(String message) {
    super(message);
  }
}
