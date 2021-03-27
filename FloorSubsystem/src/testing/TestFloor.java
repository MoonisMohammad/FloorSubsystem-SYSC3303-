package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import elevator.Floor;
import dataTypes.FloorData;


public class TestFloor {

	/** 
	 *
	 * Tests method InputStringToData() in Floor 
	 *
	 */

	@Test
	public void testInputStringToData() {
		
		Floor floor = new Floor();
		String testInput = "1:2:3.4 5 down 6";
		FloorData testData  = new FloorData(1,2,3,4,5,false,6);
		
		
		assert(testData.TimeInSeconds()== floor.inputStringToData(testInput).TimeInSeconds());
		assert(testData.getFloor()== floor.inputStringToData(testInput).getFloor());
		assert(testData.getUp()== floor.inputStringToData(testInput).getUp());
		
		testInput = "1:2:3.4 DoorError 1";
		testData  = new FloorData(1,2,3,4,1);
		assert(testData.TimeInSeconds()== floor.inputStringToData(testInput).TimeInSeconds());
		assert(testData.elevator()== floor.inputStringToData(testInput).elevator());
		
		testInput = "1:2:3.4 SensorError 1";
		testData  = new FloorData(1,2,3,4,1);
		assert(testData.TimeInSeconds()== floor.inputStringToData(testInput).TimeInSeconds());
		assert(testData.elevator()== floor.inputStringToData(testInput).elevator());
	
	}
	


}