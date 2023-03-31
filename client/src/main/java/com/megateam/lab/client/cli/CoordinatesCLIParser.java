package com.megateam.lab.client.cli;

import com.megateam.lab.common.data.Coordinates;
import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.exceptions.UIException;
import com.megateam.lab.common.exceptions.impl.UserDataInputInterruptedException;
import com.megateam.lab.common.util.Printer;
import java.util.Scanner;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * User can enter coordinates from Console
 */
@RequiredArgsConstructor
public class CoordinatesCLIParser {
  /**
   * Contains an instance of Scanner
   */
  @NonNull private Scanner scanner;
  /**
   * Contains an instance of ValidatorDataClasses
   */
  @NonNull private DataClassesValidator validator;
  /**
   * Contains an instance of Printer
   */
  @NonNull private Printer printer;

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
   * Method is parsing X coordinate
   * @return float variable of X coordinate
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private float parseXCoord() throws UIException {

    printer.print("Enter X coordinate (float & greater than -390): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println(
          "You're not able to insert a null value for float variable. Try another value.");
      proposeContinue();
      return parseXCoord();
    }

    try {
      float x = Float.parseFloat(userInput);
      validator.validateCoordinatesXCoord(x);
      return x;
    } catch (NumberFormatException e) {
      printer.println("X coordinate should be a float.");
      proposeContinue();
      return parseXCoord();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseXCoord();
    }
  }

  /**
   * Method is parsing Y coordinate
   * @return float variable of Y coordinate
   * @throws UserDataInputInterruptedException if got not y/Y from user
   */
  private Integer parseYCoord() throws UIException {
    printer.print("Enter Y coordinate (Integer): ");

    if (!scanner.hasNextLine())
      throw new UserDataInputInterruptedException("Data input successfully interrupted");

    String userInput = scanner.nextLine().trim();

    if ("".equals(userInput)) {
      printer.println("This variable cannot be null. Try another value.");
      proposeContinue();
      return parseYCoord();
    }

    try {
      Integer y = Integer.parseInt(userInput);
      validator.validateCoordinatesYCoord(y);
      return y;
    } catch (NumberFormatException e) {
      printer.println("X coordinate should be an Integer.");
      proposeContinue();
      return parseYCoord();
    } catch (Exception e) {
      printer.println(e.getMessage());
      proposeContinue();
      return parseYCoord();
    }
  }

  /**
   * Creates an instance of coordinates and returns it
   * @return instance of coordinates
   * @throws UserDataInputInterruptedException if input stream ended or process interrupted by user
   */
  public Coordinates parseCoordinates() throws UIException {
    printer.println("#### ENTERING COORDINATES ####");
    float x = parseXCoord();
    Integer y = parseYCoord();
    printer.println("#### ENTERING COORDINATES ENDED ####");

    return new Coordinates(x, y);
  }
}
