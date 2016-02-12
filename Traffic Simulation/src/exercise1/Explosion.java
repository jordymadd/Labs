package exercise1;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Explosion extends Actor {
	
	int counter = 0; 
	private static String[] explosionImgPath = {"images/new_explosion1.png", "images/new_explosion2.png",
			"images/new_explosion3.png", "images/new_explosion4.png"};
	
	
	public Explosion(){
		GreenfootImage image = new GreenfootImage(explosionImgPath[0]); 
		setImage(image);
		image.rotate(1);
	}
	
	public void explosion(){
		counter++; 
		if(counter > 60){
			setImage(explosionImgPath[1]); 
		}
		if(counter > 100){
			setImage(explosionImgPath[2]); 
		}
		if(counter > 150){
			setImage(explosionImgPath[3]); 
		}
		if(counter > 250){
			this.getWorld().removeObject(this); 
		}
		
	
		
		
	}
	
	public void act(){
		explosion(); 
	}
	

}
