package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import elevator.ElevatorSimulator;

public class TestElevatorSimulator {
	
	/** 
	 *
	 * Tests method SimulateSensorFault() in ElevatorSimulator
	 *
	 */
	
	@Test
	public void testSimulateSensorFault() {
		 
		ElevatorSimulator testElev = new ElevatorSimulator(1,null);
		testElev.simulateSensorFault();
		assert(true == testElev.sensorFault());
	}

}
