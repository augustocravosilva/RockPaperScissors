package aacs.rockpaperscissors.gamelogic;

import java.io.Serializable;

/**
 * Represents the Paper move in the game
 *
 */
public class Rock implements Shape, Serializable {

	private static final long serialVersionUID = 2963364319302918811L;

	public int play(Shape opponent) {
		
		if(opponent.getClass() == Scissors.class)
			return 1;
		
		if(opponent.getClass() == Paper.class)
			return -1;
		
		return 0;
	}

}
