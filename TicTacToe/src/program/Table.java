package program;

public class Table {
	private static final int columns = 3;
	private static final int rows = 3; 
	private char[][] table = new char [3][3]; 




	public Table (){
		for(int i= 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++)
				table[i][j]= ' '; 
		}

	}

	public boolean placeMark(int xCord, int yCord, char placeMarker){
		boolean state = false; 
		if (table[xCord][yCord] == ' '){
			table[xCord][yCord] = placeMarker;
			state = true; 
		} 

		return state; 
	}

	public boolean checkIfEmpty(char box1, char box2, char box3){
		return ((box1 != ' ') && (box1 == box2) && (box2 == box3)); 
	}

	public boolean checkForWin(){
		for (int i = 0; i < 3; i++) {
			if (checkIfEmpty(table[0][i], table[1][i], table[2][i]) || checkIfEmpty(table[i][0], table[i][1], table[i][2]) ||
					checkIfEmpty(table[0][0], table[1][1], table[2][2]) || (checkIfEmpty(table[0][2], table[1][1], table[2][0]))){

				return true;
			}
		}
		return false; 
	}


		public boolean checkForTie (){
			for(int i= 0; i < table.length; i++){
				for(int j = 0; j < table[i].length; j++)
					if (table[i][j]== ' '){
						return false; 
					}
			}
			return true; 
	
		}
	

	//Haliday suggested this but it broke my code. 
	//	public boolean checkForTie (){
//		boolean emptyFound = false;
//		for(int i= 0; !emptyFound && (i < table.length); i++){
//			for(int j = 0; !emptyFound && (j < table[i].length); j++){
//				emptyFound = (table[i][j]== ' ');
//			}
//		}
//		return emptyFound; 
//	}





@Override
public String toString() {
	String tableGen=""; 
	tableGen += " " + table[0][0] + " | " + table[0][1] + " | " + table[0][2] + "\n"+
			" --+---+--\n"; 
	tableGen += " " + table[1][0] + " | " + table[1][1] + " | " + table[1][2] + "\n"+
			" --+---+--\n";
	tableGen += " " + table[2][0] + " | " + table[2][1] + " | " + table[2][2] + "\n";

	return tableGen; 
}


}





