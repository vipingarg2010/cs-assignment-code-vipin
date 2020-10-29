package com.credit.suisse.assignment;

import com.credit.suisse.assignment.entity.EventEntity;
import com.credit.suisse.assignment.service.ApplicationEventService;
import com.credit.suisse.assignment.model.Event;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class EventProcessor {

    private final Map<String, Event> eventsMap;
    
    private ApplicationEventService applicationEventService;

    static Logger logger = Logger.getLogger(EventProcessor.class.getName());
    
    private static final int ALERT_TRIGGER = 4;
    
    public EventProcessor(Map<String, Event> eventsMap, ApplicationEventService applicationEventService) {
        this.eventsMap = eventsMap;
        this.applicationEventService = applicationEventService;
    }

    public void processEvent(Event event) {
        if (eventsMap.get(event.getId()) == null) {
            eventsMap.put(event.getId(), event);
            return;
        }
        Event existingEvent = eventsMap.get(event.getId());
        long duration = Math.abs(existingEvent.getTimeStamp() - event.getTimeStamp());
        applicationEventService.saveApplicationEntity(buildApplicationEvent(existingEvent, duration));
        eventsMap.remove(event.getId());
        logger.info("Method processEvent is completed successfully" );
    }

    public static EventEntity buildApplicationEvent(Event event, long eventDuration) {
        String alert = String.valueOf(eventDuration > ALERT_TRIGGER);
        return new EventEntity(event.getId(), (int) eventDuration, event.getType(), event.getHost(), alert);
    }
    
    public void viewSavedEvents() {        
       List<EventEntity> events =  applicationEventService.getApplicationEntities();
       logger.debug("Total Number of events available :"+events.size() );
     }
    
}
