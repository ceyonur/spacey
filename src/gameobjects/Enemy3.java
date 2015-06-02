package gameobjects;

import engines.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import utilities.Constants;
import utilities.Vector2D;

public class Enemy3 extends Enemy {
	public Color color;

	public Enemy3(Vector2D s, int radius, Color colour, int health,
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
		if (Math.random() < 0.1) {
			d.rotate(0.1);
		}
		if (Math.random() < 0.01) {
			Bullet bullet1 = mkBullet(this.getClass().getName(), Color.RED);
			Bullet bullet2 = mkBullet(this.getClass().getName(), Color.RED);
			bullet1.s.add(d, radius * 2 / 3);
			bullet1.update();
			GameEngine.getInstance().objects.add(bullet1);
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
		double rot = (d.theta() - 90) / 60;
		g.rotate(rot);
		g.setColor(color);
		int[] xPoints = { radius / 3 * 86 / 100, radius / 3, radius };
		int[] yPoints = { radius / 6, 0, radius / 3 };
		int[] xPoints2 = { -radius / 3 * 86 / 100, -radius / 3, -radius };
		g.fillPolygon(xPoints, yPoints, xPoints.length);
		g.fillPolygon(xPoints2, yPoints, xPoints2.length);
		g.fillRect(-radius / 18, radius / 3, radius / 9, radius - radius / 3);
		g.fillOval(0 - radius / 3, 0 - radius / 3, 2 * radius / 3,
				2 * radius / 3);

		g.setTransform(at);

	}



}
