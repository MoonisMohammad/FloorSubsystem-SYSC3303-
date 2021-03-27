package testing;

import static org.junit.Assert.*;
import org.junit.Test;

import elevator.FloorSubsystem;

public class TestFloorSubsystem {
	/** 
	 *
	 * Tests method sensorError() in floorSubsystem 
	 *
	 */
	
	@Test
	public void testSimulateSensorError() {
		
		FloorSubsystem floorSubsystem = new FloorSubsystem(null);
		floorSubsystem.simulateSensorError(1);
		assert(true == floorSubsystem.elevators.get(1).sensorFault());
	}

}