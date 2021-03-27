package elevator;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ElevatorSimulator {

	int currentFloor;
	int elevatorID;
	boolean check;
	boolean sensorFault;
	private FloorChannel sendChannel;

	ElevatorSimulator(int elevatorID,FloorChannel sendChannel){

		currentFloor = 1;
		this.elevatorID = elevatorID;
		this.sendChannel = sendChannel;
		this.sensorFault = false;


	}
	
	
	public void simulateSensorFault() {
		
		this.sensorFault = true;
		
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
		
		System.out.println("elevator "+elevatorID+" at floor"+currentFloor+"is going up");
		moveFloorTime();
		currentFloor++;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator "+elevatorID+" went up and arrived at"+currentFloor );

		if(!check) goUp();

		else if(check) { 

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

		moveFloorTime();
		currentFloor--;
		check = sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator "+elevatorID+" went down and arrived at "+currentFloor);

		if(!check) goDown();

		else if(check) { 

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
