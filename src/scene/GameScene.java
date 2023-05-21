package scene;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import final_project.Character;
import final_project.Config;
import final_project.MusicController;

public class GameScene {
	Scene s;
	Group root = new Group();
	ArrayList<Rectangle> obstacles = new ArrayList<>();
	
	MapSwitcher mapHandler = new MapSwitcher(root, obstacles);
	
	int curMap = 1;
	int maxMap;
	
	String degree;
	Character chara = new Character(obstacles);
	
	private MusicController mc = new MusicController();
	
	public GameScene(int max, String degree) throws IOException {
		this.degree = degree;
		this.maxMap = max;
		s = new Scene(root, 600, 700, Color.TRANSPARENT);
		loadCharacter(max);
		loadMap(max);
		over.start();
		mc.play("src/music/" + Config.curChara + ".mp3");
	}
	
	private void loadCharacter(int max) {
		chara.setMaxMap(max);
		chara.bindControls(s);
		root.getChildren().add(chara.imgView);
	}
	
	private void loadMap(int max) throws IOException {
		mapHandler.maxMap = max;
		mapHandler.setDifficult(degree);
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
