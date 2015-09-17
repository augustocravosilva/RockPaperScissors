package aacs.rockpaperscissors.gamelogic;

import java.util.ArrayList;
import java.util.Random;


/**
 * ComputerPlayer is a subclass of Player than allows random moves generation
 *
 */
public class ComputerPlayer extends Player{
	
	private static final long serialVersionUID = -2474650599577530412L;
	private Random r;
	
	public ComputerPlayer(String name) {
		super(name);
		r = new Random();
	}

	public void randomizeShape()
	{
		ArrayList<Shape> shapes = Game.getInstance().getAvailableShapes();
		currentShape = shapes.get(r.nextInt(shapes.size()));
	}
}
