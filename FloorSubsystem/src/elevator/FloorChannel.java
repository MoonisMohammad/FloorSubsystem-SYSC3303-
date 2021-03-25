package elevator;
import java.rmi.Remote;
import java.rmi.RemoteException;

import dataTypes.Data;
import dataTypes.FloorData;

public interface FloorChannel extends Remote{
	
	/** 
	 *
	 * Passes Data to scheduler
	 *
	 */

	public void passChannel(FloorData d) throws RemoteException;
	
	/** 
	 *
	 * Tells scheduler elevator has arrived
	 * 
	 *
	 */

	public boolean elevatorArrived(int floor,int Elevator) throws RemoteException;

	public void doorFailure(int elevator) throws RemoteException; 
}
