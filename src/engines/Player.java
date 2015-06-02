package engines;

import gameobjects.PlayerShip;

public class Player {
	
	public PlayerShip ship;
	private double score;	

	
	
	public Player(){		
		ship = new PlayerShip(10, 10);
		score = 0;				
	}	
	
	
	public void increaseScoreBy(double amount){
		score += amount;
	}
	
	public double getScore(){
		return score;
	}
	

	
	

	
	

}
