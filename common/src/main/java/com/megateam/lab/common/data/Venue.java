package com.megateam.lab.common.data;

import lombok.*;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Getter
@EqualsAndHashCode
@XmlRootElement(name = "venue")
@XmlAccessorType(XmlAccessType.FIELD)
public class Venue
{
	@XmlAttribute(name = "venueId", required = true)
	private long id;
	@NonNull
	@XmlElement(name = "venueName", required = true)
	private String name;
	@NonNull
	@XmlElement(name = "capacity", required = true)
	private Integer capacity;
	@XmlElement(name = "venueType")
	private VenueType type;

	@Override
	public String toString()
	{
		return String.format(
				"Venue: [id: %d, name: %s, capacity: %d, type: %s]",
				id,
				name,
				capacity,
				(type == null) ? "is not set" : type
				);
	}
}
