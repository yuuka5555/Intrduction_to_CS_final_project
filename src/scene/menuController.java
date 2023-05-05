package scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class menuController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void enterGame(ActionEvent e) throws IOException {
		getStage(e);
		GameScene gameScene = new GameScene();
		stage.setScene(gameScene.s);
	}
	
	public void getStage(ActionEvent e) {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	}
}
