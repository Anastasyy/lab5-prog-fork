package com.megateam.lab.common.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Getter
@EqualsAndHashCode
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
  @XmlElement(name = "xCoord", required = true)
  private float x;

  @NonNull
  @XmlElement(name = "yCoord", required = true)
  private Integer y;

  @Override
  public String toString() {
    return String.format("Coordinates: [x: %f, y: %d]", x, y);
  }
}
