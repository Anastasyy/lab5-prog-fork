package com.megateam.lab.common.data;

import static org.junit.jupiter.api.Assertions.*;

import com.megateam.lab.common.data.util.LocalDateTimeAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;

public class DataclassesMarshableTest {
  private Marshaller marshaller;
  private File file;

  private void createFile(String filename) throws IOException {

    assertDoesNotThrow(
        () -> {
          Files.createDirectories(Path.of("tmp"));

          file = new File("tmp/" + filename + ".xml");

          if (!file.exists()) file.createNewFile();
        });
  }

  private void prepare(Class<?> baseClass) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(baseClass);
    marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.setAdapter(new LocalDateTimeAdapter());
  }

  @Test
  public void testCoordinatesMarshable() {
    Coordinates coordinates = new Coordinates(10F, 1);
    assertDoesNotThrow(
        () -> {
          createFile("test1");
          prepare(Coordinates.class);
          marshaller.marshal(coordinates, file);
        });
  }

  @Test
  public void testVenueMarshable() {
    Venue venue = new Venue(10, "test1", 10, VenueType.LOFT);
    assertDoesNotThrow(
        () -> {
          createFile("test2");
          prepare(Venue.class);
          marshaller.marshal(venue, file);
        });
  }

  @Test
  public void testTicketMarshable() {
    Ticket ticket =
        new Ticket(
            1,
            "ticketName",
            new Coordinates(10F, 1),
            LocalDateTime.now(ZoneId.systemDefault()),
            1000F,
            "comment1",
            true,
            TicketType.VIP,
            new Venue(11, "test1", 10, VenueType.BAR));
    assertDoesNotThrow(
        () -> {
          createFile("test3");
          prepare(Ticket.class);
          marshaller.marshal(ticket, file);
        });
  }

  @Test
  public void testTicketContainerMarshable() {
    Ticket ticket1 =
        new Ticket(
            2,
            "ticketName2",
            new Coordinates(10F, 1),
            LocalDateTime.now(ZoneId.systemDefault()),
            1000F,
            "comment2",
            false,
            TicketType.BUDGETARY,
            new Venue(17, "test2", 10, VenueType.LOFT));

    Ticket ticket2 =
        new Ticket(
            1,
            "ticketName1",
            new Coordinates(10F, 1),
            LocalDateTime.now(ZoneId.systemDefault()),
            1000F,
            "comment1",
            true,
            TicketType.VIP,
            new Venue(11, "test1", 10, VenueType.BAR));

    TicketContainer container =
        new TicketContainer(LocalDateTime.now(ZoneId.systemDefault()), List.of(ticket1, ticket2));

    assertDoesNotThrow(
        () -> {
          createFile("test4");
          prepare(TicketContainer.class);
          marshaller.marshal(container, file);
        });
  }
}
