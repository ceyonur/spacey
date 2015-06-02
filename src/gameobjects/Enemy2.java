package gameobjects;

import engines.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utilities.Constants;
import utilities.Vector2D;

public class Enemy2 extends Enemy {
	public Color color;
	

	public Enemy2(Vector2D s, int radius, Color colour, int health,
			int attackPower, int bulletSpeed) {
		super(s, new Vector2D(0, 0), new Vector2D(0, 1), radius, colour,
				health, attackPower, bulletSpeed);
		color = colour;
		
		// TODO Auto-generated constructor stub
	}

	public void update() {

		if ((Math.random() < 0.5 || s.getY() > Constants.FRAME_HEIGHT / 4)
				&& s.getY() - radius >= 0) {
			v.add(0, -1);
			
		} else {
			v.add(0, 1);
			
		}
		if (Math.random() < 0.01) {
			Bullet bullet1 = mkBullet(this.getClass().getName(), Color.RED);
			Bullet bullet2 = mkBullet(this.getClass().getName(), Color.RED);
			bullet1.s.add(-radius * 2 / 3, radius / 18);
			bullet2.s.add(+radius * 2 / 3, radius / 18);
			bullet1.update();
			bullet2.update();
			GameEngine.getInstance().objects.add(bullet1);
			GameEngine.getInstance().objects.add(bullet2);
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
		g.fillRect(-radius / 3, 0, -radius / 3, radius / 6);
		g.fillRect(radius / 3, 0, radius / 3, radius / 6);
		g.fillRect(-radius / 3 + -radius / 3, 0, -radius / 6, radius * 2 / 3);
		g.fillRect(+radius / 3 + radius / 3, 0, +radius / 6, radius * 2 / 3);
		g.fillOval(0 - radius / 3, 0 - radius / 3, 2 * radius / 3,
				2 * radius / 3);
		if (this.v.getY() > 0) {
			g.setColor(Color.red);
			int[] XPTHRUST = { -radius / 6, radius / 6, 0 };
			int[] YPTHRUST = { -radius / 3, -radius / 3, -1 * radius };
			g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
		}
		g.setTransform(at);

	}



}
