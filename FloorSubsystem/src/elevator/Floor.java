package elevator;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.concurrent.TimeUnit;

import dataTypes.FloorData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Queue;

public class Floor implements Runnable{

	private Queue<FloorData> inputs;
	private FloorChannel floorChannel;
	private FloorSubsystem floorSubsystem;
	public static long startingTime;

	/** 
	 *
	 * It is a constructor for class Floor. 
	 *
	 */

	public Floor(FloorChannel floorChannel,FloorSubsystem floorSubsystem) {

		this.floorChannel = floorChannel;
		this.floorSubsystem = floorSubsystem;
		inputs = readInputFile();

	}
	
	/** 
	 *
	 * It is a constructor for class Floor. 
	 *
	 */

	public Floor() {

	

	}

	/** 
	 *
	 * It used when the the thread for class Floor is running 
	 *
	 */
	@Override
	public void run() {
		startingTime = System.nanoTime();
		while(true) {

			if(!inputs.isEmpty() && timeCheck(inputs.peek())){
				
				

				try {
					if(inputs.peek().isDoorError()) {

						floorChannel.doorFailure(inputs.peek().elevator());
						

					}
					
					else if(inputs.peek().isSensorError()) {

						floorSubsystem.simulateSensorError(inputs.peek().elevator());
					}

					else { 


						floorChannel.passChannel(inputs.peek());//sends inputs as data to scheduler}
						
					}

				} catch (RemoteException e) {
					e.printStackTrace();
				}

				System.out.println(currentTime()+">"+"Input Sent From Floor: " + inputs.peek());
				inputs.poll();
			}
			
			try {
				if(floorSubsystem.timeCalculationRequest()) {
					
					
					System.out.println("Total time it took to deal with all inputs "+ currentTime());
					
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
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
	 * timeCheck method checks if time of Data d matches with current system time
	 *
	 * @param d  the data whose time needs to be compared
	 * @return boolean
	 */
	public static boolean timeCheck(FloorData d){

		if(d.simulatedTime() <= ((System.nanoTime() - startingTime) / 1000000000)) {
			return true;}

		return false;

	}


	/** 
	 *
	 * Reads floorInput.txt file for input data and arranges the data in a queue
	 *
	 * @return Queue<Data>
	 */
	public Queue<FloorData> readInputFile() {

		Queue<FloorData> inputs = new LinkedList();

		try {
			System.out.println(System.getProperty("user.dir"));
			File myObj = new File("Input.txt");
			Scanner myReader = new Scanner(myObj);
			int i = 0;

			while (myReader.hasNextLine()) {

				String inputLine = myReader.nextLine();
				FloorData data = inputStringToData(inputLine);

				if(i==0) {

					data.simulatedTime(data.TimeInSeconds());
					i++;
				}else {

					data.simulatedTime(inputs.peek().TimeInSeconds());

				}

				inputs.add(data);
			}

			myReader.close();

		} catch (FileNotFoundException e) {

			System.out.println("An error occurred while trying to read input file.");
			e.printStackTrace();

		}

		return inputs;


	}

	/** 
	 *
	 * Converts each line of input in inputFloor.txt to a FloorData Class
	 *
	 * @param input  each single line of input inside the file
	 * @return Data
	 */
	public FloorData  inputStringToData(String input) {

		String[] splitInput = input.split(" ");
		String[] splitTime = splitInput[0].split(":");
		String[] splitSeconds = splitTime[2].split("\\.");

		int hour = Integer.valueOf(splitTime[0]);
		int minute = Integer.valueOf(splitTime[1]);
		int seconds = Integer.valueOf(splitSeconds[0]);
		int milliSeconds = Integer.valueOf(splitSeconds[1]);

		if(splitInput[1].equals("DoorError")) {

			int Elevator = Integer.valueOf(splitInput[2]);
			FloorData data = new FloorData(hour,minute,seconds,milliSeconds,Elevator);
			data.doorError();
			return data;	

		}

		if(splitInput[1].equals("SensorError")) {

			int Elevator = Integer.valueOf(splitInput[2]);
			FloorData data = new FloorData(hour,minute,seconds,milliSeconds,Elevator);
			data.sensorError();
			return data;	

		}

		int floor =Integer.valueOf(splitInput[1]);
		boolean up;

		if(splitInput[2].equals("up")) up= true;

		else up= false;

		int carButton =Integer.valueOf(splitInput[3]);

		FloorData data = new FloorData(hour,minute,seconds,milliSeconds,floor,up,carButton);

		return data;


	}


}
