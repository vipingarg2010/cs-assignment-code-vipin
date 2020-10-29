package com.credit.suisse.assignment;

import com.credit.suisse.assignment.model.Event;
import com.credit.suisse.assignment.service.impl.ApplicationEventServiceImpl;
import com.credit.suisse.assignment.util.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


public class Application {

    private static final ObjectMapper OBJECT_MAPPER;
    private static final EventProcessor eventProcessor;
    
    static Logger logger = Logger.getLogger(Application.class.getName());
    
    static {
        OBJECT_MAPPER = new ObjectMapper();
        eventProcessor = new EventProcessor(new ConcurrentHashMap<String, Event>(), new ApplicationEventServiceImpl());
    }

    public static void main(String[] args){
    
    	if(args.length==0) {
    		logger.error("No input file is provided to load the events");
    		return;
    	}
        String logFile = args[0];      	
      
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)))) {
            String line = null;
            while ((line = br.readLine()) != null) {               
            	Event event = OBJECT_MAPPER.readValue(line.trim(), Event.class);
            	eventProcessor.processEvent(event);
            }
            //This is to view the saved events in database please uncomment to see
            //eventProcessor.viewSavedEvents();
            
        } catch (IOException ex) {
        	logger.error("File not found in the given location");
        	ex.printStackTrace();
        }
        finally {
			HibernateUtil.shutdown();
		}
    }
}