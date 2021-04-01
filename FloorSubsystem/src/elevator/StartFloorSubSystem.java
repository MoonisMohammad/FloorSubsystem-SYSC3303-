package elevator;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartFloorSubSystem {

	static FloorChannel sendChannel;
	
	/** 
	 *
	 *Starts the floor subsystem//new
	 * 
	 *
	 */
	public static void main(String[] args)  {


		Thread floorThread;
		Registry schedulerRegistry;

		boolean done = false;
		while (!done) {
		try {
			
			System.out.println("Trying to connect");
			schedulerRegistry = LocateRegistry.getRegistry(5454);
			sendChannel = (FloorChannel) schedulerRegistry.lookup("FloorChannel");
			System.err.println("Floor send channel ready");
			done = true;

		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {

			e.printStackTrace();

		}
		}

		Registry registry;
		FloorInterface stub;
		FloorSubsystem floorSubsystem;
		try {
			floorSubsystem= new FloorSubsystem(sendChannel);
			stub = (FloorInterface) UnicastRemoteObject.exportObject((FloorInterface)floorSubsystem, 69);
			registry = LocateRegistry.createRegistry(69);
			registry.bind("FloorInterface",stub);
			System.err.println("Floor receive channel ready");

			Floor floor = new Floor(sendChannel,floorSubsystem);
			floorThread = new Thread(floor,"Floor Thread");
			floorThread.start();
			
			while(true) {
				floorSubsystem.updateDisplay();//wait
			}

		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();

		} catch (java.rmi.AlreadyBoundException e) {
			e.printStackTrace();
		} 
		
		
		

		



	}

}
