package elevator;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FloorInterface extends Remote{
	
	public void Move(int elevator,boolean move) throws RemoteException;//true = go up and false = go down
	

}
