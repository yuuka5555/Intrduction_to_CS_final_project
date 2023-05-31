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
	
	Parent pause = FXMLLoader.load(getClass().getResource("pause.fxml"));
	
	private MusicController mc = new MusicController();
	
	public GameScene(int max, String degree) throws IOException {
		this.degree = degree;
		this.maxMap = max;
		s = new Scene(root, 600, 700, Color.TRANSPARENT);
		loadCharacter(max);
		loadMap(max);
		gameTime.start();
		mc.play("src/music/" + Config.curChara + ".mp3");
	}
	
	private void loadCharacter(int max) {
		chara.setMaxMap(max);
		chara.bindControls(s);
		root.getChildren().add(chara.imgView);
		Config.jumpTime = 0;
	}
	
	private void loadMap(int max) throws IOException {
		mapHandler.maxMap = max;
		mapHandler.setDifficult(degree);
		mapHandler.attachNewMapAtInitial();
	}
	
	private AnimationTimer gameTime = new AnimationTimer() {
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
					s.setOnKeyPressed(null);
					mc.playApplause();
					Parent end = FXMLLoader.load(getClass().getResource("end.fxml"));
					root.getChildren().add(end);
					gameTime.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (Config.gameState == 1) {
				Config.gameState = 0;				
				root.getChildren().add(pause);
				s.setOnKeyPressed(null);
				gameTime.stop();
				pauseTime.start();
			} else if (Config.curChara.equals("professor") && Config.jumpTime > 20) {
				try {
					mc.stop();
					s.setOnKeyPressed(null);
					Parent end = FXMLLoader.load(getClass().getResource("lose.fxml"));
					root.getChildren().add(end);
					gameTime.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private AnimationTimer pauseTime = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			if (Config.gameState == 2) {
				root.getChildren().remove(pause);
				Config.gameState = 0;
				s.setOnKeyPressed(chara.controls);
				gameTime.start();
				pauseTime.stop();
			} else if (Config.gameState == 3) {
				mc.stop();
				pauseTime.stop();
				Config.gameState = 0;
			}
		}
	};
}