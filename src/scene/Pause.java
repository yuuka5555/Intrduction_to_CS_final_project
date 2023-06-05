package scene;

import java.io.IOException;

import final_project.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pause {
	private Stage stg;
	private Scene sc;
	private Parent rt;
	
	public void returnToMenu(ActionEvent e) throws IOException {
		Config.mc.playClick("src/music/click.mp3");
		Config.m.play("src/music/menu.m4a");
		rt = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stg = (Stage)((Node)e.getSource()).getScene().getWindow();
		sc = new Scene(rt);				
		stg.setScene(sc);
		Config.gameState = 3;
	}
	
	public void backToGame(ActionEvent e) {
		Config.mc.playClick("src/music/click.mp3");
		Config.gameState = 2;
	}
	
	public void openPublisherTool(ActionEvent e) {
		Config.mc.playClick("src/music/click.mp3");
		if (Config.cheat) {
			Config.cheat = false;
		} else {
			Config.cheat = true;
		}
	}
}
