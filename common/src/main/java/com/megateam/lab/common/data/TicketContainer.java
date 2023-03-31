package com.megateam.lab.common.data;

import com.megateam.lab.common.data.util.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@XmlRootElement(name = "tickets")
public class TicketContainer {
  @NonNull
  @XmlElement(name = "creationDate", required = true)
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime creationDate;

  @NonNull
  @XmlElementWrapper(name = "tickets", required = true)
  @XmlElement(name = "ticket")
  private List<Ticket> tickets;
}
