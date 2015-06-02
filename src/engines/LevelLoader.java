package engines;

import gameobjects.Boss;
import gameobjects.Enemy1;
import gameobjects.Enemy2;
import gameobjects.Enemy3;
import gameobjects.Ship;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utilities.Vector2D;
import utilities.XMLParser;

public class LevelLoader {

	private static LevelLoader instance;
	public ArrayList<Ship> enemies;

	public int levelNo;
	public int finishScore;
	private static File xmlFile = new File("xml/levels.xml");
	public int maxLevelNo;

	private LevelLoader() {
		levelNo = 0;
		finishScore = 0;
		enemies = new ArrayList<Ship>();
		maxLevelNo = getTotalNumLevels();
	}

	public static LevelLoader getInstance() {
		if (instance == null)
			instance = new LevelLoader();
		return instance;
	}

	public void loadLevel(int levelNo) {		
		finishScore = 0;
		enemies = new ArrayList<Ship>();
		this.levelNo = levelNo;
		Document document;
		try {
			document = XMLParser.getDomElement(xmlFile);

			// find the according level
			NodeList nodeList = document.getElementsByTagName("level");
			Element eLevel = null;
			for (int i = 0; i < nodeList.getLength(); i++) {
				eLevel = (Element) nodeList.item(i);
				int currentLevelNo = Integer.parseInt(XMLParser.getValue(
						eLevel, "levelno"));
				if (currentLevelNo == levelNo) {
					break;
				}
			}
			finishScore = Integer.parseInt(XMLParser.getValue(eLevel,
					"finishscore"));

			Element eEnemies = (Element) eLevel.getElementsByTagName("enemies")
					.item(0);

			NodeList childList = eEnemies.getChildNodes();
			
			for (int i = 0; i < childList.getLength(); i++) {

				Node child = childList.item(i);
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eEnemy = (Element) child;
					String type = XMLParser.getValue(eEnemy, "type");
					int radius = Integer.parseInt(XMLParser.getValue(eEnemy,
							"radius"));
					Color color = Color.WHITE;
					try {
						color = (Color) Color.class.getField(
								XMLParser.getValue(eEnemy, "colour")).get(null);
					} catch (IllegalArgumentException | IllegalAccessException
							| NoSuchFieldException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int health = Integer.parseInt(XMLParser.getValue(eEnemy,
							"health"));
					int attackpower = Integer.parseInt(XMLParser.getValue(
							eEnemy, "attackpower"));
					int bulletspeed = Integer.parseInt(XMLParser.getValue(
							eEnemy, "bulletspeed"));
					Vector2D position = new Vector2D(
							Double.parseDouble(XMLParser.getValue(eEnemy,
									"xPos")), Double.parseDouble(XMLParser
											.getValue(eEnemy, "yPos")));
					Ship enemy = null;

					switch (type) {
					case "Enemy1":
						enemy = new Enemy1(position, radius, color, health,
								attackpower, bulletspeed);
						break;
					case "Enemy2":
						enemy = new Enemy2(position, radius, color, health,
								attackpower, bulletspeed);
						break;
					case "Enemy3":
						enemy = new Enemy3(position, radius, color, health,
								attackpower, bulletspeed);
						break;
					case "Boss":
						enemy = new Boss(position, radius, color, health,
								attackpower, bulletspeed);
						break;

					}
					enemies.add(enemy);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	public String toString() {
		String result = "";
		result += enemies.toString();
		return result;
	}	
	
	public int getTotalNumLevels() {		
		Document document;		
		try {
			document = XMLParser.getDomElement(xmlFile);

			NodeList nodeList = document.getElementsByTagName("level");
			System.out.println(nodeList.getLength());
			return nodeList.getLength();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return 1;

	}
}
