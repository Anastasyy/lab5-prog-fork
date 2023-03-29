package com.megateam.lab.client.cli;

import com.megateam.lab.common.data.Venue;
import com.megateam.lab.common.data.VenueType;
import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.exceptions.UIException;
import com.megateam.lab.common.exceptions.impl.UserDataInputInterruptedException;
import com.megateam.lab.common.util.Printer;
import java.util.Arrays;
import java.util.Scanner;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VenueCLIParser {
  @NonNull private Scanner scanner;
  @NonNull private DataClassesValidator validator;
  @NonNull private Printer printer;

  private void proposeContinue() throws UIException {
    printer.print("Do you want to continue? [y/Y - for yes, other - for no]: ");
    String userInput = scanner.nextLine().trim();
    if (!"Y".equalsIgnoreCase(userInput))
      throw new UserDataInputInterruptedException("Data input successfully interrupted");
  }

  private String parseVenueName() throws UIException {
    printer.print("Enter venue name (non-empty): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println(
          "You're not able to insert a null value for this variable. Try another value.");
      proposeContinue();
      return parseVenueName();
    }

    try {
      validator.validateVenueName(userInput);
      return userInput;
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseVenueName();
    }
  }

  private Integer parseVenueCapacity() throws UIException {
    printer.print("Enter venue capacity (Integer & greater than 0): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println("This variable cannot be null. Try another value.");
      proposeContinue();
      return parseVenueCapacity();
    }

    try {
      Integer capacity = Integer.parseInt(userInput);
      validator.validateVenueCapacity(capacity);
      return capacity;
    } catch (NumberFormatException e) {
      printer.println("Venue capacity should be an Integer.");
      proposeContinue();
      return parseVenueCapacity();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseVenueCapacity();
    }
  }

  private VenueType parseVenueType() throws UIException {
    printer.print(
        String.format("Enter venue type (can be null) %s: ", Arrays.toString(VenueType.values())));

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      return null;
    }

    try {
      return VenueType.valueOf(userInput);
    } catch (IllegalArgumentException e) {
      printer.println("You should select type from the listed ones.");
      proposeContinue();
      return parseVenueType();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseVenueType();
    }
  }

  public Venue parseVenue() throws UIException {
    printer.println("#### ENTERING VENUE ####");
    String name = parseVenueName();
    Integer capacity = parseVenueCapacity();
    VenueType type = parseVenueType();
    printer.println("#### ENTERING VENUE ENDED ####");

    return new Venue(name, capacity, type);
  }
}
