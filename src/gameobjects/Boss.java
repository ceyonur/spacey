package gameobjects;

import engines.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import utilities.Constants;
import utilities.Vector2D;

public class Boss extends Enemy{
	public Color color;

	public Boss(Vector2D s, int radius, Color colour, int health,
			int attackPower, int bulletSpeed) {
		super(s, new Vector2D(0, 0), new Vector2D(0, 1), radius, colour,
				health, attackPower, bulletSpeed);
		color = colour;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		Random rgen = new Random();
		double x = rgen.nextInt(1 - -1 + 1) + -1;
		double y = rgen.nextInt(1 - -1 + 1) + -1;		
 		v.add(x,y);
		if ((Math.random() < 0.3 || s.getY() > Constants.FRAME_HEIGHT / 4)
				&& s.getY() - radius >= 0) {
			v.add(x, -1);
			
		} else {
			v.add(x, 1);
			
		}
		if (Math.random() < 0.05) {
			Bullet bullet = mkBullet(this.getClass().getName(), Color.GREEN);
			bullet.d.add(GameEngine.getInstance().player.ship.s);
			bullet.d.add(-s.getX(),-s.getY());
			bullet.update();
			GameEngine.getInstance().objects.add(bullet);
		}
		super.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform at = g.getTransform();
		g.translate(s.getX(), s.getY());
		int SCALE = 1;
		g.scale(SCALE, SCALE);			
		g.setColor(color);
		g.fillOval(0-radius, 0-radius, 2*radius, 2*radius);
		g.setColor(Color.BLACK);
		g.fillOval(0-radius*2/3, 0-radius*2/3, radius/2, radius/2);
		g.drawLine(radius, 0, -radius, 0);		
		g.setTransform(at);
	}	

}
