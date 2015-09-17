package aacs.rockpaperscissors.gamelogic;

import java.io.Serializable;

/**
 * Represents the Paper move in the game
 *
 */
public class Scissors implements Shape,Serializable {

	private static final long serialVersionUID = -3244201599431133671L;

	public int play(Shape opponent) {
		
		if(opponent.getClass() == Paper.class)
			return 1;
		
		if(opponent.getClass() == Rock.class)
			return -1;
		
		return 0;
		
	}

}
