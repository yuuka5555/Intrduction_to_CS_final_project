package scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ModeSelect {
	private Stage stage;
	
	public void enterGame(ActionEvent e) throws IOException {
		getStage(e);
		GameScene gameScene = new GameScene(3, "");
		stage.setScene(gameScene.s);
		stage.show();
	}
	
	public void enterHell(ActionEvent e) throws IOException {
		getStage(e);
		GameScene gameScene = new GameScene(10, "Hard");
		stage.setScene(gameScene.s);
		stage.show();
	}
	
	public void getStage(ActionEvent e) {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	}
}
