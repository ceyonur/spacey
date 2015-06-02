package gameobjects;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import engines.GameEngine;
import utilities.Action;
import utilities.Constants;
import utilities.Vector2D;

public class PlayerShip extends Ship {
	public static final int radius = 24;
	public static final double STEER_RATE = 2 * Math.PI; // in radians per second	

	// magnitude of acceleration when thrust is applied
	public static final double MAG_ACC = 200;

	// constant speed loss factor
	public static final double LOSS = 0.01;
	
	public  int ShipBulletCount;

	

	// direction in which ship is turning
	// this is a "unit" vector (so magnitude is 1)
	public PlayerShip(int HP, int AP) {
		super(new Vector2D(Constants.FRAME_WIDTH / 2,Constants.FRAME_HEIGHT - (Constants.FRAME_HEIGHT / 8)), new Vector2D(0, 0), new Vector2D(0,-1),radius, Color.GRAY, HP, AP,500);
		ShipBulletCount = 0;
	}
	

	public void reset() {
		s = new Vector2D(Constants.FRAME_WIDTH / 2,Constants.FRAME_HEIGHT - (Constants.FRAME_HEIGHT / 8));
		v = new Vector2D(0, 0);
		d = new Vector2D(0,-1);	
		ShipBulletCount = 0;

	}

	public void update() {
		Action action = GameEngine.getInstance().ctrl.action;
		v.add(MAG_ACC * Constants.DT * action.left_right, MAG_ACC
				* Constants.DT * action.thrust);
		v.add(v, -LOSS);
		if (action.shoot && ShipBulletCount < 5) {
			Bullet bullet = mkBullet(this.getClass().getName(),Color.green);			
			bullet.update();
			GameEngine.getInstance().objects.add(bullet);
			ShipBulletCount++;
			action.shoot = false;
		}
		super.update();
	}	



	public void draw(Graphics2D g) {		
		AffineTransform at = g.getTransform();
		g.translate(s.getX(), s.getY());
		int SCALE = 1;
		g.scale(SCALE, SCALE);
		double rot = (0);		
		g.rotate(rot);
		int[] XP1 = { radius * 1 / 2, radius * 1, radius * 1 };
		int[] YP1 = { 0, -radius * 2 / 3, radius * 2 / 3 };
		int[] XP2 = { -radius * 1 / 2, -radius * 1, -radius * 1 };
		int[] YP2 = { 0, -radius * 2 / 3, radius * 2 / 3 };
		g.setColor(Color.GRAY);		
		g.drawLine(radius / 3, 0, radius * 1 / 2, 0);
		g.drawLine(-radius / 3, 0, -radius * 1 / 2, 0);
		g.drawLine(0, -radius / 3, 0, -radius / 3 * 2);
		g.fillOval(0 - radius / 3, 0 - radius / 3, 2 * radius / 3,
				2 * radius / 3);
		g.fillPolygon(XP1, YP1, XP1.length);
		g.fillPolygon(XP2, YP2, XP2.length);
		if (GameEngine.getInstance().ctrl.action().thrust < 0) {
			g.setColor(Color.red);
			int[] XPTHRUST = { -radius / 6, radius / 6, 0 };
			int[] YPTHRUST = { radius / 3, radius / 3, 1 * radius };
			g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
		} else if (GameEngine.getInstance().ctrl.action().thrust > 0) {
			g.setColor(Color.red);
			int[] XPTHRUST = { -radius / 6, radius / 6, 0 };
			int[] YPTHRUST = { -radius / 3, -radius / 3, -1 * radius };
			g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
		}	
		g.setColor(Color.BLUE);
		g.drawOval(0-radius*(HP/5), 0-radius*(HP/5), radius*2*(HP/5), radius*2*(HP/5));

		g.setTransform(at);
	}
	

	@Override
	public void dead() {
		// TODO Auto-generated method stub
		GameEngine.getInstance().lost();
		super.dead();
	}


}
