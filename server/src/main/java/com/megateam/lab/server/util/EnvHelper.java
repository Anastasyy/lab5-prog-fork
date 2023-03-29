package com.megateam.lab.server.util;

import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.VariableAccessDeniedException;
import com.megateam.lab.common.exceptions.impl.VariableNotDefinedException;

public class EnvHelper {

  public static String retrieveFileName() throws EnvException {
    try {
      String variable = System.getenv("SAVING_FILE");

      if (variable == null)
        throw new VariableNotDefinedException("Collection saving file path variable not exists.");

      if ("".equals(variable))
        throw new VariableNotDefinedException(
            "Collection saving file path variable exists, but is empty");

      return variable;
    } catch (SecurityException e) {
      throw new VariableAccessDeniedException(
          "You're not able to access collection saving file path variable.");
    }
  }
}
