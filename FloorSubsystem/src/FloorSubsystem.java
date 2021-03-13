
import java.rmi.RemoteException;
import java.util.HashMap;

public class FloorSubsystem implements FloorInterface{

	static FloorChannel sendChannel;
	HashMap<Integer, ElevatorSimulator> elevators = new HashMap<Integer, ElevatorSimulator>();

	public FloorSubsystem(FloorChannel sendChannel) {

		this.sendChannel = sendChannel;
		ElevatorSimulator elev0,elev1,elev2;
		elev0 = new ElevatorSimulator(sendChannel);
		elev1 = new ElevatorSimulator(sendChannel);
		elev2 = new ElevatorSimulator(sendChannel);
		elevators.put(0,elev0);
		elevators.put(1,elev1);
		elevators.put(2,elev2);

	}


	@Override
	public synchronized  void Move(int elevator, boolean move) throws RemoteException{

		if(move)
			try {

				elevators.get(elevator).goUp();

			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}
		else
			try {

				elevators.get(elevator).goDown();

			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}




	}

}
