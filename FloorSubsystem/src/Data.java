public class Data {
	int hour;
	int minute;
	int seconds;
	int milliSeconds;
	int floor;
	boolean up;
	int carButton;
	
	/** 
	 *
	 * It is a constructor. 
	 *
	 * @param hour  the hour
	 * @param minute  the minute
	 * @param seconds  the seconds
	 * @param milliSeconds  the milliseconds
	 * @param floor  the floor
	 * @param up  the up
	 * @param carButton  the car button
	 */

	/**
	 * out dated constructor
	 * 
	 * 
	 * @param hour 
	 * @param minute
	 * @param seconds
	 * @param milliSeconds
	 * @param floor
	 * @param up
	 * @param carButton
	 */
	public Data(int hour,int minute,int seconds,int milliSeconds,int floor,boolean up,int carButton) {

		this.hour =hour;
		this.minute = minute;
		this.seconds = seconds;
		this.milliSeconds = milliSeconds;
		this.floor = floor;
		this.up = up;
		this.carButton= carButton;	
		
	}

	

	/** 
	 *
	 * It is a constructor. 
	 *
	 */
	public Data() {}


	public Data(int floor) {
		hour = 0;
		minute = 0;
		seconds = 0;
		milliSeconds = 0;
		this.floor = floor;
		up = false;
		carButton = 0;
	}
	/**
	 * only sets the floor and the up boolean
	 * everything else is 0
	 * @param floor
	 * @param up
	 */
	public Data(int floor,boolean up) {
		hour = 0;
		minute = 0;
		seconds = 0;
		milliSeconds = 0;
		this.floor = floor;
		this.up = up;
		carButton = 0;
	}

	// all the getters


	
	/** 
	 *
	 * returns hour from data time stamp
	 *
	 * @return int
	 */

	public int hour() {
		
		return hour;
	}
	
	/** 
	 *
	 * returns minute from data time stamp
	 *
	 * @return int
	 */
	public int minute() {
		
		return minute;
	}
	
	/** 
	 *
	 * returns seconds from data time stamp
	 *
	 * @return int
	 */
	public int seconds() {
		
		return seconds;
	}
	
	/** 
	 *
	 * returns milliseconds from data time stamp
	 *
	 * @return int
	 */
	public int milliSeconds() {
		
		return milliSeconds;
	}
	
	/** 
	 *
	 * returns Floor number
	 *
	 * @return int
	 */
	public int floor() {
		
		return floor;
	}
	
	/** 
	 *
	 * Is true if up button was pressed
	 *
	 * @return boolean
	 */
	public boolean isUp() {
		
		return up;
	}
	
	/** 
	 *
	 * returns the car button Car button
	 *
	 * @return int
	 */
	public int carButton() {
		
		return carButton;
	}
	
	/** 
	 *
	 * Returns the system input time
	 *
	 * @return int 
	 */
	public int TimeInSeconds() {
		
		return TimeInSeconds;
	}
	
	/** 
	 *
	 * Returns the Simulated Time
	 *
	 * @return int 
	 */
	public int simulatedTime() {
		
		return simulatedTime;
	}
	
	/** 
	 *
	 * Calculates simulated Time
	 * @return 
	 *
	 * @return int 
	 */
	public void simulatedTime(int reference) {
		
		simulatedTime = TimeInSeconds -  reference;
	}
	
	public String toString() {
		return simulatedTime+" ms";
	}
	

	/** 
	 *
	 * To string
	 *
	 * @return String
	 */

	public String toString() {
		return hour+":"+minute+":"+seconds+"."+milliSeconds+" at floor "+floor+" up was "+up+" and carbutton "+carButton;
	}
	
	
	

}
