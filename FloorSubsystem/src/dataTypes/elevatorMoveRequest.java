package dataTypes;

public class elevatorMoveRequest {
	
	public boolean up;
	public int elevator;
	
	public elevatorMoveRequest(int elevator,boolean up) {
		
		this .up = up;
		this.elevator = elevator;
	}

}
