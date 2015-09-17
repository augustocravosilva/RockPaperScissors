package aacs.rockpaperscissors.gamelogic;

/**
 * HumanPlayer is a subclass of Player than allows the current shape to be manually set
 *
 */
public class HumanPlayer extends Player {
	
	private static final long serialVersionUID = 8875598152480536930L;

	public HumanPlayer(String name) {
		super(name);
	}

	public void setCurrentShape(Shape shape)
	{
		this.currentShape = shape;
	}
}
