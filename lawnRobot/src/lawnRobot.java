public class lawnRobot {

	
	static int lastDirection = 0;
	static int newDirection = 0;
	
	static int moveCounter = 0;
	static int rotationCounter = 0;
	static int bounceCounter = 0;
	
	int totalGrassArea = 0;
	int totalGrassCut = 0;
	
	static int stationPosX = 0;
	static int stationPosY = 0;
	
	static int robotPosX = 0;
	static int robotPosY = 0;
	
	
	
	public static int calcRotates (int lastDir, int newDir) {
		if (newDir == lastDir) {
			return 0;
		} else {
			return 4-(Math.abs((Math.abs(newDir-lastDir)/45)-4));
		}
		//return newDir == lastDir ? 0 : 4 - (Math.abs((Math.abs(newDir-lastDir)/45)-4));
	}
	
	public static int getTargetDirectionX (int dir) {   // just get taerget Posiion, not change og position itself
		if ((dir == 0) || (dir ==180)) {
			return 0;
		} else if ((dir == 45) || (dir == 90) || (dir == 135)) {
			return 1;
		} else {
			return -1;
		}
	}

	public static int getTargetDirectionY (int dir) {   // just get taerget Posiion, not change og position itself
		if ((dir == 90) || (dir ==270)) {
			return 0;
		} else if ((dir == 135) || (dir == 180) || (dir == 225)) {
			return +1;
		} else {
			return -1;
		}
	}
	
	public static void printResults (String robotName, String gardenName, garden g) {
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
	
	public static void main (String[] args) {
		
	
		System.out.println ("challenge started ....\n");

		// read garden
		garden g1 = new garden ();
		boolean succ = g1.readGarden ("abc");
				
		// create robot
		robot r1 = new robot ();
		
		/*
		for (int i = 1; i <= 100; i++) {
			System.out.printf (" " + r1.move());
		}
		System.out.println();
		*/
		
		
		// cutting
		
		while ( (! r1.isFinished()) && (! r1.cantMove ())) {
			newDirection = r1.move ();
			rotationCounter = rotationCounter + calcRotates (lastDirection, newDirection);
			
			int targetPosX = robotPosX +  getTargetDirectionX (newDirection);
			int targetPosY = robotPosY +  getTargetDirectionY (newDirection);
			
			if (! g1.isGrass(targetPosX, targetPosY)) {
				r1.sensorTriggered();
				bounceCounter++;
			} else {
				robotPosX = targetPosX;
				robotPosY = targetPosY;
				moveCounter++;
				g1.setCut(robotPosX, robotPosY);
				
				System.out.println (moveCounter + " " + robotPosX + " " + robotPosY);
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
		printResults (r1.getName(), g1.getName(), g1);
	}
}
