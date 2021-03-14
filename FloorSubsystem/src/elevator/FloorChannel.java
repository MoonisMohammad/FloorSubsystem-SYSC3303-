package elevator;
import java.rmi.Remote;
import java.rmi.RemoteException;

import dataTypes.Data;
import dataTypes.FloorData;

public interface FloorChannel extends Remote{

	public void passChannel(FloorData d) throws RemoteException;

	public boolean elevatorArrived(int floor,int Elevator) throws RemoteException;


}
