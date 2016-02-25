package exercise1;

import exercise1.Car.Speed;
import greenfoot.GreenfootImage;

public class BlueCar extends Car{
	
	protected static int blueIntsPassed = 0; 
	
	public BlueCar(){
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[0]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);
	}

	public void turnDetermine(){
		if(isTurningRight){
			turnRight();
			isTurningRight =false; 
		}
		else{
			turnLeft(); 
			isTurningRight =true; 
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
			speed =Speed.REGULAR; 
		}
		blueIntsPassed ++; 
	}

	@Override
	public String toString() {
		String output = "" + blueIntsPassed  + "\t\t\t\t"; 
		return output; 
	}

}
