package final_project;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Config {
	public static DoubleProperty volume = new SimpleDoubleProperty(0.5);
	
	public static String curChara = "squirrel";
	
	public static int gameState = 0;
	
	public static int jumpTime = 0;
	
	public static boolean cheat = false;
	
	public static MusicController mc = new MusicController();
}
