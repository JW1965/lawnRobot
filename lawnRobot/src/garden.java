import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 


public class garden {	
	private char grassCharacter = 'w';
	private char stationCharacter = 'S';
	private char cutCharacter = '"';
	private char obstacleCharacter = '#'; 
	private String gardenName = "";
	private int MAXSIZE = 100;
	private int maxXGrass = 0;
	private int maxYGrass = 0;
	private int stationPosX = 0;
	private int stationPosY = 0;
	private char[][] areaOrg = new char[MAXSIZE][MAXSIZE];
	private int[][] areaCut = new int[MAXSIZE][MAXSIZE];

	public garden() {
	}

	public String getName() {
		return gardenName;
	}
	
	public boolean readGarden(String fname) {
		boolean result = true;
		
		gardenName = fname;
        File file = new File(fname);

        String lineString = "";

        try {
	        Scanner scanner = new Scanner(file);
	
	        int lineNo = 0;
	        while (scanner.hasNextLine()) {
	               lineString = scanner.nextLine();
               
	               for (int i=0; i<lineString.length(); i++) {
	            	   areaOrg [lineNo][i] = lineString.charAt(i);
	               }
	               lineNo++;
	        }
			
			maxXGrass = 0;
			maxYGrass = 0;
			for (int i = 0; i < MAXSIZE; i++) {
				for (int j = 0; j < MAXSIZE; j++) {
					switch (areaOrg[i][j]) {
						case 'w': 	areaCut [i][j] = 0;
									if (i>maxYGrass) {maxYGrass = i; };
									if (j>maxXGrass) {maxXGrass = j; };
									break;
						case '"':	areaCut [i][j] = 1;
									if (i>maxYGrass) {maxYGrass = i; };
									if (j>maxXGrass) {maxXGrass = j; };
									break;
						case 'S':	areaCut [i][j] = -1;
									stationPosX = i;
									stationPosY = j;
									break;
						case '#':	areaCut [i][j] = -2;
									break;
						default:	areaCut [i][j] = -2;
									break;
					}
				}
			}
	        scanner.close ();
        } catch (FileNotFoundException e) {
	        result = false;
	    } 
		return result;
	}
	
	public boolean isStation(int posX, int posY) {
		return areaOrg[posX][posY] == stationCharacter;
	}
	public boolean isGrass(int posX, int posY) {
		return areaOrg[posX][posY] == grassCharacter;
	}
	public void setCut(int posX, int posY) {
		areaCut[posX][posY]++; 
	}
	public int getGrassArea() {
		int cnt = 0;
		for (int  i=0; i < maxYGrass+2; i++) {
			for (int j= 0; j < maxXGrass+2; j++) {
				if (areaCut[i][j] >=0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	public int getCuttedArea() {
		int cnt = 0;
		for (int  i=0; i < maxYGrass+2; i++) {
			for (int j= 0; j < maxXGrass+2; j++) {
				if (areaCut[i][j] >0) {
					cnt++;
				}
			}
		}
		return cnt;
	}	
	
	public int getStationPosX () {
		return stationPosX;
	}
	
	public int getStationPosY () {
		return stationPosY;
	}
	
	public void printOrg() {
		for (int  i=0; i < maxYGrass+2; i++) {
			for (int j= 0; j < maxXGrass+2; j++) {
				System.out.printf("%c", areaOrg[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printCut() {
		for (int  i= 0; i < maxYGrass+2; i++) {
			for (int j=  0; j < maxXGrass+8; j++) {
				if (areaCut[i][j] == 0) { 
					System.out.print ('w');
				} else if (areaCut[i][j] > 0) { 
					System.out.print ('"');
				} else if (areaCut[i][j] == -1) { 
					System.out.print ('S');
				} else if (areaCut[i][j] == -2) { 
					System.out.print ('#');
				} else {
					System.out.print ('*');
				}
			}
			System.out.println();
		}
	}
	
	public void printHeatmap() {
		for (int  i= 0; i < maxYGrass+2; i++) {
			for (int j=  0; j < maxXGrass+8; j++) {
				//System.out.printf("%d", areaCut[i][j]);
				if (areaCut[i][j] == 0) { 
					System.out.print (0);
				} else if (areaCut[i][j] > 0) { 
					System.out.print ((areaCut[i][j] < 10) ? areaCut[i][j] : 9);
				} else if (areaCut[i][j] == -1) { 
					System.out.print ('S');
				} else if (areaCut[i][j] == -2) { 
					System.out.print ('#');
				} else {
					System.out.print ('*');
				}
			}
			System.out.println();
		}
	}
}