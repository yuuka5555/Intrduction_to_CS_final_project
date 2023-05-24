package scene;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.shape.Rectangle;

public class MapSwitcher {
	private String baseMapName = "/scene/map";
	private String difficult;
	private Group root;
	public int curMap = 1;
	public int maxMap;
	
	public ArrayList<Rectangle> obstacles;
	
	public void setDifficult(String degree) {
		this.difficult = degree;
	}
	
	public MapSwitcher(Group root, ArrayList<Rectangle> obstacles) {
		this.root = root;
		this.obstacles = obstacles;
	}
	
	public boolean goUp() throws IOException {
		if (curMap + 1 > maxMap) {
			return false;
		}
		else {
			curMap++;
		}
		
		attachNewMap();
		return true;
	}
	
	public boolean goDown() throws IOException {
		if (curMap == 1) {
			return false;
		}
		else {
			curMap--;
		}
		
		attachNewMap();
		return true;
	}
	
	public void attachNewMapAtInitial() throws IOException {
		Parent fxmlRoot = FXMLLoader.load(getClass().getResource(baseMapName + difficult + String.valueOf(curMap) + ".fxml"));
		SubScene fxmlScene = new SubScene(fxmlRoot, 600, 700);
		
		ObservableList<Node> list = root.getChildren();
		
		Node chara = list.get(0);
		list.remove(0);
		
		// put in the new map
		list.add(fxmlScene);
		
		// re-put the character in
		list.add(chara);
		
		obstacles.clear();
		for (Node n: fxmlRoot.getChildrenUnmodifiable()) {
			if (n instanceof Rectangle) {
				obstacles.add((Rectangle) n);
			}
		}
	}
	
	public void attachNewMap() throws IOException {
		Parent fxmlRoot = FXMLLoader.load(getClass().getResource(baseMapName + difficult + String.valueOf(curMap) + ".fxml"));
		SubScene fxmlScene = new SubScene(fxmlRoot, 600, 700);
		
		ObservableList<Node> list = root.getChildren();
		
		// take out the character
		Node chara = list.get(1);
		list.remove(1);
		list.remove(0);
		
		// put in the new map
		list.add(fxmlScene);
		
		obstacles.clear();
		for (Node n: fxmlRoot.getChildrenUnmodifiable()) {
			if (n instanceof Rectangle) {
				obstacles.add((Rectangle) n);
			}
		}
		
		// re-put the character in
		list.add(chara);
	}
}
