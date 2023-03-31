package com.megateam.lab.common.util;

import com.megateam.lab.common.data.TicketType;
import com.megateam.lab.common.data.VenueType;
import com.megateam.lab.common.exceptions.impl.InaproppriateArgumentsTypeException;

/** Parses types from string */
public class TypesParser {
  public static Integer parseInteger(String line) throws InaproppriateArgumentsTypeException {
    try {
      return Integer.parseInt(line);
    } catch (NumberFormatException e) {
      throw new InaproppriateArgumentsTypeException(
          "Inaproppriate argument type: should be integer.");
    }
  }

  public static Long parseLong(String line) throws InaproppriateArgumentsTypeException {
    try {
      return Long.parseLong(line);
    } catch (NumberFormatException e) {
      throw new InaproppriateArgumentsTypeException("Inaproppriate argument type: should be long.");
    }
  }

  public static Boolean parseBoolean(String line) throws InaproppriateArgumentsTypeException {

    if ("true".equalsIgnoreCase(line)) {
      return true;
    } else if ("false".equalsIgnoreCase(line)) {
      return false;
    }

    throw new InaproppriateArgumentsTypeException(
        "Inaproppriate argument type: should be boolean.");
  }

  public static TicketType parseTicketType(String line) throws InaproppriateArgumentsTypeException {

    for (TicketType type : TicketType.values()) {
      if (type.toString().equalsIgnoreCase(line)) {
        return TicketType.valueOf(line);
      }
    }

    throw new InaproppriateArgumentsTypeException(
        "Inaproppriate argument type: should be TicketType or specified variable not found.");
  }

  public static VenueType parseVenueType(String line) throws InaproppriateArgumentsTypeException {
    for (VenueType type : VenueType.values()) {
      if (type.toString().equalsIgnoreCase(line)) {
        return VenueType.valueOf(line);
      }
    }

    throw new InaproppriateArgumentsTypeException(
        "Inaproppriate argument type: should be VenueType or specified variable not found.");
  }

  public static Float parseFloat(String line) throws InaproppriateArgumentsTypeException {
    try {
      return Float.parseFloat(line);
    } catch (NumberFormatException e) {
      throw new InaproppriateArgumentsTypeException(
          "Inaproppriate argument type: should be float.");
    }
  }

  public static Double parseDouble(String line) throws InaproppriateArgumentsTypeException {
    try {
      return Double.parseDouble(line);
    } catch (NumberFormatException e) {
      throw new InaproppriateArgumentsTypeException(
          "Inaproppriate argument type: should be float.");
    }
  }
}
