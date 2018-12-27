import java.util.Random;

public class robot {
	private boolean isFinished;
	private boolean cantMove;	
	private boolean sensorTriggered;
	private int moveCount;
	private String robotName = "";
	
	// methods	
	public robot() {
		isFinished = false;
		cantMove = false;
		sensorTriggered = false;
		moveCount = 0;	
		robotName = "Robbi 1 - a stupid one"; 
	}	
	// public static int move()
	// requests lawn mover to do the next move
	// the result indicates the angle of the move and must be one of 
	//    0, 45, 90, 135, 180, 225, 270, 315
	public String getName() {
		return robotName;
	}	
	public int move() {
		// strategy:
		// choose a random direction in each step
		// stop after MAXMOVE steps
		int MAXMOVE = 100;
		Random randomDir = new Random();
		int direction = randomDir.nextInt(8) *45;
		if (moveCount++ >= MAXMOVE) isFinished = true;
		
		sensorTriggered = false;
		return direction;
	}	
	// public static boolean isFinsihed ()
	// return true if robot indicates that the job is done
	// before the first step, the result ist false
	public boolean isFinished() {
		return isFinished;
	}	
	// public static boolean cantMove ()
	// indicates that the robot stopped working without finishing
	// this is used for later extensions like out of battery status
	// before the first step, the result ist false
	public boolean cantMove() {
		return cantMove;
	}	
	// public static boolean sensorTriggered ()
	// false if last move was successful
	// true if lawn mover hits an obstacle or fence and move was not perfomed
	public void sensorTriggered() {
		//void;
	}
}