package aacs.rockpaperscissors.gamelogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Game represents the game logic behind the Rock Paper Scissors game<br>
 * The current game instance can be obtained by the getInstance() method<br>
 * Allows customization of shapes to be used<br>
 * Allows game saving and loading
 *
 */
public class Game implements Serializable{
	private static final String SAVED_GAME_PATH = "data/saved_game.rpsdat";
	private static final long serialVersionUID = 2597169843865427864L;
	static private Game instance;
	private ArrayList<Shape> shapes;
	private int round;
	private Player[] players = new Player[2];

	static public Game getInstance()
	{
		if(instance==null)
			instance = new Game();
		return instance;
	}
	
	private Game(){
		shapes = new ArrayList<>();
	}
	
	static public void loadGame() throws ClassNotFoundException, IOException
	{
		 FileInputStream fileIn = new FileInputStream(SAVED_GAME_PATH);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         instance = (Game) in.readObject();
         in.close();
         fileIn.close();
	}
	
	static public void saveGame() throws IOException{
		clearSavedGame();
		FileOutputStream fileOut = new FileOutputStream(SAVED_GAME_PATH);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(getInstance());
		out.close();
		fileOut.close();
	}
	
	static public void clearSavedGame(){
		new File(SAVED_GAME_PATH).delete();
	}
	
	static public void resetGame()
	{
		instance = new Game();
	}
		
	public void startGame(Player player1, Player player2)
	{
		players[0]= player1;
		players[1] = player2;
		round = 1;
	}
	
	public Player getWinner(){
		if(players[0].getPoints() > players[1].getPoints())
			return players[0];
		else if(players[1].getPoints() > players[0].getPoints())
			return players[1];
		else return null;
	}
	
	
	/**
	 * @return the Winner, null in case of a tie
	 */
	public Player nextRound(){
		Shape p1Shape = players[0].getCurrentShape();
		Shape p2Shape = players[1].getCurrentShape();
		Player roundWinner = null;
		
		if(p1Shape.play(p2Shape) - p2Shape.play(p1Shape) > 0)
		{
			players[0].addPoint();
			roundWinner = players[0];
		}
		else if(p1Shape.play(p2Shape) - p2Shape.play(p1Shape) < 0)
		{
			players[1].addPoint();
			roundWinner = players[1];
		}
		
		players[0].resetShape();
		players[1].resetShape();
		round++;
		
		return roundWinner;
	}
	
	public ArrayList<Shape> getAvailableShapes(){
		return shapes;
	}
	
	public Shape getShape(Class<?> var)
	{
		for (Shape s : shapes) {
			if(s.getClass() == var)
				return s;
		}
		return null;
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}

	public int getRound() {
		return round;
	}
	
	public Player[] getPlayers(){
		return players;
	}
	
}
