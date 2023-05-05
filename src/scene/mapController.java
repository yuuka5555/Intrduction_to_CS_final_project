package scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import final_project.Character;

public class mapController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public static Rectangle character;
	
	public void strat(Scene s) {
		
	}
	
//	public void switchTo2(ActionEvent e) throws IOException {
//		root = FXMLLoader.load(getClass().getResource("map2.fxml"));
//		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//		
//		
//		
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
}
