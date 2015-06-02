package gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import engines.GameEngine;
import utilities.Action;
import utilities.Constants;
import utilities.Vector2D;

public class Enemy1 extends Enemy {
	public Color color;

	public Enemy1(Vector2D s, int radius, Color colour, int health,
			int attackPower, int bulletSpeed) {
		super(s, new Vector2D(0, 0), new Vector2D(0, 1), radius, colour,
				health, attackPower, bulletSpeed);
		color = colour;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		if (Math.random() < 0.01) {
			Bullet bullet = mkBullet(this.getClass().getName(), Color.RED);
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
		int[] XP1 = { radius * 1 / 2, radius * 1, radius * 1 };
		int[] YP1 = { 0, -radius * 2 / 3, radius * 2 / 3 };
		int[] XP2 = { -radius * 1 / 2, -radius * 1, -radius * 1 };
		int[] YP2 = { 0, -radius * 2 / 3, radius * 2 / 3 };
		g.setColor(color);
		g.drawLine(radius / 3, 0, radius * 1 / 2, 0);
		g.drawLine(-radius / 3, 0, -radius * 1 / 2, 0);
		g.drawLine(0, radius / 3, 0, radius / 3 * 2);
		g.fillOval(0 - radius / 3, 0 - radius / 3, 2 * radius / 3,
				2 * radius / 3);
		g.fillPolygon(XP1, YP1, XP1.length);
		g.fillPolygon(XP2, YP2, XP2.length);
		g.setTransform(at);

	}

	

}
