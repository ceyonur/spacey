package gameobjects;

import java.awt.Color;

import utilities.Vector2D;

public abstract class Enemy extends Ship{

	public Enemy(Vector2D s, Vector2D v, Vector2D d, int radius, Color colour,
			int health, int attackPower, int bulletSpeed) {
		super(s, v, d, radius, colour, health, attackPower, bulletSpeed);
		// TODO Auto-generated constructor stub
	}

}
