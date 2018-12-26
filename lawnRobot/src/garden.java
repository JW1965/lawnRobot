

public class garden {
	
	private char grassCharacter = '.';
	private char stationCharacter = 'S';
	private char cutCharacter = '.';
	private String gardenName = "";
	private int MAXSIZE = 50;
	
	private char[][] areaOrg = new char[MAXSIZE][MAXSIZE];
	//private int[][] areaCut = new int[MAXSIZE][MAXSIZE];

	public garden() {
		gardenName = "First garden";
	}
	
	public String getName() {
		return gardenName;
	}
	
	public boolean readGarden (String fname) {
		for (int i=0; i<MAXSIZE; i++) {
			for (int j=0; j<MAXSIZE; j++) {
				areaOrg[i][j] = '.';
			};
			System.out.println();
			
		}
		return true;
	}
	
	public boolean isStation (int posX, int posY) {
		return false;
	}
	
	public boolean isGrass (int posX, int posY) {
		return areaOrg [posX][posY] == grassCharacter;
	}
	
	public void setCut (int posX, int posY) {
		// void
	}
	
	public int getGrassArea () {
		return 10;
	}
	
	public int getCuttedArea () {
		return 4;
	}
	
	public void print() {
		for (int i=0; i<MAXSIZE; i++) {
			for (int j=0; j<MAXSIZE; j++) {
				System.out.printf ("%d", areaOrg[i][j]);
			};
			System.out.println();
			
		}
	}
	
	
}
