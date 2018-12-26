public class lawnRobot {
	private static int lastDirection = 0;
	private static int newDirection = 0;	
	private static int moveCounter = 0;
	private static int rotationCounter = 0;
	private static int bounceCounter = 0;	
	private static int stationPosX = 0;
	private static int stationPosY = 0;
	private static int robotPosX = 5;
	private static int robotPosY = 5;
	private int totalGrassArea = 0;
	private int totalGrassCut = 0;	
			
	public static int calcRotates(int lastDir, int newDir) {
		return newDir == lastDir ? 0 : 4 - (Math.abs((Math.abs(newDir-lastDir)/45)-4));
		/* Falls bugs:
		if (newDir == lastDir) {
			return 0;
		} else {
			return 4-(Math.abs((Math.abs(newDir-lastDir)/45)-4));
		}
		 */
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
		/*
		 *  output Move-Counter
		 *  output rotation counter
		 *  output Bounce-Counter
		 *  output if robot is back in station
		 *  absolute values and percantage of cut/uncut grass 
		 *  coloured output of lawn map (green is cut, red is uncut)
		 */
		
		System.out.println("=================================================================================================================================================");
		System.out.println("Challenge:       Robot " +  robotName + "        Garden: " + gardenName);
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("# moves: " + moveCounter);
		System.out.println("# rotations: " + rotationCounter);
		System.out.println("# bounces: " + bounceCounter);
		System.out.println("# cut grass/total grass: " + g.getCuttedArea() + "/" + g.getGrassArea() + "      " + g.getCuttedArea() *100 / g.getGrassArea() + " %");
		System.out.println("robot completed mission back in station: " + g.isStation (robotPosX, robotPosY));
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		g.print();
		System.out.println("=================================================================================================================================================");
		
	}
	
	public static void main(String[] args) {
		System.out.println("Challenge started ....\n");
		// read garden
		garden g1 = new garden();
		boolean succ = g1.readGarden("abc");			
		// create robot
		robot r1 = new robot();
		/*
		for (int i = 1; i <= 100; i++) {
			System.out.printf (" " + r1.move());
		}
		System.out.println();
		*/
		// cutting		
		while ((!r1.isFinished()) && (!r1.cantMove())) {
			newDirection = r1.move();
			rotationCounter = rotationCounter + calcRotates(lastDirection, newDirection);		
			int targetPosX = robotPosX + getTargetDirectionX(newDirection), targetPosY = robotPosY + getTargetDirectionY(newDirection);
			if (!g1.isGrass(targetPosX, targetPosY)) {
				r1.sensorTriggered();
				bounceCounter++;
			} else {
				robotPosX = targetPosX;
				robotPosY = targetPosY;
				moveCounter++;
				g1.setCut(robotPosX, robotPosY);
				System.out.println(moveCounter + " " + robotPosX + " " + robotPosY);
			}
		}
		/*

		 * 
		 * 		wenn neue Position auf einem Hindernis, dann
		 * 			robot.sencor triggered
		 * 			erhöhe Bounce-Counter
		 * 		sonst
		 * 			setze neue Posoition des Robots
		 * 			moveCounter++;		
		 * 			kennzeichne Rasenfläche als gemäht
		 * 		end
		 * end
		 * 				
		 */	
		// output of result
		printResults(r1.getName(), g1.getName(), g1);
	}
}
