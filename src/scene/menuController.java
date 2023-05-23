package scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuController {
	private Stage stage;
	
	public void enterModeSelect(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/mode.fxml"));
		Parent root = loader.load();
		getStage(e);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void enterVolumeController(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/volume.fxml"));
		Parent root = loader.load();
		getStage(e);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void selectCharacter(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/characterSelect.fxml"));
		Parent root = loader.load();
		getStage(e);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void intructor(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/instructor.fxml"));
		Parent root = loader.load();
		getStage(e);
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void getStage(ActionEvent e) {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	}
}