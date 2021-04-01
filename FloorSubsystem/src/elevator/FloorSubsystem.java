package elevator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class Floor subsystem implements floor interface
 */ 

public class FloorSubsystem extends JFrame implements FloorInterface {

	static FloorChannel sendChannel;
	public HashMap<Integer, ElevatorSimulator> elevators = new HashMap<Integer, ElevatorSimulator>();
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	
	/** 
	 *
	 * Floor subsystem constructor
	 *
	 * @param sendChannel  the send channel
	 * @return 	public
	 */

	public FloorSubsystem(FloorChannel sendChannel) {


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
		LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);  
		panel.setLayout(layout);

		JLabel heading = new JLabel(" Elevator ID |"+" Floor |" +" State");
		panel.add(heading);
		getContentPane().add(panel, BorderLayout.CENTER);

		label1  = new JLabel(elevators.get(0).toString());
		label2  = new JLabel(elevators.get(1).toString());
		label3  = new JLabel(elevators.get(2).toString());
		label4  = new JLabel(elevators.get(3).toString());
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		
		setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);
		updateDisplay();

	}

	public void updateDisplay() {


			label1.setText(elevators.get(0).toString());
			label2.setText(elevators.get(1).toString());
			label3.setText(elevators.get(2).toString());
			label4.setText(elevators.get(3).toString());

						

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
	 * Notifies door error in a elevator
	 *
	 * @param elevator  the elevator

	 */

	public void simulateDoorError(int elevator) {

		elevators.get(elevator).simulateDoorFault();

	}

}
