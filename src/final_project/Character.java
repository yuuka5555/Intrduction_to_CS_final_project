package final_project;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Character {
	int curMap = 1;
	int maxMap;
	double timeInTheAir = 0;
	double bottom = 680;
	double time = 0;
	double goalY = 700;
	double charaWidth = 40;
	double charaHeight = 45;
	
	public boolean canJump = true;
	public boolean canMove = true;
	
	public boolean win = false;
	
	char direction = 'n';
	
	public ImageView imgView;
	public Image img;

	public AnimationTimer jumping;
	
	public ArrayList<Rectangle> obstacles;
	
	//jumping animation
	private AnimationTimer space = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			time++;
		}
	};
	
	//fall down animation
	private AnimationTimer fall = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			fallDown();
			if (onTheFloor()) {
				fall.stop();
				canMove = true;
			}
		}
	};
	
	//claim character
	public Character(ArrayList<Rectangle> obstacles) {		
		img = new Image(getClass().getResourceAsStream("/picture/" + Config.curChara + ".png"));
		imgView = new ImageView();
		imgView.setImage(img);
		imgView.setFitWidth(charaWidth);
		imgView.setFitHeight(charaHeight);
		imgView.setX(300 - (charaWidth / 2));
		imgView.setY(680 - charaHeight);
		
		this.obstacles = obstacles;
	}
	
//	public EventHandler<KeyEvent> p = new EventHandler<KeyEvent>() {
//
//		@Override
//		public void handle(KeyEvent event) {
//			if (event.getCode().equals(KeyCode.ESCAPE)) {
//				
//			}
//		}
//	};
	
	//moving command determined
	public EventHandler<KeyEvent> controls = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {				
			switch(event.getCode()) {
			case A:
				canJump = false;
				if (canMove) {
					if (!wallL()) {
						moveL();
						direction = 'l';
					}
					if (!onTheFloor()) {
						fall.start();
					}
				}
				canJump = true;
				
				break;
			case D:
				canJump = false;
				if (canMove) {
					if (!wallR()) {
						moveR();
						direction = 'r';
					}
					
					if (!onTheFloor()) {
						fall.start();
					}
				}
				canJump = true;
				
				break;
			case SPACE:
				if (onTheFloor() && time == 0) {
					canMove = false;
					space.start();
				}
				break;
			case W:
				if (canJump && canMove) {
					direction = 'n';
				}
				break;
			case ESCAPE:
				Config.gameState = 1;
				break;
			default:
				break;
			}
		}
		
	};
	
	public void bindControls(Scene s) {
		s.setOnKeyPressed(controls);
		
		s.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (onTheFloor() && event.getCode().equals(KeyCode.SPACE)) {
					space.stop();
					jump();
				}
			}
			
		});
	}
	
	//move to right
	public void moveR() {
		imgView.setX(imgView.getX() + 10);
	}
	
	//move to left
	public void moveL() {
		imgView.setX(imgView.getX() - 10);
	}
	
	//jump move
	public void jump() {
		jumping = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				timeInTheAir++;
				
				if (curMap == maxMap && goalY == 700) {
					getGoalY();
				}
				
				if (timeInTheAir < timeControlY(time) && canJump) {
					imgView.setY(imgView.getY()-5);
					jumpX();
					canJump = false;
					canMove = false;
				} else if (timeInTheAir < timeControlY(time) && !onTheFloor() && !hit() && !hitSide()) {
					imgView.setY(imgView.getY()-5);
					jumpX();
				} else if (timeInTheAir < timeControlY(time) && !onTheFloor() && hit()) {
					imgView.setY(imgView.getY()+5);
					timeInTheAir = 80;
					jumpX();
				} else if (timeInTheAir < timeControlY(time) && !onTheFloor() && hitSide()) {
					imgView.setY(imgView.getY()-5);
					changeDirection();
					jumpX();
				} else if (!onTheFloor() && hitSide()) {
					imgView.setY(imgView.getY()+5);
					changeDirection();
					jumpX();
				} else if (!onTheFloor()) {
					imgView.setY(imgView.getY()+5);
					jumpX();
				} else {
					jumping.stop();
					timeInTheAir = 0;
					canMove = true;
					canJump = true;
					time = 0;
				}
			}
		};
		jumping.start();
	}
	
	//make character move horizontally in the air
	public void jumpX() {
		wall();
		
		if (direction == 'l') {
			imgView.setX(imgView.getX() - speedControlX(time));
		} else if (direction == 'r') {
			imgView.setX(imgView.getX() + speedControlX(time));
		}
	}
	
	//control the horizontal speed
	public double speedControlX(double t) {
		if (t > 40) {
			t = 40;
		}
		return t * 0.1 + 0.2;
	}
	
	//limit the max jumping time
	public double timeControlY(double t) {
		if (t > 80) {
			t = 80;
		}
		return t;
	}
	
	//step on the floor
	public boolean onTheFloor() {
		if (curMap == 1 && imgView.getY() == 680 - charaHeight) {
			imgView.setY(680 - charaHeight);
			return true;
		}
		
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);			
			if (imgView.getY() == temp.getY()-charaHeight && imgView.getX() >= temp.getX()-(charaWidth/2) && imgView.getX() <= temp.getX()+temp.getWidth()-(charaWidth/2)) {
				if (curMap == maxMap && temp.getY() == goalY) {
					win = true;
				}
				
				return true;
			}
		}
		return false;
	}
	
	//walk out of the floor
	public void fallDown() {
		canMove = false;
		imgView.setY(imgView.getY() + 5);
	}
	
	//change direction when needed
	public void changeDirection() {
		if (direction == 'l') {
			direction = 'r';
		} else if (direction == 'r') {
			direction = 'l';
		}
	}
	
	//hit the wall during jumping
	public void wall() {
		if (imgView.getX() <= 0) {
			direction = 'r';
		} else if (imgView.getX() >= 550) {
			direction = 'l';
		}
	}
	
	//hit the left wall when on the floor
	public boolean wallL() {
		if (imgView.getX() <= 0) {
			return true;
		}
		return false;
	}
	
	//hit the left wall when on the floor
	public boolean wallR() {
		if (imgView.getX() >= 550) {
			return true;
		}
		return false;
	}
	
	//hit the bottom
	public boolean hit() {
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (imgView.getY() == temp.getY()+20 && imgView.getX() <= temp.getX()+temp.getWidth() && imgView.getX() >= temp.getX()-charaWidth) {
				return true;
			}
		}
		return false;
	}
	
	//hit the side of the obstacle
	public boolean hitSide() {
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (direction == 'l' && imgView.getY() >= temp.getY()-charaHeight && imgView.getY() <= temp.getY()+20 && imgView.getX() > temp.getX()+temp.getWidth()-(charaWidth/2) && imgView.getX() <= temp.getX()+temp.getWidth()) {
				return true;
			} else if (direction == 'r' && imgView.getY() >= temp.getY()-charaHeight && imgView.getY() < temp.getY()+20 && imgView.getX() >= temp.getX()-charaWidth && imgView.getX() < temp.getX()+(charaWidth/2)) {
				return true;
			}
		}
		return false;
	}
	
	//get current map number
	public void setCurMap(int curMap) {
		this.curMap = curMap;
	}
	
	//get max map number
	public void setMaxMap(int maxMap) {
		this.maxMap = maxMap;
	}
	
	//get goal's y
	public void getGoalY() {
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (temp.getY() < goalY) {
				goalY = temp.getY();
			}
		}
	}
}
