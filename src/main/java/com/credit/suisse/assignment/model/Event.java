package com.credit.suisse.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Event {
	@JsonProperty("id")
	private final String id;
	@JsonProperty("state")
	private final String state;
	@JsonProperty("type")
	private final String type;
	@JsonProperty("host")
	private final String host;
	@JsonProperty("timestamp")
	private final Long timeStamp;

	@JsonCreator
	public Event(@JsonProperty("id") String id, @JsonProperty("state") String state, @JsonProperty("type") String type,
			@JsonProperty("host") String host, @JsonProperty("timestamp") Long timeStamp) {
		this.id = id;
		this.state = null;
		this.type = type;
		this.host = host;
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Event event = (Event) o;
		return Objects.equals(id, event.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
