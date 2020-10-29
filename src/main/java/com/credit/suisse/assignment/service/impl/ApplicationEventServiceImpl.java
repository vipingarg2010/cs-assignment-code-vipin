package com.credit.suisse.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.credit.suisse.assignment.entity.EventEntity;
import com.credit.suisse.assignment.service.ApplicationEventService;
import com.credit.suisse.assignment.util.HibernateUtil;

public class ApplicationEventServiceImpl implements ApplicationEventService {

	Logger logger = Logger.getLogger(ApplicationEventServiceImpl.class.getName());

	@Override
	public boolean saveApplicationEntity(EventEntity event) {
		logger.debug("ApplicationEvent Object to be saved is :" + event);
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
			logger.info("ApplicationEvent Object with Id :" + event.getEventId() + " and Duration is :"
					+ event.getEventDuration() + "is saved successfully");
			return true;
		} catch (Exception exception) {
			logger.error("An error occured while saving the event to Database so Rolling back the transaction");
			if (session != null) {
				session.getTransaction().rollback();
			}
			return false;
		}
	}

	@Override
	public List<EventEntity> getApplicationEntities() {
		try {
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			List<EventEntity> events = session1.createQuery("from ApplicationEvent").list();
			events.forEach(event -> logger.info(event));
			return events;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<EventEntity>();
		}
	}
}
