package dataTypes;

import java.util.ArrayList;


public class FloorData extends Data implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	transient int TimeInSeconds;
	transient int simulatedTime;

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
		return "floor: "+floor+" going up "+up+" to "+buttonPressed.get(0)+" ";
	}




}