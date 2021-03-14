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


	public void goUp() throws InterruptedException, RemoteException {

		moveFloorTime();
		currentFloor++;
		sendChannel.elevatorArrived(currentFloor,elevatorID);


	}

	public void goDown() throws InterruptedException, RemoteException {

		moveFloorTime();
		currentFloor--;
		sendChannel.elevatorArrived(currentFloor,elevatorID);

	}

	

	public void moveFloorTime() throws InterruptedException {

		TimeUnit.SECONDS.sleep(3);

	}



}
