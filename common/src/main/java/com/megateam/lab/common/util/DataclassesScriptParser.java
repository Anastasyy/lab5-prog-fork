package com.megateam.lab.common.util;

import com.megateam.lab.common.data.*;
import com.megateam.lab.common.exceptions.impl.DataclassParsingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataclassesScriptParser {

  private static Coordinates parseCoordinates(List<String> coordinatesFields)
      throws DataclassParsingException {
    try {
      float x = Float.parseFloat(coordinatesFields.get(0));
      Integer y = Integer.parseInt(coordinatesFields.get(1));

      return new Coordinates(x, y);
    } catch (NumberFormatException e) {
      throw new DataclassParsingException("Unable to parse Coordinates from script.");
    }
  }

  private static Venue parseVenue(List<String> venueFields) throws DataclassParsingException {
    try {
      String name = venueFields.get(0);
      Integer capacity = Integer.parseInt(venueFields.get(1));

      //			VenueType type = (venueFields.get(2) == null || "".equals(venueFields.get(2)))
      //					? null : VenueType.valueOf(venueFields.get(2));

      VenueType type;
      try {
        type = VenueType.valueOf(venueFields.get(2));
      } catch (IllegalArgumentException e) {
        type = null;
      }

      return new Venue(name, capacity, type);
    } catch (IllegalArgumentException e) {
      throw new DataclassParsingException("Unable to parse Venue from script.");
    }
  }

  public static Ticket parseTicket(Scanner scanner) throws DataclassParsingException {
    try {
      String name = scanner.nextLine();
      List<String> coordinatesFields = List.of(scanner.nextLine(), scanner.nextLine());
      float price = Float.parseFloat(scanner.nextLine());
      String comment = scanner.nextLine();
      boolean refundable = Boolean.parseBoolean(scanner.nextLine());
      TicketType ticketType = TicketType.valueOf(scanner.nextLine());
      Venue venue = parseVenue(List.of(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));

      return new Ticket(
          name,
          parseCoordinates(coordinatesFields),
          LocalDateTime.now(ZoneId.systemDefault()),
          price,
          comment,
          refundable,
          ticketType,
          venue);
    } catch (IllegalArgumentException e) {
      throw new DataclassParsingException("Unable to parse Ticket from script.");
    } catch (NoSuchElementException e) {
      throw new DataclassParsingException("Incorrect script format. Please, refactor it.");
    }
  }
}
