package elevator;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ElevatorSimulator {

	int currentFloor;
	int elevatorID;
	boolean check;
	boolean sensorFault;
	boolean doorFault;
	boolean up;
	boolean down;
	boolean stop;
	private FloorChannel sendChannel;

	public ElevatorSimulator(int elevatorID,FloorChannel sendChannel){

		currentFloor = 1;
		this.elevatorID = elevatorID;
		this.sendChannel = sendChannel;
		this.sensorFault = false;
		this.doorFault = false;
		up =false;
		down = false;
		stop =true;


	}
	/**
	 * @return 
	 * 
	 * 
	 */
	public int elevatorID() {


		return elevatorID;
	}

	/**
	 * @return 
	 * 
	 * 
	 */
	public int currentFloor() {


		return currentFloor;
	}

	/** 
	 * simulates a sensor failure
	 */
	public void simulateSensorFault() {

		this.sensorFault = true;

	}
	
	/** 
	 *Notify a door failure
	 */
	public void simulateDoorFault() {

		this.doorFault = true;

	}

	/** 
	 * return true if there is a sensor failure
	 * @return boolean
	 */
	public boolean sensorFault() {

		return sensorFault;

	}

	/** 
	 * return true if there is a sensor failure
	 * @return boolean
	 */
	public String toString() {

		if(sensorFault) return elevatorID + " | "+currentFloor+" | "+ " Sensor-ERROR ";
		if(doorFault) return elevatorID + " | "+currentFloor+" | "+ " Door-ERROR ";
		else if(up) return elevatorID + " | "+currentFloor+" | "+ " up ";
		else if(down)return elevatorID + " | "+currentFloor+" | "+ " down ";
		else return elevatorID + " | "+currentFloor+" | "+ " stop ";	


	}

	/** 
	 *
	 * Go up
	 *
	 * @param InterruptedException  the interrupted exception
	 * @param {  the {
	 * @throws   InterruptedException
	 * @throws  RemoteException 
	 */
	public void goUp() throws InterruptedException, RemoteException {

		if(sensorFault) {
			System.out.println("elevator "+elevatorID+" arrival sensor stop working");
			return; // Makes elevator 2 arrival sensor stop working
		}

		up =true;
		down = false;
		stop =false;

		System.out.println("elevator "+elevatorID+" at floor"+currentFloor+"is going up");
		moveFloorTime();
		currentFloor++;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator "+elevatorID+" went up and arrived at"+currentFloor );

		if(!check) goUp();

		else if(check) {

			up =false;
			down = false;
			stop =true;
			System.out.println("elevator "+elevatorID+"stopped at "+currentFloor);
			return;

		}

	}
	/** 
	 *
	 * Go down
	 *
	 * @param InterruptedException  the interrupted exception
	 * @param {  the {
	 * @throws   InterruptedException
	 * @throws  RemoteException 
	 */
	public void goDown() throws InterruptedException, RemoteException {

		if(sensorFault) {
			System.out.println("elevator "+elevatorID+" arrival sensor stop working");
			return; // Makes elevator 2 arrival sensor stop working
		}

		up =false;
		down = true;
		stop =false;

		moveFloorTime();
		currentFloor--;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator "+elevatorID+" went down and arrived at "+currentFloor);

		if(!check) goDown();

		else if(check) { 

			up =false;
			down = false;
			stop =true;
			System.out.println("elevator "+elevatorID+" stopped at "+currentFloor);
			return;

		}

	}


	/** 
	 *
	 * Simulates move floor time
	 *
	 * @param InterruptedException  the interrupted exception
	 * @throws   InterruptedException 
	 */

	public void moveFloorTime() throws InterruptedException {

		TimeUnit.SECONDS.sleep(3);

	}



}
