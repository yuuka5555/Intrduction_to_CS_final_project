package scene;

import java.io.IOException;

import final_project.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lose {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void returnToMenu(ActionEvent e) throws IOException {
		Config.mc.playClick("src/music/click.mp3");
		Config.m.play("src/music/menu.m4a");
		root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);				
		stage.setScene(scene);
	}
}
