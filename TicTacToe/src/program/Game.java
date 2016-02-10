package program;

import consoleInterface.ConsoleUI;

public class Game {
	
	static ConsoleUI console = new ConsoleUI(); 
	private static int xCord;
	private static int yCord; 
	private static char placeMarker=' '; 
	private static boolean P1Turn = true; 
	private static int playerCounter; 
	private static boolean keepPlaying = true; 
	private static boolean playAgain = true;
	private static boolean retry = true;
	
	private static void run() {
		
		while (playAgain){
			
			keepPlaying = true; 
		
	Placemarker pMarker_o = Placemarker.MARKER_0;
	Placemarker pMarker_x = Placemarker.MARKER_X; 
	placeMarker = pMarker_x.getMark(); 
	P1Turn = true; 
	

	System.out.println("Welcome to TicTacToe!\n");
	
	Table table = new Table(); 
	
	while (keepPlaying){
		
		
		// I used the turinary operator here. It seems like the ? means if and the : means else 
		playerCounter = P1Turn?1:2; 
	
	System.out.println("It's Your turn " + "Player " + playerCounter + "\n" + table.toString());
	
	//while stmt in case they enter cords that have already been entered
	retry = true;
	 
	
	while (retry){
		xCord = console.promptForInt("Enter your X coordinate move 0-2", 0, 2); 
		yCord = console.promptForInt("Enter your Y coordinate move 0-2", 0, 2); 
		
		
		if (P1Turn&&table.placeMark(xCord, yCord, placeMarker)==true){
			placeMarker = pMarker_o.getMark();
			P1Turn=false; 
			retry=false; 
		}
		else if (!P1Turn&&table.placeMark(xCord, yCord, placeMarker)==true) {
			placeMarker =  pMarker_x.getMark();  
			P1Turn=true; 
			retry = false;}
			
		else 
			System.out.println("That spot is already taken. Please renter.\n");
		    break; 
		
		
	}
	
	
	keepPlaying=!table.checkForWin() && !table.checkForTie(); 

	}
	
	if(table.checkForWin()){
		System.out.println("\n" + table.toString() + "\n" + "You Win!");
	}
	else{ System.out.println("It is a tie");
		
	}
	
	playAgain = console.promptForBool("Do You Want to Play Again? Type Y or N", "y", "n"); 

	
		}
	}

	public static void main(String[] args) {
		Game.run(); 

	}

	
}
