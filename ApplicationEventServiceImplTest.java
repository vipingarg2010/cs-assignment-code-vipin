package com.credit.suisse.assignment.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.credit.suisse.assignment.entity.EventEntity; 
import com.credit.suisse.assignment.service.ApplicationEventService;
import com.credit.suisse.assignment.service.impl.ApplicationEventServiceImpl;

public class ApplicationEventServiceImplTest  {

	ApplicationEventService service = new ApplicationEventServiceImpl();

	@Test
	public void insert_Event_Alert_Yes() {
		EventEntity event = new EventEntity("TestId", 10, "app_log");
		event.setAlert("Y");
		boolean isEventSaved = service.saveApplicationEntity(event);
		assertEquals(true, isEventSaved);
	}
	
	@Test
	public void insert_Event_Alert_No() {
		EventEntity event = new EventEntity("TestId1", 3, "app_log");
		event.setAlert("N");
		boolean isEventSaved = service.saveApplicationEntity(event);
		assertEquals(true, isEventSaved);
	}
	
	@Test
	public void insert_Event_Alert_Fail() {
		EventEntity event = new EventEntity("TestId2", 6, "app_log");		
		boolean isEventSaved = service.saveApplicationEntity(event);
		assertEquals(false, isEventSaved);
	}
	
	@Test
	public void getApplicationEvents() {		
		EventEntity event = new EventEntity("TestId2", 6, "app_log");	
		event.setAlert("N");
		service.saveApplicationEntity(event);
		List<EventEntity> events = service.getApplicationEntities();
		assertEquals(true, events.size()>0);
	}
}