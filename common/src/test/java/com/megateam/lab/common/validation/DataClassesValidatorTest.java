package com.megateam.lab.common.validation;

import static org.junit.jupiter.api.Assertions.*;

import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.exceptions.ValidationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataClassesValidatorTest {
  private DataClassesValidator validator;

  @BeforeEach
  public void init() {
    validator = new DataClassesValidator();
  }

  @Test
  public void validateCoordinatesXCoordTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateCoordinatesXCoord(200F);
          validator.validateCoordinatesXCoord(20F);
          validator.validateCoordinatesXCoord(-390F);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateCoordinatesXCoord(-11231F);
          validator.validateCoordinatesXCoord(182381231893712213298371231273123112319F);
        });
  }

  @Test
  public void validateCoordinatesYCoordTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateCoordinatesYCoord(200);
          validator.validateCoordinatesYCoord(20);
          validator.validateCoordinatesYCoord(-390);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateCoordinatesYCoord(null);
        });
  }

  @Test
  public void validateVenueIdTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateVenueId(1);
          validator.validateVenueId(2);
          validator.validateVenueId(3);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateVenueId(-1);
          validator.validateVenueId(0);
          validator.validateVenueId(-2);
        });
  }

  @Test
  public void validateVenueNameTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateVenueName("test");
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateVenueName(null);
          validator.validateVenueName("");
        });
  }

  @Test
  public void validateVenueCapacityTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateVenueCapacity(1);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateVenueCapacity(null);
          validator.validateVenueCapacity(0);
          validator.validateVenueCapacity(-1);
        });
  }

  @Test
  public void validateTicketIdTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketId(1);
          validator.validateTicketId(2);
          validator.validateTicketId(2);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketId(null);
          validator.validateTicketId(0);
          validator.validateTicketId(-1);
        });
  }

  @Test
  public void validateTicketNameTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketName("test");
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketName(null);
          validator.validateTicketName("");
        });
  }

  @Test
  public void validateTicketCreationDateTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketCreationDate(LocalDateTime.now(ZoneId.systemDefault()));
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketCreationDate(null);
        });
  }

  @Test
  public void validateTicketPriceTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketPrice(1);
          validator.validateTicketPrice(2);
          validator.validateTicketPrice(3);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketPrice(0);
          validator.validateTicketPrice(-1);
        });
  }

  @Test
  public void validateTicketCommentTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketComment("test");
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketComment(null);
          //			TODO: implement 404 characters length string testing
        });
  }

  @Test
  public void validateTicketRefundableTest() {
    assertDoesNotThrow(
        () -> {
          validator.validateTicketRefundable(true);
          validator.validateTicketRefundable(false);
        });

    assertThrows(
        ValidationException.class,
        () -> {
          validator.validateTicketRefundable(null);
        });
  }
}
