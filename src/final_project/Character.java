package final_project;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import scene.MapSwitcher;

public class Character {
	int curMap = 1;
	double timeInTheAir = 0;
	double bottom = 680;
	double time = 0;
	boolean canJump = true;
	boolean canMove = true;
	char direction = 'n';
	
	public Rectangle c;

	private AnimationTimer jumping;
	
	public ArrayList<Rectangle> obstacles;
	
	private AnimationTimer space = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			time++;
		}
	};
	
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
	
	public Character(ArrayList<Rectangle> obstacles) {
		c = new Rectangle(290, 630, 20, 50);
		c.setFill(Color.BLUE);
		this.obstacles = obstacles;
	}
	
	public void bindControls(Scene s) {
		s.setOnKeyPressed(new EventHandler<KeyEvent>() {

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
				case W:
					if (onTheFloor() && time == 0) {
						canMove = false;
						space.start();
					}
					break;
				default:
					break;
				}
			}
			
		});
		
		s.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (onTheFloor() && event.getCode().equals(KeyCode.W)) {
					space.stop();
					jump();
				}
			}
			
		});
	}
	
	public void moveR() {
		c.setX(c.getX() + 10);
	}
	
	public void moveL() {
		c.setX(c.getX() - 10);
	}
	
	public void jump() {
		canMove = true;
		jumping = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				timeInTheAir++;
				
				if (timeInTheAir < speedControlY(time) && canJump) {
					c.setY(c.getY()-5);
					jumpX();
					canJump = false;
					canMove = false;
				} else if (timeInTheAir < speedControlY(time) && !onTheFloor() && !hit() && !hitSide()) {
					c.setY(c.getY()-5);
					jumpX();
				} else if (timeInTheAir < speedControlY(time) && !onTheFloor() && hit()) {
					c.setY(c.getY()+5);
					timeInTheAir = 80;
					jumpX();
				} else if (timeInTheAir < speedControlY(time) && !onTheFloor() && hitSide()) {
					c.setY(c.getY()-5);
					changeDirection();
					jumpX();
				} else if (!onTheFloor() && hitSide()) {
					c.setY(c.getY()+5);
					changeDirection();
					jumpX();
				} else if (!onTheFloor()) {
					c.setY(c.getY()+5);
					jumpX();
				} else if (onTheFloor() && !canJump) {
					timeInTheAir = 161;
					canJump = true;
				} else {
					jumping.stop();
					timeInTheAir = 0;
					canMove = true;
					time = 0;
				}
			}
		};
		jumping.start();
	}
	
	public void jumpX() {
		wall();
		
		if (direction == 'l') {
			c.setX(c.getX() - speedControlX(time));
		} else if (direction == 'r') {
			c.setX(c.getX() + speedControlX(time));
		}
	}
	
	public double speedControlX(double t) {
		if (t > 40) {
			t = 40;
		}
		return t * 0.1 + 0.2;
	}
	
	public double speedControlY(double t) {
		if (t > 80) {
			t = 80;
		}
		return t;
	}
	
	public void setCurMap(int curMap) {
		this.curMap = curMap;
	}
	
	public boolean onTheFloor() {
		if (curMap == 1 && c.getY() == 630) {
			c.setY(630);
			return true;
		}
		
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (c.getY() == temp.getY()-50 && c.getX() > temp.getX()-10 && c.getX() < temp.getX()+temp.getWidth()-10) {
				return true;
			}
		}
		return false;
	}
	
	public void fallDown() {
		canMove = false;
		c.setY(c.getY() + 5);
	}
	
	public void wall() {
		if (c.getX() <= 0) {
			direction = 'r';
		} else if (c.getX() >= 580) {
			direction = 'l';
		}
	}
	
	public boolean wallL() {
		if (c.getX() <= 0) {
			return true;
		}
		return false;
	}
	
	public boolean wallR() {
		if (c.getX() >= 580) {
			return true;
		}
		return false;
	}
	
	public boolean hit() {
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (c.getY() == temp.getY()+20 && c.getX() <= temp.getX()+temp.getWidth() && c.getX() >= temp.getX()-20) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hitSide() {
		for (int i = 0; i < obstacles.size(); i++) {
			Rectangle temp = obstacles.get(i);
			if (direction == 'l' && c.getY() > temp.getY()-50 && c.getY() < temp.getY()+20 && c.getX() >= temp.getX()+temp.getWidth() && c.getX() < temp.getX()+temp.getWidth()+3) {
				return true;
			} else if (direction == 'r' && c.getY() > temp.getY()-50 && c.getY() < temp.getY()+20 && c.getX() > temp.getX()-23 && c.getX() <= temp.getX()-20) {
				return true;
			}
		}
		return false;
	}
	
	public void changeDirection() {
		if (direction == 'l') {
			direction = 'r';
		} else if (direction == 'r') {
			direction = 'l';
		}
	}
	
	public void reset(ActionEvent e) {
		c.setX(290);
		c.setY(630);
	}
}
