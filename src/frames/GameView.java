package frames;

import engines.GameEngine;
import gameobjects.GameObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import utilities.Constants;
import utilities.Vector2D;

public class GameView extends JComponent {
	// background colour
	public static final Color BG_COLOR = Color.black;

	private ArrayList<Vector2D> starsPos;

	public GameView() {
		
		starsPos = new ArrayList<Vector2D>();
		for (int i = 0; i < Math.random() * 1000; i++) {
			int x = (int) (Math.random() * 1000 % Constants.FRAME_WIDTH);
			int y = (int) (Math.random() * 1000 % Constants.FRAME_HEIGHT);
			starsPos.add(new Vector2D(x, y));
		}
	}

	@Override
	public void paintComponent(Graphics g0) {
		Graphics2D g = (Graphics2D) g0;
		// paint the background
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (Vector2D pos : starsPos) {
			if (Math.random() < 0.3) {
				g.setColor(Color.WHITE);
			}
			g.fillOval((int) pos.getX(), (int) pos.getY(),
					(int) pos.getX() % 5, (int) pos.getX() % 5);
		}
		g.setColor(Color.WHITE);
		g.drawString(("Score: " + (Double) GameEngine.getInstance().player
				.getScore()).toString(), Constants.FRAME_WIDTH - 100,
				Constants.FRAME_HEIGHT - 20);
		if (GameEngine.getInstance().player.ship.HP > 0) {
			g.drawString("Shield: " + GameEngine.getInstance().player.ship.HP,
					Constants.FRAME_WIDTH - 100, Constants.FRAME_HEIGHT - 10);
		} else {
			g.drawString("Shield: Down!",
					Constants.FRAME_WIDTH - 100, Constants.FRAME_HEIGHT - 10);

		}
		synchronized (GameEngine.class) {
			for (GameObject object : GameEngine.getInstance().objects) {
				object.draw(g);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return Constants.FRAME_SIZE;
		
	}
	
	public void lostScreen(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(100, 100, 500, 500);
	}

}
