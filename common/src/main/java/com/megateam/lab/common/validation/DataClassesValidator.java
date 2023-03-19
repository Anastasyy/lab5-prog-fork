package com.megateam.lab.common.validation;

import com.megateam.lab.common.data.*;
import com.megateam.lab.common.exceptions.ValidationException;
import com.megateam.lab.common.exceptions.impl.FieldCannotBeEmptyStringException;
import com.megateam.lab.common.exceptions.impl.FieldCannotBeNullException;
import com.megateam.lab.common.exceptions.impl.OutOfAllowedBoundException;
import java.time.LocalDateTime;

public class DataClassesValidator {

  public void validateCoordinatesXCoord(float x) throws ValidationException {
    if (x < -390) throw new OutOfAllowedBoundException("X coordinate should be greater than -390.");
    if (x == Float.POSITIVE_INFINITY)
      throw new OutOfAllowedBoundException("X coordinate cannot be infinity.");
  }

  public void validateCoordinatesYCoord(Integer y) throws ValidationException {
    if (y == null) throw new FieldCannotBeNullException("Y coordinate cannot be null.");
  }

  public void validateCoordinates(Coordinates coordinates) throws ValidationException {
    validateCoordinatesXCoord(coordinates.getX());
    validateCoordinatesYCoord(coordinates.getY());
  }

  public void validateVenueId(long id) throws ValidationException {
    if (id <= 0) throw new OutOfAllowedBoundException("Venue id should be greater than zero.");
  }

  public void validateVenueName(String name) throws ValidationException {
    if (name == null) throw new FieldCannotBeNullException("Venue name cannot be null.");
    if ("".equals(name)) throw new FieldCannotBeEmptyStringException("Venue name cannot be empty");
  }

  public void validateVenueCapacity(Integer capacity) throws ValidationException {
    if (capacity == null) throw new FieldCannotBeNullException("Venue capacity cannot be null.");
    if (capacity <= 0)
      throw new OutOfAllowedBoundException("Venue capacity should be greater than zero.");
  }

  public void validateVenue(Venue venue) throws ValidationException {
    validateVenueId(venue.getId());
    validateVenueName(venue.getName());
    validateVenueCapacity(venue.getCapacity());
  }

  public void validateTicketId(Integer id) throws ValidationException {
    if (id == null) throw new FieldCannotBeNullException("Ticket id cannot be null.");
    if (id <= 0) throw new OutOfAllowedBoundException("Ticket id should be greater than zero.");
  }

  public void validateTicketName(String name) throws ValidationException {
    if (name == null) throw new FieldCannotBeNullException("Ticket name cannot be null.");
    if ("".equals(name)) throw new FieldCannotBeEmptyStringException("Ticket name cannot be empty");
  }

  public void validateTicketCreationDate(LocalDateTime creationDate) throws ValidationException {
    if (creationDate == null)
      throw new FieldCannotBeNullException("Ticket creation date cannot be null.");
  }

  public void validateTicketPrice(float price) throws ValidationException {
    if (price <= 0)
      throw new OutOfAllowedBoundException("Ticket price should be greater than zero.");
  }

  public void validateTicketComment(String comment) throws ValidationException {
    if (comment == null) throw new FieldCannotBeNullException("Ticket comment cannot be null.");
    if (comment.length() > 404)
      throw new OutOfAllowedBoundException(
          "Ticket comment length cannot be more than 404 chars long.");
  }

  public void validateTicketRefundable(Boolean refundable) throws ValidationException {
    if (refundable == null)
      throw new FieldCannotBeNullException("Ticket refundable status cannot be null.");
  }

  public void validateTicket(Ticket ticket) throws ValidationException {
    validateTicketId(ticket.getId());
    validateTicketName(ticket.getName());
    validateCoordinates(ticket.getCoordinates());
    validateTicketCreationDate(ticket.getCreationDate());
    validateTicketPrice(ticket.getPrice());
    validateTicketComment(ticket.getComment());
    validateTicketRefundable(ticket.getRefundable());
    validateVenue(ticket.getVenue());
  }
}
