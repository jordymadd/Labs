package exercise1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;


public class TrafficWorld extends World {
	private String blueCar = "images/topCarBlue.png"; 
	private String purpleCar = "images/topCarPurple.png"; 
	private String yellowCar = "images/topCarYellow.png"; 
	private String redCar = "images/topCarRed.png"; 
	private static final int BLUE = 3; 
	private static final int PURPLE = 2; 
	private static final int RED = 1; 
	private static final int YELLOW = 0; 
	public static final int WIDTH = 1000; 
	public static final int HEIGHT = 750;
	private static final int CELL_SIZE = 1;
	public static int WIDTH_OF_ROAD = 50;
	//variables for vertical roads
	private static final int NUM_OF_VROADS = 7;
	private static final int NUM_OF_VROAD_SPACES = NUM_OF_VROADS-1;
	private static int VERTICAL_WHITESPACE =(WIDTH -(WIDTH_OF_ROAD*NUM_OF_VROADS))/NUM_OF_VROAD_SPACES; 
	//variables for horizontal roads
	private static final int NUM_OF_HROADS = 5;
	private static final int NUM_OF_HROAD_SPACES = NUM_OF_HROADS-1;
	private static final int HORIZONTAL_WHITESPACE = (HEIGHT -(WIDTH_OF_ROAD*NUM_OF_HROADS))/NUM_OF_HROAD_SPACES; 
	ReadAndWrite readAndWriter = new ReadAndWrite();

	Roads[] roadArrayHoriz = new Roads[NUM_OF_HROADS]; 
	Roads[] roadArrayVert = new Roads[NUM_OF_VROADS];
	Intersection[] intersectArray = new Intersection[NUM_OF_HROADS*NUM_OF_VROADS]; 
	Intersection[] intersectArrayVert = new Intersection[NUM_OF_VROADS]; 
	Intersection[] nearIntersectArray = new Intersection[NUM_OF_HROADS];
	private int gameOverCounter = 0; 
	private int carCounter = 0; 
	private int gameOverBound = 2500; 
	private int carGenBound=60;
	private String intersectionStats; 
	private int simulationCounter = 0; 
	public int simulationTime=3600; //1min 


	//act method to be used to generate all cars in TF world
	public void act(){ 
		simulationCounter++; 
		gameOverCounter ++; 
		carCounter++; 
		//array of different cars
		Car carGen[] = new Car[4];
		carGen[YELLOW] = new YellowCar(readAndWriter.getDriveStyleYellow()); 
		carGen[RED] = new RedCar(readAndWriter.getDriveStyleRed()); 
		carGen[PURPLE] = new PurpleCar(readAndWriter.getDriveStylePurple()); 
		carGen[BLUE] = new BlueCar(readAndWriter.getDriveStyleBlue()); 


		//time simulation will run 
		if(simulationCounter==simulationTime){
			Greenfoot.stop();
		}

		if (carCounter == carGenBound){
			Random rand = new Random(); 

			int rNum = rand.nextInt(4); 
			int randHoriz = rand.nextInt(NUM_OF_HROADS); 
			int randVert = rand.nextInt(NUM_OF_VROADS); 

			switch (carGen[rNum].getDirection()) {
			case NORTH: this.addObject(carGen[rNum], roadArrayVert[randVert].getX()+(WIDTH_OF_ROAD/4), HEIGHT);
			break;
			case SOUTH: this.addObject(carGen[rNum], roadArrayVert[randVert].getX()-(WIDTH_OF_ROAD/4), 0);
			break;
			case EAST: this.addObject(carGen[rNum], 0, roadArrayHoriz[randHoriz].getY()+(WIDTH_OF_ROAD/4));
			break;
			case WEST: this.addObject(carGen[rNum], WIDTH, roadArrayHoriz[randHoriz].getY()-(WIDTH_OF_ROAD/4));
			break;
			}
			carCounter = 0; 
		}

		//after the counter reaches the counter limit a statistics screen will appear

		if (gameOverCounter == gameOverBound){
			//for loop that will print out all of the intersection array stats. 
			for(int i = 0; i < intersectArray.length; i++){
				intersectionStats += "\n"+intersectArray[i];
			}
			GameOverScreen carStats = new GameOverScreen("SIMULATION OVER\n\n" + "Blocks Traveled: " + "\nBlue\t " + "Purple\t "+ "Red\t " + "Yellow\t\n" 
					+ carGen[BLUE] + carGen[PURPLE] + carGen[RED] + carGen[YELLOW]); 
			carStats.msgTxt += intersectionStats; 
			addObject(carStats, getWidth() / 2, getHeight() / 2);
			//		    Greenfoot.stop(); // this will stop greenfoot but you can press play again without having to reset (currently commented out) . 
			return;
		}
	}

