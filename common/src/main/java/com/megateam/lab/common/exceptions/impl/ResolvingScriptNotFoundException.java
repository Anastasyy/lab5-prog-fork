package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ResolverException;

public class ResolvingScriptNotFoundException extends ResolverException {
  public ResolvingScriptNotFoundException(String message) {
    super(message);
  }
}
