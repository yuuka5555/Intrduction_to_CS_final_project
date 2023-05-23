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
		rt = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stg = (Stage)((Node)e.getSource()).getScene().getWindow();
		sc = new Scene(rt);				
		stg.setScene(sc);
		Config.gameState = 3;
	}
	
	public void backToGame(ActionEvent e) {
		Config.gameState = 2;
	}
}