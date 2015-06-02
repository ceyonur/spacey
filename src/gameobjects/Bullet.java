package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Timer;

import engines.GameEngine;
import utilities.Constants;
import utilities.Vector2D;


public class Bullet extends GameObject {	
	private String who;
	private long COUNTDOWN;	
	protected double damage;
	public Bullet(Vector2D s,Vector2D v,Vector2D d, String who,Color colour,double damage,double speed,int radius){		
		super(s,v,d,radius,colour);		
		dead = false;		
		if(!who.equals(PlayerShip.class.getName())){
		COUNTDOWN = 500;
		}
		else{
		COUNTDOWN = 100;
		}
		this.colour = colour;
		this.who = who;
		this.damage = damage;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		int x = (int) s.getX();
		int y = (int) s.getY();
		g.setColor(colour);
		Vector2D d = new Vector2D(v);
		d.normalise();
		int dx = (int) (d.getX()*radius);
		int dy = (int) (d.getY()*radius);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);		
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		COUNTDOWN--;
		if(COUNTDOWN == 0){
			dead();
		}
		if(s.getY()-radius <= 0 || s.getY()+radius >= Constants.FRAME_HEIGHT ){
			this.dead();
		}
	}

	@Override
	public void hit(GameObject other) {
		// TODO Auto-generated method stub
		if(!(this.getWho().equals(other.getClass().getName()))){
		dead();
		}
	}
	
	public String getWho(){
		return who;
	}
	
	@Override
	public void dead(){
		if(this.getWho().equals(PlayerShip.class.getName())){
			GameEngine.getInstance().player.ship.ShipBulletCount--;			
	}
		super.dead();		
	}



	
	

	
}
