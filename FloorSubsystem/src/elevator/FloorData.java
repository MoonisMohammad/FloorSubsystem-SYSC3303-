package elevator;
/**
* The class Floor data extends data
*/ 
public class FloorData extends Data {
	
	int TimeInSeconds;
	int simulatedTime;
	
	/** 
	 *
	 * Floor data
	 *
	 * @param systemInputTime  the system input time
	 * @param floor  the floor
	 * @param up  the up
	 * @param carButton  the car button
	 * @return 	public
	 */
	public FloorData(int hour,int minute,int seconds,int milliSeconds,int floor,boolean up,int carButton) {

		
		super.hour =hour;
		super.minute = minute;
		super.seconds = seconds;
		super.milliSeconds = milliSeconds;
		super.floor = floor;
		super.up = up;
		super.carButton= carButton;
		this.TimeInSeconds= hour*3600+minute*60+seconds;
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
	
	
	

}

