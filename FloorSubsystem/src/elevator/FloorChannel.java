package elevator;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FloorChannel extends Remote{

	public void passChannel(Data d) throws RemoteException;

	public boolean elevatorArrived(int floor,int Elevator) throws RemoteException;


}
