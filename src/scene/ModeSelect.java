package scene;

import java.io.IOException;

import final_project.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModeSelect {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void returnToMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		getStage(e);
		scene = new Scene(root);				
		stage.setScene(scene);
	}
	
	public void enterGame(ActionEvent e) throws IOException {
		Config.mc.stop();
		getStage(e);
		GameScene gameScene = new GameScene(3, "");
		stage.setScene(gameScene.s);
		stage.show();
	}
	
	public void enterHell(ActionEvent e) throws IOException {
		Config.mc.stop();
		getStage(e);
		GameScene gameScene = new GameScene(10, "Hard");
		stage.setScene(gameScene.s);
		stage.show();
	}
	
	public void getStage(ActionEvent e) {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	}
}
