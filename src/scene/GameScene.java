package scene;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import final_project.Character;

public class GameScene {
	Scene s;
	Group root = new Group();
	ArrayList<Rectangle> obstacles = new ArrayList<>();
	MapSwitcher mapHandler = new MapSwitcher(root, obstacles);
	
	int curMap = mapHandler.curMap;
	Character chara = new Character(obstacles);
	
	
	public GameScene() throws IOException {
		s = new Scene(root, 600, 700, Color.TRANSPARENT);
		loadCharacter();
		loadMap();
		over.start();
	}
	
	private void loadCharacter() {
		chara.bindControls(s);
		root.getChildren().add(chara.c);
	}
	
	private void loadMap() throws IOException {
		mapHandler.attachNewMapAtInitial();
	}
	
	private AnimationTimer over = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			if (chara.c.getY() <= 0) {
				try {
					if (mapHandler.goUp()) {
						mapHandler.goUp();
						chara.c.setY(695);
						curMap = mapHandler.curMap;
						chara.setCurMap(curMap);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (chara.c.getY() >= 700) {
				try {
					mapHandler.goDown();
					chara.c.setY(5);
					curMap = mapHandler.curMap;
					chara.setCurMap(curMap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
}
