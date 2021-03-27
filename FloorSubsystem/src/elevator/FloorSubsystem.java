package elevator;

import java.rmi.RemoteException;
import java.util.HashMap;


/**
* The class Floor subsystem implements floor interface
*/ 

public class FloorSubsystem implements FloorInterface{

	static FloorChannel sendChannel;
	HashMap<Integer, ElevatorSimulator> elevators = new HashMap<Integer, ElevatorSimulator>();
	
	/** 
	 *
	 * Floor subsystem constructor
	 *
	 * @param sendChannel  the send channel
	 * @return 	public
	 */

	public FloorSubsystem(FloorChannel sendChannel) {

		this.sendChannel = sendChannel;
		ElevatorSimulator elev0,elev1,elev2;
		elev0 = new ElevatorSimulator(0,sendChannel);
		elev1 = new ElevatorSimulator(1,sendChannel);
		elev2 = new ElevatorSimulator(2,sendChannel);
		
		
		elevators.put(0,elev0);
		elevators.put(1,elev1);
		elevators.put(2,elev2);

	}
	
	/** 
	 *
	 * Moves elevator up or down
	 *
	 * @param elevator  the elevator
	 * @param move  the move
	 * @throws   RemoteException
	 */
		


	@Override
	public void Move(int elevator, boolean move) throws RemoteException{
		

		if(move)
			try {
				
				System.out.println("Received from Scheduler: make elevator "+ elevator +" to go up ");
				elevators.get(elevator).goUp();

			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}
		else
			try {
				
				System.out.println("Received from Scheduler: make elevator "+ elevator +" to go down ");
				elevators.get(elevator).goDown();

			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}




	}
	
	public void simulateSensorError(int elevator) {
		
		elevators.get(elevator).simulateSensorFault();

	}

}
