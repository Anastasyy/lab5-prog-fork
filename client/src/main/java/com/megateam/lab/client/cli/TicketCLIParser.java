package com.megateam.lab.client.cli;

import com.megateam.lab.common.data.Coordinates;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.data.TicketType;
import com.megateam.lab.common.data.Venue;
import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.exceptions.UIException;
import com.megateam.lab.common.exceptions.impl.UserDataInputInterruptedException;
import com.megateam.lab.common.util.Printer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Scanner;
import lombok.NonNull;

/**
 * User can enter ticket from Console
 */
public class TicketCLIParser {
  /**
   * Contains an instance of Scanner
   */
  @NonNull private Scanner scanner;
  /**
   * Contains an instance of ValidatorClassesData
   */
  @NonNull private DataClassesValidator validator;
  /**
   * Contains an instance of Printer
   */
  @NonNull private Printer printer;
  /**
   * Contains an instance of ParserCLICoordinates
   */

  private CoordinatesCLIParser coordinatesCLIParser;
  /**
   * Contains an instance of ParserCLIVenue
   */
  private VenueCLIParser venueCLIParser;

  /**
   * TicketCLIParser constructor
   * @param  scanner instance
   * @param validator instance
   * @param printer instance
   */
  public TicketCLIParser(Scanner scanner, DataClassesValidator validator, Printer printer) {
    this.scanner = scanner;
    this.validator = validator;
    this.printer = printer;

    this.coordinatesCLIParser = new CoordinatesCLIParser(scanner, validator, printer);
    this.venueCLIParser = new VenueCLIParser(scanner, validator, printer);
  }

  /**
   * Offers an ability to interrupt data input
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private void proposeContinue() throws UIException {
    printer.print("Do you want to continue? [y/Y - for yes, other - for no]: ");
    String userInput = scanner.nextLine().trim();
    if (!"Y".equalsIgnoreCase(userInput))
      throw new UserDataInputInterruptedException("Data input successfully interrupted");
  }

  /**
   * Method can parse ticket name
   * @return ticket name
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private String parseTicketName() throws UIException {
    printer.print("Enter ticket name (non-empty): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println(
          "You're not able to insert a null value for this variable. Try another value.");
      proposeContinue();
      return parseTicketName();
    }

    try {
      validator.validateTicketName(userInput);
      return userInput;
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseTicketName();
    }
  }

  /**
   * Method can parse Coordinates
   * @return coordinates
   * @throws UserDataInputInterruptedException  if got not y/Y from user
   */
  private Coordinates parseTicketCoordinates() throws UIException {
    return this.coordinatesCLIParser.parseCoordinates();
  }

  /**
   * Method can parse ticket price
   * @return ticket price
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private float parseTicketPrice() throws UIException {
    printer.print("Enter ticket price (float & greater than 0): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println(
          "You're not able to insert a null value for float variable. Try another value.");
      proposeContinue();
      return parseTicketPrice();
    }

    try {
      float x = Float.parseFloat(userInput);
      validator.validateTicketPrice(x);
      return x;
    } catch (NumberFormatException e) {
      printer.println("Ticket coordinate should be a float.");
      proposeContinue();
      return parseTicketPrice();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseTicketPrice();
    }
  }

  /**
   * Method can parse ticket comment
   * @return ticket comment
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private String parseTicketComment() throws UIException {
    printer.print("Enter ticket comment (non-empty): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println(
          "You're not able to insert a null value for this variable. Try another value.");
      proposeContinue();
      return parseTicketComment();
    }

    try {
      validator.validateTicketComment(userInput);
      return userInput;
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseTicketComment();
    }
  }

  /**
   * Method can parse ticket refundable status
   * @return ticket refundable
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private Boolean parseTicketRefundable() throws UIException {
    printer.print("Enter ticket refundable (non-empty): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    try {
      Boolean refundable = Boolean.parseBoolean(userInput);
      validator.validateTicketRefundable(refundable);
      return refundable;
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseTicketRefundable();
    }
  }

  /**
   * Method can parse ticket type
   * @return ticket type
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private TicketType parseTicketType() throws UIException {
    printer.print(
        String.format(
            "Enter ticket type (can be null) %s: ", Arrays.toString(TicketType.values())));

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      return null;
    }

    try {
      return TicketType.valueOf(userInput);
    } catch (IllegalArgumentException e) {
      printer.println("You should select ticket type from the listed ones.");
      proposeContinue();
      return parseTicketType();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseTicketType();
    }
  }

  /**
   * Method can parse venue
   * @return venue
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private Venue parseTicketVenue() throws UIException {
    return this.venueCLIParser.parseVenue();
  }

  /**
   * Crate an instance of Ticket and return it
   * @return ticket
   * @throws UserDataInputInterruptedException if input stream ended or process interrupted by user
   */
  public Ticket parseTicket() throws UIException {
    printer.println("#### ENTERING TICKET ####");
    String name = parseTicketName();
    Coordinates coordinates = parseTicketCoordinates();
    LocalDateTime creationDate = LocalDateTime.now(ZoneId.systemDefault());
    float price = parseTicketPrice();
    String comment = parseTicketComment();
    Boolean ref = parseTicketRefundable();
    TicketType type = parseTicketType();
    Venue venue = parseTicketVenue();
    printer.println("#### ENTERING TICKET ENDED ####");

    return new Ticket(name, coordinates, creationDate, price, comment, ref, type, venue);
  }
}
