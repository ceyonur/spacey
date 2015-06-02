package engines;

import frames.GameOverView;
import frames.GameView;
import frames.LostView;
import gameobjects.Enemy;
import gameobjects.GameObject;
import gameobjects.PlayerShip;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import utilities.Constants;
import utilities.JEasyFrame;

public class GameEngine {
	public  BasicKeys ctrl;	
	public  List<GameObject> objects;	
	
	public  Player player;	
	private static GameEngine instance = new GameEngine();
	public static boolean gameOn;
	private int enemiesLeft;
	private JEasyFrame frame;
	private GameView view;
	private boolean lw = false;
	private boolean go = false;
	private boolean disableWin = false;
	

	private GameEngine() {		
		objects = new CopyOnWriteArrayList<GameObject>();		
		ctrl = new BasicKeys();
		player = new Player();
		gameOn = true;
    	PlayerShip ship = new PlayerShip(10,10);
    	player.ship = ship;
		objects.add(ship);		
		loadLevel(1);
		
	}

	public static GameEngine getInstance() {		
		return instance;
	}
	
	
	public void loadLevel(int i){		
		player.ship = new PlayerShip(10,10);
		objects.clear();
		objects.add(player.ship);
		LevelLoader.getInstance().loadLevel(i);
		objects.addAll(LevelLoader.getInstance().enemies);
		enemiesLeft = LevelLoader.getInstance().enemies.size();	
		gameOn = true;
	}

	
	public void update(List<GameObject> objects) {
		
		ListIterator<GameObject> it = objects.listIterator();		
		while (it.hasNext()) {
			List<GameObject> objects2 = new CopyOnWriteArrayList<GameObject>();
			objects2.addAll(objects);
			GameObject obj = it.next();			
			obj.update();
			for(GameObject ob : objects2){
				obj.collisionHandling(ob);
				objects2.remove(ob);
			}
			if (obj.dead) {				
				if(obj instanceof Enemy){
					enemiesLeft--;
				}
				objects.remove(obj);
			}
		}
		checkLostSituation();
		if(!disableWin){
		checkWinSituation();
		}
		
	}

	public static void main(String[] args) throws Exception {
		GameEngine.getInstance().loadLevel(1);		
		GameEngine.getInstance().view = new GameView();
		GameEngine.getInstance().frame = new JEasyFrame(GameEngine.getInstance().view, "Spacey");
		GameEngine.getInstance().frame.addKeyListener(GameEngine.getInstance().ctrl);	
			GameEngine.getInstance().startGame();
			
		}
	
	public void checkWinSituation(){
		if(enemiesLeft == 0 && !player.ship.dead){
			if(LevelLoader.getInstance().maxLevelNo == LevelLoader.getInstance().levelNo){
				gameOver();
			}
			else{
			win();
			}
		}
		
		
	}
	
	public void checkLostSituation(){
		if(player.ship.dead){			
			lost();	
			disableWin = true;
		}
	}

	private void win() {
		// TODO Auto-generated method stub
		player.increaseScoreBy(LevelLoader.getInstance().finishScore);
		loadLevel(LevelLoader.getInstance().levelNo+1);
		
	}
	
	private void gameOver(){
		if(!go){
			 GameOverView gOver = new GameOverView();		
			 gOver.show();
			 }
			 go = true;	
	}
	
	public void startAgain(){
		disableWin = false;
		player = new Player();
		lw = false;
		go = false;
		loadLevel(1);		
		
		
	}

	public void lost() {
		// TODO Auto-generated method stub	
		 
		 if(!lw){
		 LostView lw = new LostView();		
		 lw.show();
		 }
		 lw = true;	 
		 
	}
	
	public void startGame(){

			
		// run the game			
		while (true) {
			if(gameOn){
			GameEngine.getInstance().update(GameEngine.getInstance().objects);
			view.repaint();				
			try {
				Thread.sleep(Constants.DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}

}
