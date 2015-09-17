package aacs.rockpaperscissors.gamelogic;

import java.io.Serializable;

/**
 * Represents the Paper move in the game
 *
 */
public class Paper implements Shape, Serializable{

	private static final long serialVersionUID = 4773327899783732646L;

	public int play(Shape opponent) {
		
		if(opponent.getClass() == Rock.class)
			return 1;
		
		if(opponent.getClass() == Scissors.class)
			return -1;
		
		return 0;
		
	}
}
