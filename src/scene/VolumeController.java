package scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import final_project.Config;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class VolumeController implements Initializable{
	@FXML
	private Slider volumeController;
	
	public double volume;
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		volumeController.setValue(Config.volume.getValue() * 100);
		volumeController.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				volume = volumeController.getValue();
				Config.volume.setValue(volume / 100);
			}
		});
	}
	
	public void returnToMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);				
		stage.setScene(scene);
	}
}
