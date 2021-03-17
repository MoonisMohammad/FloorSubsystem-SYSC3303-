package elevator;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ElevatorSimulator {

	int currentFloor;
	int elevatorID;
	private FloorChannel sendChannel;

	ElevatorSimulator(int elevatorID,FloorChannel sendChannel){

		currentFloor = 0;
		this.elevatorID = elevatorID;
		this.sendChannel = sendChannel;


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
		
		System.out.println("elevator"+elevatorID+"at floor"+currentFloor+"is going up");
		moveFloorTime();
		currentFloor++;
		sendChannel.elevatorArrived(currentFloor,elevatorID);
		


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
		
		System.out.println("elevator"+elevatorID+"at floor"+currentFloor+"is going down");
		moveFloorTime();
		currentFloor--;
		sendChannel.elevatorArrived(currentFloor,elevatorID);

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
