package gameobjects;

import engines.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;

import utilities.Vector2D;

public abstract class Ship extends GameObject{
	public int HP;
	public int AP;
	
	protected Color colour;
	protected int bulletSpeed;
	protected int bulletRadius;
	public double value;
	
	public Ship(Vector2D s, Vector2D v, Vector2D d, int radius,Color colour,int health, int attackPower,int bulletSpeed) {
		super(s, v,d ,radius,colour);		
		HP = health;
		AP = attackPower;
		this.bulletRadius = radius/8;
		this.bulletSpeed = bulletSpeed;
		value = (HP+AP+radius+bulletSpeed)/10;
		// TODO Auto-generated constructor stub
	}

	protected Bullet mkBullet(String who,Color colour) {
		Vector2D vb;
		if(v.getY() > 0){
			vb = new Vector2D(0,0);
		}
		else{
			vb = new Vector2D(0,v.getY());
		}
		Vector2D sb = new Vector2D(s);		   
		sb.add(d);
		vb.add(d,bulletSpeed);
		Bullet bullet = new Bullet(sb, vb,d,who,colour,AP,bulletSpeed,bulletRadius);
		return bullet; 
	}
	
	@Override
	public void hit(GameObject other) {
		// TODO Auto-generated method stub
		if (other.getClass() == Bullet.class) {
			if(!((Bullet) other).getWho()
				.equals(this.getClass().getName())){
				this.HP -= ((Bullet) other).damage;
			}
			
		}
		else{
			super.dead();
		}
		
		if(this.HP < 0){			
			if(other.getClass() == Bullet.class && ((Bullet)other).getWho().equals(PlayerShip.class.getName())){				
				GameEngine.getInstance().player.increaseScoreBy(value*10);				
			}
			super.dead();
		}
	}
	

}
