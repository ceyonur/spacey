package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import utilities.Constants;
import utilities.Vector2D;

public abstract class GameObject {
	public Vector2D s;
	public Vector2D v;
	public Vector2D d;
	public boolean dead;
	public int radius;
	public Color colour;

	public GameObject(Vector2D s, Vector2D v,Vector2D d, int radius, Color colour) {
		this.s = s;
		this.v = v;
		this.d = d;
		this.radius = radius;
		this.dead = false;
		this.colour = colour;
	}

	public abstract void hit(GameObject other);

	public void update() {
		s.add(v, Constants.DT);		
		s.wrapWidth(Constants.FRAME_HEIGHT,Constants.FRAME_WIDTH,radius);
		d.normalise();
		
	}

	public boolean overlap(GameObject other) {
		if ((this.radius + other.radius) >= this.s.dist(other.s)) {				
			return true;			
		}

		return false;
	}

	public void collisionHandling(GameObject other) {
		if (this.getClass() != other.getClass() && this.overlap(other)) {
			this.hit(other);
			other.hit(this);
		}
		
	}

	public abstract void draw(Graphics2D g);		

	public void dead(){
		this.dead = true;
	}
		
		
	

}
