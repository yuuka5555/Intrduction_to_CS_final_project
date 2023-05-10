package scene;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import final_project.Character;
import final_project.MusicController;

public class GameScene {
	Scene s;
	Group root = new Group();
	ArrayList<Rectangle> obstacles = new ArrayList<>();
	
	MapSwitcher mapHandler = new MapSwitcher(root, obstacles);
	
	int curMap = mapHandler.curMap;
	int maxMap = mapHandler.maxMap;
	
	String name = "professor";
	Character chara = new Character(obstacles, name);
	
	private MusicController mc = new MusicController();
	
	public GameScene() throws IOException {
		s = new Scene(root, 600, 700, Color.TRANSPARENT);
		loadCharacter();
		loadMap();
		over.start();
		chara.setMaxMap(maxMap);
		mc.play("src/music/" + chara.name + ".mp3");
	}
	
	private void loadCharacter() {
		chara.bindControls(s);
		root.getChildren().add(chara.imgView);
		//root.getChildren().add(chara.c);
	}
	
	private void loadMap() throws IOException {
		mapHandler.attachNewMapAtInitial();
	}
	
	private AnimationTimer over = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			if (chara.imgView.getY() <= 0) {
				try {
					if (mapHandler.goUp()) {
						chara.imgView.setY(695);
						curMap = mapHandler.curMap;
						chara.setCurMap(curMap);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (chara.imgView.getY() >= 700) {
				try {
					mapHandler.goDown();
					chara.imgView.setY(5);
					curMap = mapHandler.curMap;
					chara.setCurMap(curMap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (chara.win) {
				try {
					mc.stop();
					mc.playApplause();
					Parent end = FXMLLoader.load(getClass().getResource("end.fxml"));
					root.getChildren().add(end);
					over.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
}
