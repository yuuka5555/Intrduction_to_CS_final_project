package scene;

import java.io.IOException;

import final_project.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class CharacterSelect {
	@FXML
	private RadioButton yoga, professor, squirrel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void setCharacter(ActionEvent e) {
		if (yoga.isSelected()) {
			Config.curChara = "yoga";
		} else if (professor.isSelected()) {
			Config.curChara = "professor";
		} else if (squirrel.isSelected()) {
			Config.curChara = "squirrel";
		}
	}
	
	public void returnToMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);				
		stage.setScene(scene);
	}
}
