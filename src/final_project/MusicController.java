package final_project;

import java.io.File;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicController {
	private Media media;
	
	private MediaPlayer mediaplayer;
	
	private File directory;
	
	int songNumber = 0;
	
	public void play(String song) {
		directory = new File(song);
		media = new Media(directory.toURI().toString());
		mediaplayer = new MediaPlayer(media);		
		mediaplayer.setCycleCount(Timeline.INDEFINITE);
		mediaplayer.play();
	}
	
	public void playApplause() {
		directory = new File("src/music/applause.mp3");
		media = new Media(directory.toURI().toString());
		mediaplayer = new MediaPlayer(media);
		mediaplayer.play();
	}
	
	public void stop() {
		mediaplayer.stop();
	}
}
