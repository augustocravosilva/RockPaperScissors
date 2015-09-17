package aacs.rockpaperscissors.gamelogic;

import java.io.Serializable;


/**
 * Represents a Game Player and holds its current move choice
 *
 */
public class Player implements Serializable{
	
	private static final long serialVersionUID = -4454818718130648431L;
	protected String name;
	protected Shape currentShape;
	private int points=0;

	public Player(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Shape getCurrentShape() {
		return currentShape;
	}
	
	public void resetShape(){
		currentShape=null;
	}

	public int getPoints() {
		return points;
	}

	public void addPoint() {
		this.points++;
	}
	
}
