package elevator;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartFloorSubSystem {

	static FloorChannel sendChannel;

	public static void main(String[] args)  {


		Thread floorThread;
		Registry schedulerRegistry;

		try {
			schedulerRegistry = LocateRegistry.getRegistry(5454);
			sendChannel = (FloorChannel) schedulerRegistry.lookup("FloorChannel");
			System.err.println("Floor send channel ready");

		}catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {

			e.printStackTrace();

		};

		Floor floor = new Floor(sendChannel);
		floorThread = new Thread(floor,"Floor Thread");

		Registry registry;
		FloorInterface stub;
		FloorSubsystem floorSubsystem;
		try {
			floorSubsystem= new FloorSubsystem(sendChannel);
			stub = (FloorInterface) UnicastRemoteObject.exportObject((FloorInterface)floorSubsystem, 69);
			registry = LocateRegistry.createRegistry(69);
			registry.bind("FloorInterface",stub);
			System.err.println("Floor receive channel ready");



		}catch(RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();

		} catch (java.rmi.AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		floorThread.start();

		while(true) {
			//wait
		}



	}

}
