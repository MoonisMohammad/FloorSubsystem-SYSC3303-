package elevator;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ElevatorSimulator {

	int currentFloor;
	int elevatorID;
	private FloorChannel sendChannel;

	ElevatorSimulator(FloorChannel sendChannel){

		currentFloor = 0;
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

		moveFloorTime();
		currentFloor++;
		sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator"+elevatorID+"at floor"+currentFloor+"is going up");


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

		moveFloorTime();
		currentFloor--;
		sendChannel.elevatorArrived(currentFloor,elevatorID);
		System.out.println("elevator"+elevatorID+"at floor"+currentFloor+"is going down");

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
