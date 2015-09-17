package aacs.rockpaperscissors.gamelogic;


/**
 * All game moves should implement this Interface
 * and override the play method
 *
 */
public interface Shape {
	/**
	 * Override this method to set the relationship with other shapes<br>
	 * Return positive values for winning moves<br>
	 * Return negative values for losing moves<br>
	 * Return 0 when it is a tie<br>
	 * 
	 */
	int play(Shape opponent);
}