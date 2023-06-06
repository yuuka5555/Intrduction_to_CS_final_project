package scene;

import java.io.IOException;

import final_project.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EndScene {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void returnToMenu(ActionEvent e) throws IOException {
		Config.m.playClick("src/music/click.mp3");
		Config.mc.play("src/music/menu.mp3");
		root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);				
		stage.setScene(scene);
	}
}