	public TrafficWorld(){
		super(WIDTH, HEIGHT, CELL_SIZE); 
		readAndWriter.read();
		simulationTime = readAndWriter.getSimTime();
		carGenBound = readAndWriter.getCarGenInput(); 
		
	
		//		Swing simulationSetup = new Swing("title of something");
		//		simulationSetup
		setPaintOrder(this.getClass(), Car.class); 
		GreenfootImage background = this.getBackground(); 
		background.setColor(Color.GREEN);
		background.fill();

		//horizontal roads (5) 
		for (int i = 0; i < NUM_OF_HROADS; i++){
			roadArrayHoriz[i] = new Roads(WIDTH, WIDTH_OF_ROAD); 
			this.addObject(roadArrayHoriz[i], WIDTH/2, (HORIZONTAL_WHITESPACE + WIDTH_OF_ROAD)*i + WIDTH_OF_ROAD/2); 
		}

		//vertical roads (7)
		for (int i = 0; i < NUM_OF_VROADS; i++){
			roadArrayVert[i] = new Roads(WIDTH_OF_ROAD, HEIGHT); 
			this.addObject(roadArrayVert[i], (VERTICAL_WHITESPACE + WIDTH_OF_ROAD+1)*i + WIDTH_OF_ROAD/2, HEIGHT/2);		
		}
		
		//intersection generator
		for(int horizRoadNo = 0; horizRoadNo < roadArrayHoriz.length; horizRoadNo++){
			for(int vertRoadNo = 0; vertRoadNo < roadArrayVert.length; vertRoadNo++){
				int interNo = (horizRoadNo*NUM_OF_VROADS)+vertRoadNo;
				intersectArray[interNo] = new Intersection(); 
				this.addObject(intersectArray[interNo], roadArrayVert[vertRoadNo].getX(), roadArrayHoriz[horizRoadNo].getY());
				for(TrafficLight tf: intersectArray[interNo].getTrafficLight())
				{
					int xPosition=0; int yPosition=0; 
					switch (tf.getDirection()) {
					case NORTH:
						xPosition = intersectArray[interNo].getX(); 
						yPosition = intersectArray[interNo].getY()+(intersectArray[interNo].getImage().getHeight())/2+(tf.getImage().getHeight()/2); 
						break;
					case SOUTH:
						xPosition = intersectArray[interNo].getX(); 
						yPosition = intersectArray[interNo].getY()-(intersectArray[interNo].getImage().getHeight())/2-(tf.getImage().getHeight()/2); 
						break;
					case EAST:
						xPosition = intersectArray[interNo].getX()-(intersectArray[interNo].getImage().getHeight())/2-(tf.getImage().getHeight()/2);
						yPosition = intersectArray[interNo].getY();  
						break;
					case WEST:
						xPosition = intersectArray[interNo].getX()+(intersectArray[interNo].getImage().getHeight())/2+(tf.getImage().getHeight()/2);  
						yPosition = intersectArray[interNo].getY(); 
						List<Integer> numbers = new ArrayList<Integer>();
						break;

					}
					tf.setRotation(tf.getDirection().getRotation());
					this.addObject(tf, xPosition, yPosition); 
				}

			}
		}

	}





}
