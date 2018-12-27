public class lawnRobot {
	private static int lastDirection = 0;
	private static int newDirection = 0;	
	private static int moveCounter = 0;
	private static int rotationCounter = 0;
	private static int bounceCounter = 0;	
	private static int robotPosX = 20;
	private static int robotPosY = 20;
			
	public static int calcRotates(int lastDir, int newDir) {
		return newDir == lastDir ? 0 : 4 - (Math.abs((Math.abs(newDir-lastDir)/45)-4));
	}	

	public static int getTargetDirectionX(int dir) { //just get target position, not change OG position itself
		if ((dir == 0) || (dir == 180)) {
			return 0;
		} else if ((dir == 45) || (dir == 90) || (dir == 135)) {
			return 1;
		} else {
			return -1;
		}
	}
	public static int getTargetDirectionY(int dir) { //just get target position, not change OG position itself
		if ((dir == 90) || (dir == 270)) {
			return 0;
		} else if ((dir == 135) || (dir == 180) || (dir == 225)) {
			return 1;
		} else {
			return -1;
		}
	}
	public static void printResults(String robotName, String gardenName, garden g) {
		
		System.out.println("=================================================================================================================================================");
		System.out.println("Challenge:       Robot " +  robotName + "        Garden: " + gardenName);
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("# moves: " + moveCounter);
		System.out.println("# rotations: " + rotationCounter);
		System.out.println("# bounces: " + bounceCounter);
		System.out.println("# cut grass/total grass: " + g.getCuttedArea() + "/" + g.getGrassArea() + "      " + g.getCuttedArea() *100 / g.getGrassArea() + " %");
		System.out.println("robot completed mission back in station: " + g.isStation (robotPosX, robotPosY));
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Original");
		g.printOrg();
		System.out.println();
		System.out.println("Nach Robotereinsatz:");
		g.printCut();
		System.out.println();
		System.out.println("Heatmap:");
		g.printHeatmap();
		System.out.println("=================================================================================================================================================");
		
	}
	
	public static void main(String[] args) {
		System.out.println("Challenge started ....\n");
		// read garden
		garden g1 = new garden();
		boolean succ = g1.readGarden("K:/javaprog/workspace_Win10/git/lawnRobot/lawnRobot/garden01.txt");			
		// create robot
		robot r1 = new robot();

		// cutting		
		robotPosX = g1.getStationPosX ();
		robotPosY = g1.getStationPosY (); 
		
		//System.out.printf("%10s%10s%10s%10s\n", "# Moves", "Direction", "Pos X", "Pos Y");
		while ((!r1.isFinished()) && (!r1.cantMove())) {
			newDirection = r1.move();
			rotationCounter = rotationCounter + calcRotates(lastDirection, newDirection);		
			int targetPosX = robotPosX + getTargetDirectionX(newDirection), targetPosY = robotPosY + getTargetDirectionY(newDirection);
			if (!g1.isGrass(targetPosX, targetPosY)) {
				r1.sensorTriggered();
				bounceCounter++;
				System.out.printf("%10d%10d%50s\n", moveCounter, newDirection, "sensor active");
			} else {
				robotPosX = targetPosX;
				robotPosY = targetPosY;
				moveCounter++;
				g1.setCut(robotPosX, robotPosY);
				System.out.printf("%10d%10d%10d%10d\n", moveCounter, newDirection, robotPosX, robotPosY);
			}
		}

		// output of result
		printResults(r1.getName(), g1.getName(), g1);
	}
}
