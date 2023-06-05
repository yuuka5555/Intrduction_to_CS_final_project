package final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application{	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Image icon = new Image(getClass().getResourceAsStream("/picture/icon.png"));
		Config.m.play("src/music/menu.m4a");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Leap of Lengend");
		stage.getIcons().add(icon);
		stage.show();
	}

}
