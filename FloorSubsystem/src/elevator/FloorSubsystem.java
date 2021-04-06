package elevator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataTypes.FloorData;
import dataTypes.elevatorMoveRequest;

/**
 * The class Floor subsystem implements floor interface
 */ 

public class FloorSubsystem extends JFrame implements FloorInterface,Runnable {

	private volatile Queue<elevatorMoveRequest> requests = new LinkedList(); ;
	public static long startingTime;
	static FloorChannel sendChannel;
	public HashMap<Integer, ElevatorSimulator> elevators = new HashMap<Integer, ElevatorSimulator>();
	public boolean timeCalculationRequest;

	JLabel idLabel1,floorLabel1,stateLabel1,error1;
	JLabel idLabel2,floorLabel2,stateLabel2,error2;
	JLabel idLabel3,floorLabel3,stateLabel3,error3;
	JLabel idLabel4,floorLabel4,stateLabel4,error4;

	String noError = "<html><font color='green'>no error</font></html>";
	String sensorError = "<html><font color='red'>Sensor error</font></html>";
	String doorError = "<html><font color='red'>Door error</font></html>";

	/** 
	 *
	 * Floor subsystem constructor
	 *
	 * @param sendChannel  the send channel
	 * @return 	public
	 */

	public FloorSubsystem(FloorChannel sendChannel) {

		startingTime = System.nanoTime();
		this.sendChannel = sendChannel;
		ElevatorSimulator elev0,elev1,elev2,elev3;
		elev0 = new ElevatorSimulator(0,sendChannel);
		elev1 = new ElevatorSimulator(1,sendChannel);
		elev2 = new ElevatorSimulator(2,sendChannel);
		elev3 = new ElevatorSimulator(3,sendChannel);


		elevators.put(0,elev0);
		elevators.put(1,elev1);
		elevators.put(2,elev2);
		elevators.put(3,elev3);


		JPanel panel = new JPanel();
		LayoutManager layout =  new GridLayout(5,4);;  
		panel.setLayout(layout);


		JLabel idHeading= new JLabel(" Elevator ID");
		JLabel floorHeading= new JLabel("Floor");
		JLabel stateHeading= new JLabel("State");
		JLabel errorHeading = new JLabel("Error");

		panel.add(idHeading);
		panel.add(floorHeading);
		panel.add(stateHeading);
		panel.add(errorHeading);

		getContentPane().add(panel, BorderLayout.CENTER);

		idLabel1= new JLabel("  "+elevators.get(0).elevatorID());
		floorLabel1= new JLabel();
		stateLabel1= new JLabel();
		error1= new JLabel(noError);
		panel.add(idLabel1);
		panel.add(floorLabel1);
		panel.add(stateLabel1);
		panel.add(error1);


		idLabel2= new JLabel("  "+elevators.get(1).elevatorID());
		floorLabel2= new JLabel();
		stateLabel2= new JLabel();
		error2= new JLabel(noError);
		panel.add(idLabel2);
		panel.add(floorLabel2);
		panel.add(stateLabel2);
		panel.add(error2);

		idLabel3= new JLabel("  "+elevators.get(2).elevatorID());
		floorLabel3= new JLabel();
		stateLabel3= new JLabel();
		error3= new JLabel(noError);
		panel.add(idLabel3);
		panel.add(floorLabel3);
		panel.add(stateLabel3);
		panel.add(error3);

		idLabel4= new JLabel("  "+elevators.get(3).elevatorID());
		floorLabel4= new JLabel();
		stateLabel4= new JLabel();
		error4= new JLabel(noError);
		panel.add(idLabel4);
		panel.add(floorLabel4);
		panel.add(stateLabel4);
		panel.add(error4);

		updateDisplay();
		setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);


	}

	public void updateDisplay() {



		floorLabel1.setText(elevators.get(0).currentFloor()+"");
		stateLabel1.setText(elevators.get(0).state());


		floorLabel2.setText(elevators.get(1).currentFloor()+"");
		stateLabel2.setText(elevators.get(1).state());


		floorLabel3.setText(elevators.get(2).currentFloor()+"");
		stateLabel3.setText(elevators.get(2).state());

		floorLabel4.setText(elevators.get(3).currentFloor()+"");
		stateLabel4.setText(elevators.get(3).state());





	}
	@Override
	public void Move(int elevator, boolean move) throws RemoteException{

		requests.add(new elevatorMoveRequest(elevator,move));


	}

	/** 
	 *
	 * Moves elevator up or down
	 *
	 * @param elevator  the elevator
	 * @param move  the move
	 * @throws   RemoteException
	 */

	public void HandleMoveRequest(int elevator, boolean move){


		if(move) {

			System.out.println(currentTime()+">"+"Received from Scheduler: make elevator "+ elevator +" to go up ");
			elevators.get(elevator).upRequest(true);
			Thread Thread = new Thread(elevators.get(elevator),"Floor Thread");
			Thread.start();

		}
		else {
			

				System.out.println(currentTime()+">"+"Received from Scheduler: make elevator "+ elevator +" to go down ");
				elevators.get(elevator).upRequest(false);
				Thread Thread = new Thread(elevators.get(elevator),"Floor Thread");
				Thread.start();

			
			}




	}

	/** 
	 *
	 * Creates sensor error in a elevator
	 *
	 * @param elevator  the elevator

	 */

	public void simulateSensorError(int elevator) {


		elevators.get(elevator).simulateSensorFault();

	}

	/** 
	 *
	 * Displays sensor error in a elevator
	 *
	 * @param elevator  the elevator

	 */

	public void displaySensorError(int elevator)throws RemoteException {

		System.out.println(currentTime()+">"+"Received from Scheduler: Sensor error at "+ elevator);

		if(elevator == 0)error1.setText(sensorError);
		else if(elevator == 1)error2.setText(sensorError);
		else if(elevator == 2)error3.setText(sensorError);
		else error4.setText(sensorError);

	}

	/** 
	 *
	 * Notifies door error in a elevator
	 *
	 * @param elevator  the elevator
	 * @throws InterruptedException 

	 */

	public void displayDoorError(int elevator) throws InterruptedException,RemoteException {

		System.out.println(currentTime()+">"+"Received from Scheduler: Door error at "+ elevator);

		if(elevator == 0)error1.setText(doorError);
		else if(elevator == 1)error2.setText(doorError);
		else if(elevator == 2)error3.setText(doorError);
		else error4.setText(doorError);

		TimeUnit.SECONDS.sleep(3);

		if(elevator == 0)error1.setText(noError);
		else if(elevator == 1)error2.setText(noError);
		else if(elevator == 2)error3.setText(noError);
		else error4.setText(noError);

	}

	/** 
	 *
	 * returns current time 
	 *
	 * @return long
	 */
	public long currentTime(){

		return ((System.nanoTime() - startingTime) / 1000000000); 


	}

	/**
	 * 
	 * Calculates the time it took to deal with all the inputs
	 */
	public void calculateTime() throws RemoteException {

		timeCalculationRequest = true;

	}
	/**
	 * return true if requested for time calculation by scheduler
	 *@return boolean time calculation request
	 */

	public boolean timeCalculationRequest() throws RemoteException {

		return timeCalculationRequest;

	}

	@Override
	public void run() {

		while(true) {
			if(!requests.isEmpty()) {

				HandleMoveRequest(requests.peek().elevator,requests.peek().up);
				requests.poll();
			}
		}

	}

}
