package dk.reibke.schedulefun.domain;

import java.util.Calendar;

public class Repeat {
	
	//interval in miliseconds
	private long interval;
	
	private int days;
	private byte hours;
	private byte minutes;
	private byte seconds;
	
	// how many times to repeat
	private int repeats;
	
	public Repeat(Calendar first, Calendar next, int times){
		
		setupRepeat(first, next);
		this.repeats = times;
		
	}

	private void setupRepeat(Calendar first, Calendar next) {
		
		this.interval = next.getTimeInMillis() - first.getTimeInMillis();
		
		byte second1 = (byte) first.SECOND;
		byte second2 = (byte) next.SECOND;
		boolean takeOne = false;
		if(second2 < second1){
			takeOne = true;
			second2 += 60;
		}
		this.seconds = (byte) (second2 - second1);
		
		byte hours1 = (byte) first.HOUR_OF_DAY;
		byte hours2 = (byte) next.HOUR_OF_DAY;
		
		
		
		int dayOfYear1 = first.DAY_OF_YEAR;
		int dayOfYear2 = next.DAY_OF_YEAR;
		
		this.days = dayOfYear2 - dayOfYear1;
		
		
		
		
		
		
	}
	
}
