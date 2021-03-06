package elevator;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FloorInterface extends Remote{
	
	/** 
	 *
	 * Moves elevator up or down
	 * 
	 * 
	 * @param elevator  the elevator
	 * @param move  the move
	 * @throws   RemoteException
	 *
	 */
	
	public void Move(int elevator,boolean move) throws RemoteException;//true = go up and false = go down
	

}
