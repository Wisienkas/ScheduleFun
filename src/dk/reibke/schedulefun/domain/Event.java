package dk.reibke.schedulefun.domain;

import java.util.Calendar;

public class Event {
	
	private Calendar eventTime;
	private String message;
	private Repeat repeater;
	
	public Event(Calendar time, String message){
		this(time, message, null);
	}
	
	public Event(Calendar time, String message, Repeat repeater){
		
		this.message = message;
		this.eventTime = time;
		
	}
	
	
}
