package exercise1;
import java.util.Random;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Car extends Actor implements TrafficLightSensor{

	private static String[] carImgPath = {"images/topCarBlue.png", "images/topCarPurple.png",
			"images/topCarYellow.png","images/topCarRed.png"};

	private Speed speed = Speed.REGULAR; 
	private Direction direction; 
	Random rand = new Random();
	private boolean turning=false; 
	private int targetY;
	private int targetX; 
	private int currentY;  
	private int currentX; 
	private Direction turnDirection; 
	private Intersection in; 
	private static GreenfootImage image = new GreenfootImage("images/explosion1.png");

	public Car(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[randNum]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}

	public int Random(int length){
		int randomNum = rand.nextInt(length); 
		return randomNum;
	}


	public void act(){
		Actor car = getOneIntersectingObject(Car.class); 
		currentY = this.getY(); 
		currentX = this.getX(); 
		if(speed==Speed.REGULAR){
			move(speed.getSpeed());
		}
		else if(speed==Speed.SLOW){
			move(speed.getSpeed());
		}
		else if(speed==Speed.STOP){
			move(speed.getSpeed());
		}
		if (turning==true){
			doTurn();
		}
		handleWrap();

		try{
			if (car != null) { //if car is not intersecting with another car. 
				throw new Exception(); 
			}
		}catch(Exception e){
			System.out.println("catch called");
			Greenfoot.playSound("sound/car_explosion.wav");
			Explosion explosion = new Explosion(); 
			getWorld().addObject(explosion, getX(), getY());
			getWorld().removeObject(car);
			getWorld().removeObject(this); 
		}finally {
			System.out.println("finally called");
		}
	}

	//this method places the car back to where it started once it reaches the edge. 
	private void handleWrap() {
		if(isAtEdge()){
			getWorld().removeObject(this);
			System.out.println("remove3");
			//			switch (this.getRotation()) {
			//			case D_WEST : setLocation(0, getY());
			//			break;
			//			case D_SOUTH : setLocation(getX(), 0);
			//			break;
			//			case D_EAST :setLocation(TrafficWorld.WIDTH, getY());
			//			break;
			//			case D_NORTH :setLocation(getX(), TrafficWorld.HEIGHT);
			//			break;
			//			}
		}
	}

	public enum Speed{
		STOP(0), 
		SLOW(1), 
		REGULAR(2);
		private int speed; 

		public int getSpeed(){
			return speed; 
		}

		private Speed(int speed){
			this.speed = speed; 
		}
	}

	public void doTurn(){
		if(direction.equals(Direction.EAST)){
			if(currentX > targetX){
				this.setLocation(targetX, getY());
				this.setRotation(turnDirection.getRotation());
				direction = turnDirection;
				turning = false;
			}
		}
		else if (direction.equals(Direction.WEST)){
			if(currentX < targetX){
				this.setLocation(targetX, getY());
				this.setRotation(turnDirection.getRotation());
				direction = turnDirection;
				turning = false;
			}
		}
		else if(direction.equals(Direction.SOUTH) ){
			if(currentY > targetY){
				this.setLocation(targetX, getY());
				this.setRotation(turnDirection.getRotation());
				direction = turnDirection;
				turning = false;
			}
		}
		else if(direction.equals(Direction.NORTH)){
			if(currentY < targetY){
				this.setLocation(targetX, getY());
				this.setRotation(turnDirection.getRotation());
				direction = turnDirection;
				turning = false;
			}
		}
	}

	private void turnLeft() {
		turning = true; 
		switch (this.direction) {
		case WEST : targetX = in.getX()-(TrafficWorld.WIDTH_OF_ROAD/4);
		turnDirection = Direction.SOUTH;
		break;
		case SOUTH : targetY = in.getY()+(TrafficWorld.WIDTH_OF_ROAD/4); 
		turnDirection = Direction.EAST;
		break;
		case EAST : targetX = in.getX()-(TrafficWorld.WIDTH_OF_ROAD/4);
		turnDirection = Direction.NORTH;
		break;
		case NORTH : targetY = in.getY()-(TrafficWorld.WIDTH_OF_ROAD/4); 
		turnDirection = Direction.WEST;
		break;
		}
	}



	private void turnRight() {
		turning = true; 
		switch (this.direction) {
		//this is a place holder variable for the y coordinates so when called the car can be repositioned correctly. currentY must be a variable that utilizes getCurrentY. 
		case WEST : targetX = in.getX()+(TrafficWorld.WIDTH_OF_ROAD/4); 
		//this is a place holder variable that will hold what the car direction should be. 
		turnDirection = Direction.NORTH;
		break;
		case SOUTH : targetY = in.getY()-(TrafficWorld.WIDTH_OF_ROAD/4); 
		turnDirection = Direction.WEST;
		break;
		case EAST : targetX = in.getX()-(TrafficWorld.WIDTH_OF_ROAD/4); 
		turnDirection = Direction.SOUTH;
		break;
		case NORTH : targetY = in.getY()+(TrafficWorld.WIDTH_OF_ROAD/4); 
		turnDirection = Direction.EAST;
		break;
		}
	}

	public void turnDetermine(){
		//this method will determine whether or not to turn or go straight (10% chance right, 10% left, 80% straight)
		int r = rand.nextInt(100);
		if(r >= 90){
			turnRight();
		}
		else if(r > 80 && r < 90){
			turnLeft();
		}
	}

	@Override
	public void nearIntersection(Intersection intersection) { 
		in =intersection; 

		if(turning==false){
			turnDetermine();
		}

		if (intersection.isGreen(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isYellow(direction)){
			speed =Speed.SLOW; 
		}
		else if (intersection.isRed(direction)){
			speed =Speed.SLOW; 
		}
	}

	@Override
	public void inInterSection(Intersection intersection) {

		if (intersection.isGreen(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isYellow(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isRed(direction)){
			speed =Speed.STOP; 
		}
	}

	@Override
	public void leavingIntersection(Intersection intersection) {
		if (intersection.isGreen(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isYellow(direction)){
			speed =Speed.REGULAR; 
		}
		else if (intersection.isRed(direction)){
			speed =Speed.REGULAR; 
		}
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

}
