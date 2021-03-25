package dataTypes;

import java.util.ArrayList;


public class FloorData extends Data implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	transient int TimeInSeconds;
	transient int simulatedTime;
	transient int elevator;
	transient boolean doorError;

	private int  floor;
	private boolean  up;
	private ArrayList<Integer> buttonPressed = new ArrayList();

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

		this.floor = floor;
		this.up = up;
		this. buttonPressed.add(carButton);
		this.TimeInSeconds= hour*3600+minute*60+seconds;
		this.doorError=false;
	}
	
	/** 
	 *
	 * Floor data door error constructor
	 *
	 * @param systemInputTime  the system input time
	 * @param floor  the floor
	 */

	public FloorData(int hour,int minute,int seconds,int milliSeconds,int elevator) {

		this.elevator = elevator;
		this.TimeInSeconds= hour*3600+minute*60+seconds;
		this.doorError=true;
	}
	
	/** 
	 *
	 * Returns the elevator
	 *
	 * @return int 
	 */

	public int elevator() {

		return elevator;
	}
	
	/** 
	 *
	 * Returns the Door Error
	 *
	 * @return boolean 
	 */

	public boolean doorError() {

		return doorError;
	}
	
	/** 
	 *
	 * Returns the floor
	 *
	 * @return int 
	 */

	public int getFloor() {

		return floor;
	}

	/** 
	 *
	 * Returns the up value
	 *
	 * @return boolean 
	 */
	public boolean getUp() {
		return up;
	}

	/** 
	 *
	 * Returns the values of buttons
	 *
	 * @return ArrayList<Integer> 
	 */
	public ArrayList<Integer> getButton() {
		return buttonPressed;
	}

	/** 
	 *
	 * Returns the values of buttons
	 *
	 * @return ArrayList<Integer> 
	 */
	public int TimeInSeconds() {

		return TimeInSeconds;
	}


	public int simulatedTime() {

		return simulatedTime;
	}

	/** 
	 *
	 * Calculates simulated Time
	 *
	 * @return int 
	 */
	public void simulatedTime(int reference) {

		simulatedTime = TimeInSeconds -  reference;
	}

	/** 
	 *
	 * converts floor data to a string
	 *
	 * @return String 
	 */
	public String toString() {

		if(up) return "System time:"+simulatedTime+" Floor:"+floor+" up was pressed"+" carbuttons: "+buttonPressed.get(0);

		else return "System time:"+simulatedTime+" Floor:"+floor+" down was pressed"+" carbuttons: "+buttonPressed.get(0);
	}




}