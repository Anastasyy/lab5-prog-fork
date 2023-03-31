package com.megateam.lab.common.data;

import com.megateam.lab.common.data.util.LocalDateTimeAdapter;
import com.megateam.lab.common.data.util.TicketIdGenerator;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Getter
@EqualsAndHashCode
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket implements Comparable<Ticket> {
  @NonNull
  @XmlAttribute(name = "ticketId", required = true)
  private Integer id;

  @NonNull
  @XmlElement(name = "ticketName", required = true)
  private String name;

  @NonNull
  @XmlElement(name = "ticketCoordinates", required = true)
  private Coordinates coordinates;

  @NonNull
  @XmlElement(name = "creationDate", required = true)
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime creationDate;

  @XmlElement(name = "ticketPrice", required = true)
  private float price;

  @NonNull
  @XmlElement(name = "ticketComment", required = true)
  private String comment;

  @NonNull
  @XmlElement(name = "refundable", required = true)
  private Boolean refundable;

  @XmlElement(name = "ticketType")
  private TicketType type;

  @NonNull
  @XmlElement(name = "ticketVenue", required = true)
  private Venue venue;

  public Ticket(
      String name,
      Coordinates coordinates,
      LocalDateTime creationDate,
      float price,
      String comment,
      Boolean refundable,
      TicketType type,
      Venue venue) {
    this.id = TicketIdGenerator.generateNewId();
    this.name = name;
    this.coordinates = coordinates;
    this.creationDate = creationDate;
    this.price = price;
    this.comment = comment;
    this.refundable = refundable;
    this.type = type;
    this.venue = venue;
  }

  @Override
  public int compareTo(Ticket obj) {
    if (this.equals(obj)) return 0;
    return Integer.compare(this.id, obj.getId());
  }

  @Override
  public String toString() {
    return String.format(
        """
						Ticket: [id: %d, name: %s,
						coordinates: %s, creationDate: %s, price: %f, comment: %s, refundable: %b, type: %s,
						venue: %s]
				""",
        id,
        name,
        coordinates,
        creationDate,
        price,
        comment,
        refundable,
        (type == null) ? "is not set" : type,
        venue);
  }
}
