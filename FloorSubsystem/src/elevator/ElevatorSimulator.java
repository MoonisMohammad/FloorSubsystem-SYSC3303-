package elevator;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ElevatorSimulator {

	int currentFloor;
	int elevatorID;
	boolean check;
	boolean sensorFault;
	boolean up;
	boolean down;
	boolean stop;
	boolean fullSpeed;
	private FloorChannel sendChannel;
	public static long startingTime;

	public ElevatorSimulator(int elevatorID,FloorChannel sendChannel){

		startingTime = System.nanoTime();
		currentFloor = 1;
		this.elevatorID = elevatorID;
		this.sendChannel = sendChannel;
		this.sensorFault = false;
		up =false;
		down = false;
		stop =true;
		fullSpeed = false;


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
	public String state() {

		
		if(up) return "up";
		else if(down)return "down";
		else return "stop";	


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
			System.out.println(currentTime()+">"+"elevator "+elevatorID+" arrival sensor stop working");
			return; // Makes elevator 2 arrival sensor stop working
		}


		up =true;
		down = false;
		stop =false;

		if(fullSpeed) {

			fullSpeedTime();
		}

		else {
			initialSpeedTime();
			fullSpeed =true;
		}

		currentFloor++;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println(currentTime()+">"+"elevator "+elevatorID+" went up and arrived at "+currentFloor );

		if(!check) goUp();

		else if(check) {
			fullSpeed =false;
			up =false;
			down = false;
			stop =true;
			System.out.println(currentTime()+">"+"elevator "+elevatorID+" stopped at "+currentFloor);
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
			System.out.println(currentTime()+">"+"elevator "+elevatorID+" arrival sensor stop working");
			return; // Makes elevator 2 arrival sensor stop working
		}

		up =false;
		down = true;
		stop =false;

		if(fullSpeed) {

			fullSpeedTime();
		}

		else {
			initialSpeedTime();
			fullSpeed =true;
		}
		
		currentFloor--;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println(currentTime()+">"+"elevator "+elevatorID+" went down and arrived at "+currentFloor);

		if(!check) goDown();

		else if(check) { 
			fullSpeed =false;
			up =false;
			down = false;
			stop =true;
			System.out.println(currentTime()+">"+"elevator "+elevatorID+" stopped at "+currentFloor);
			return;

		}

	}




	/** 
	 *
	 * Simulates move floor time with initial speed
	 *
	 * @param InterruptedException  the interrupted exception
	 * @throws   InterruptedException 
	 */

	public void initialSpeedTime() throws InterruptedException {

		TimeUnit.MILLISECONDS.sleep(4700);

	}

	/** 
	 *
	 * Simulates move floor time with full speed
	 *
	 * @param InterruptedException  the interrupted exception
	 * @throws   InterruptedException 
	 */

	public void fullSpeedTime() throws InterruptedException {

		TimeUnit.SECONDS.sleep(1);

	}

	/** 
	 *
	 * returns current time 
	 *
	 * @return long
	 */
	public long currentTime(){

		return ((System.nanoTime() - startingTime) / 1000000000); 


	}



}
