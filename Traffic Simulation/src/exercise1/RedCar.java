package exercise1;

import greenfoot.GreenfootImage;

public class RedCar extends Car {
	
	public RedCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[3]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}
	
	protected void handleWrap() {
		if(isAtEdge()){
			switch (this.getRotation()) {
			case 110 : setLocation(0, getY()); //west
			break;
			case 90 : setLocation(getX(), 0); //south
			break;
			case 0 :setLocation(TrafficWorld.WIDTH, getY()); //east
			break;
			case 270 :setLocation(getX(), TrafficWorld.HEIGHT); //north
			break;
			}
		}
	}
	
	public void turnDetermine(){
		//I think if the turnDetermine() is blank then that should let the car just go straight
		
		//this method will determine whether or not to turn or go straight (10% chance right, 10% left, 80% straight)
//		int r = rand.nextInt(100);
//		if(r >= 90){
//			turnRight();
//		}
//		else if(r > 80 && r < 90){
//			turnLeft();
//		}
	}

//	public RedCar() {
//		// TODO Auto-generated constructor stub
//	}

}
