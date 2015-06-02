package engines;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import utilities.Action;

public class BasicKeys extends KeyAdapter implements Controller  {
	  public Action action;
	  public BasicKeys() {
	    action = new Action();
	  }

	  public Action action() {
	    // this is defined to comply with the standard interface
	    return action;
	  }

	public void keyPressed(KeyEvent e) {
	    int key = e.getKeyCode();
	    switch (key) {
	    case KeyEvent.VK_UP:
	      action.thrust = -1;
	      break;
	    case KeyEvent.VK_LEFT:	      
	      action.left_right = -1;
	      break;
	    case KeyEvent.VK_RIGHT:
	      action.left_right = +1;
	      break;
	    case KeyEvent.VK_SPACE:
	      action.shoot = true;
	      break;
	    case KeyEvent.VK_DOWN:
		  action.thrust = +1;
		  break;
	    }
	  }

	  public void keyReleased(KeyEvent e) {
	    // please add appropriate event handling code
		  int key = e.getKeyCode();
		    switch (key) {
		    case KeyEvent.VK_UP:
		      action.thrust = 0;
		      break;
		    case KeyEvent.VK_LEFT:
		      action.left_right = 0;
		      break;
		    case KeyEvent.VK_RIGHT:
		      action.left_right = 0;
		      break;
		    case KeyEvent.VK_SPACE:
		      action.shoot = false;
		      break;
		    case KeyEvent.VK_DOWN:
		      action.thrust = 0;
		      break;		    	
		    }
	  }
	}