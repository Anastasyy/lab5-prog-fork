package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ResolverException;

public class CommandNotFoundException extends ResolverException {
  public CommandNotFoundException(String message) {
    super(message);
  }
}
