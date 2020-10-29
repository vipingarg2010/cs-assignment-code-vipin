package com.credit.suisse.assignment.service;

import java.util.List;

import com.credit.suisse.assignment.entity.EventEntity;

public interface ApplicationEventService {
	
	boolean saveApplicationEntity(EventEntity event);
	
	List<EventEntity> getApplicationEntities();

}
