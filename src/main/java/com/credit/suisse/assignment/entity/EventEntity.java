package com.credit.suisse.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION_EVENT")
public class EventEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = false, nullable = false)
	private String eventId;

	@Column(name = "DURATION", unique = false, nullable = false, length = 30)
	private int eventDuration;

	@Column(name = "EVENT_LOGTYPE", unique = false, nullable = true, length = 30)
	private String eventLogType;

	@Column(name = "HOST", unique = false, nullable = true, length = 30)
	private String host;

	@Column(name = "ALERT", unique = false, nullable = false, length = 5)
	private String alert;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(int eventDuration) {
		this.eventDuration = eventDuration;
	}

	public String getEventLogType() {
		return eventLogType;
	}

	public void setEventLogType(String eventLogType) {
		this.eventLogType = eventLogType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public EventEntity(String eventId, int eventDuration, String eventLogType) {
		super();
		this.eventId = eventId;
		this.eventDuration = eventDuration;
		this.eventLogType = eventLogType;
	}

	public EventEntity(String eventId, int eventDuration, String eventLogType, String host, String alert) {
		this(eventId, eventDuration, eventLogType);
		this.host = host;
		this.alert = alert;
	}

	protected EventEntity() {

	}

	@Override
	public String toString() {
		return "ApplicationEvent [eventId=" + eventId + ", eventDuration=" + eventDuration + ", eventLogType="
				+ eventLogType + ", host=" + host + ", alert=" + alert + "]";
	}

}