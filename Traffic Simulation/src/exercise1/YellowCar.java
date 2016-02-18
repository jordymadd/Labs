package exercise1;

import greenfoot.GreenfootImage;

public class YellowCar extends Car{
	
	public YellowCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[2]); 
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
		//this method will determine whether or not to turn or go straight (25% chance right only), 75% straight)
		int r = rand.nextInt(100);
		if(r >= 75){
			turnRight();
		}

	}
}
