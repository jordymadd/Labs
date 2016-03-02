package exercise1;

import exercise1.Car.Speed;
import greenfoot.GreenfootImage;

public class BlueCar extends Car{

	protected static int blueIntsPassed = 0; 
	private static int drivingStyle; 

	public BlueCar(int drivingStyle){
		this.drivingStyle = drivingStyle; 
		int randNum = Random(4); 
		GreenfootImage image = new GreenfootImage(carImgPath[0]); 
		Direction[] directions = Direction.values();
		this.direction = directions[randNum]; 
		setRotation(direction.getRotation());
		this.setImage(image);

	}

	public void turnDetermine(){
		//this will make the car alternate between left and right if driving style == 1
		if(drivingStyle==1){
			if(isTurningRight){
				turnRight();
				isTurningRight =false; 
			}
			else{
				turnLeft(); 
				isTurningRight =true; 
			}
		}
		else if(drivingStyle==2){
			turnRight();
		}
		else if(drivingStyle==3){
			turnLeft();
		}
		else if(drivingStyle==4){
			//left intentionally blank
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
