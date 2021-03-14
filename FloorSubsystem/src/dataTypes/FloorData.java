package dataTypes;

import java.util.ArrayList;

/**
 * The class Floor data extends data
 */ 
public class FloorData extends Data implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	transient int TimeInSeconds;
	transient int simulatedTime;

	private int  floor;
	private boolean  up;
	private ArrayList<Integer> buttonPressed;
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
	 * Returns the system input time
	 *
	 * @return int 
	 */
	public int getFloor() {

		return floor;
	}
	public boolean getUp() {
		return up;
	}
	public ArrayList<Integer> getButton() {
		return buttonPressed;
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
		return "floor: "+floor+" going up "+up+" to "+buttonPressed.get(0)+" ";
	}




}

