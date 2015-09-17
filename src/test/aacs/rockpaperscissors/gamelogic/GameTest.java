package aacs.rockpaperscissors.gamelogic;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class GameTest {
	
	@Test
	public void HumanComputerTest() {
		
		for(int gameNumber = 0;gameNumber < 100; gameNumber++){
			humanComputerGame();
			Game.resetGame();
		}
		
	}

	private void humanComputerGame() {
		int p1Points = 0;
		int p2Points = 0;
		HumanPlayer p1 = new HumanPlayer("John");
		ComputerPlayer p2 = new ComputerPlayer("TheMachine");
		Player[] pp = {p1,p2};
		Game.getInstance().addShape(new Paper());
		Game.getInstance().addShape(new Rock());
		Game.getInstance().addShape(new Scissors());
		
		Game.getInstance().startGame(p1, p2);
		assertArrayEquals(pp, Game.getInstance().getPlayers());
		
		for(int roundNumber = 0; roundNumber < 100; roundNumber++){
			p1.setCurrentShape(Game.getInstance().getShape(Rock.class));
			p2.randomizeShape();
			
			if(p2.getCurrentShape().getClass() == Paper.class)
			{
				assertEquals(Game.getInstance().nextRound(), p2);
				p2Points++;
			}else if(p2.getCurrentShape().getClass() == Scissors.class)
			{
				assertEquals(Game.getInstance().nextRound(), p1);
				p1Points++;
			}else{
				assertNull(Game.getInstance().nextRound());
			}
			
			p1.setCurrentShape(Game.getInstance().getShape(Scissors.class));
			p2.randomizeShape();
			
			if(p2.getCurrentShape().getClass() == Paper.class)
			{
				assertEquals(Game.getInstance().nextRound(), p1);
				p1Points++;
			}else if(p2.getCurrentShape().getClass() == Scissors.class)
			{
				assertNull(Game.getInstance().nextRound());	
			}else{
				assertEquals(Game.getInstance().nextRound(), p2);
				p2Points++;
			}
			
			p1.setCurrentShape(Game.getInstance().getShape(Paper.class));
			p2.randomizeShape();
			
			if(p2.getCurrentShape().getClass() == Paper.class)
			{
				assertNull(Game.getInstance().nextRound());
			}else if(p2.getCurrentShape().getClass() == Scissors.class)
			{
				assertEquals(Game.getInstance().nextRound(),p2);
				p2Points++;
			}else{
				assertEquals(Game.getInstance().nextRound(),p1);
				p1Points++;
			}	
		}
		
		assertEquals(p1.getPoints(), p1Points);
		assertEquals(p2.getPoints(), p2Points);
		
		if(p1Points > p2Points)
			assertEquals(p1,Game.getInstance().getWinner());
		else if(p2Points > p1Points)
			assertEquals(p2,Game.getInstance().getWinner());
		else assertNull(Game.getInstance().getWinner());
	}
	
	private class ComputerPlayerMock extends ComputerPlayer{
		
		private static final long serialVersionUID = 7033879297628952642L;
		public int simulateRandom;
		public ComputerPlayerMock(String name) {
			super(name);
		}
		public void randomizeShape(){
			ArrayList<Shape> shapes = Game.getInstance().getAvailableShapes();
			currentShape = shapes.get(simulateRandom);
		}
	}
	
	@Test
	public void ComputerComputerTest(){
		ComputerPlayerMock p1 = new ComputerPlayerMock("M1");
		ComputerPlayerMock p2 = new ComputerPlayerMock("M2");
		Game.getInstance().addShape(new Paper()); //0
		Game.getInstance().addShape(new Rock()); //1
		Game.getInstance().addShape(new Scissors()); //2
		
		Game.getInstance().startGame(p1, p2);
		
		for(int i = 0; i < 3; i++)
		{
			assertEquals(i+1, Game.getInstance().getRound());
			p1.simulateRandom = i%3;
			p2.simulateRandom = (i+1)%3;
			
			p1.randomizeShape();
			p2.randomizeShape();
			Player winner = Game.getInstance().nextRound();
			assertEquals(p1, winner);
		}	
	}
	
	@Test
	public void Points1Test(){
		ComputerPlayerMock p1 = new ComputerPlayerMock("M1");
		ComputerPlayerMock p2 = new ComputerPlayerMock("M2");
		Game.getInstance().addShape(new Paper()); //0
		Game.getInstance().addShape(new Rock()); //1
		Game.getInstance().addShape(new Scissors()); //2
		
		Game.getInstance().startGame(p1, p2);
		
		p1.simulateRandom = 0;
		p2.simulateRandom = 1;
		
		p1.randomizeShape();
		p2.randomizeShape();
		Game.getInstance().nextRound();
		assertEquals(p1.getPoints(),1);
		assertEquals(p2.getPoints(),0);
		assertEquals(p1,Game.getInstance().getWinner());
	}
	
	@Test
	public void Points2Test(){
		ComputerPlayerMock p1 = new ComputerPlayerMock("M1");
		ComputerPlayerMock p2 = new ComputerPlayerMock("M2");
		Game.getInstance().addShape(new Paper()); //0
		Game.getInstance().addShape(new Rock()); //1
		Game.getInstance().addShape(new Scissors()); //2
		
		Game.getInstance().startGame(p1, p2);
		
		p1.simulateRandom = 0;
		p2.simulateRandom = 2;
		
		p1.randomizeShape();
		p2.randomizeShape();
		Game.getInstance().nextRound();
		assertEquals(p1.getPoints(),0);
		assertEquals(p2.getPoints(),1);
		assertEquals(p2,Game.getInstance().getWinner());
	}
	
	@Test
	public void PointsDrawTest(){
		ComputerPlayerMock p1 = new ComputerPlayerMock("M1");
		ComputerPlayerMock p2 = new ComputerPlayerMock("M2");
		Game.getInstance().addShape(new Paper()); //0
		Game.getInstance().addShape(new Rock()); //1
		Game.getInstance().addShape(new Scissors()); //2
		
		Game.getInstance().startGame(p1, p2);
		
		p1.simulateRandom = 2;
		p2.simulateRandom = 2;
		
		p1.randomizeShape();
		p2.randomizeShape();
		Game.getInstance().nextRound();
		assertEquals(p1.getPoints(),0);
		assertEquals(p2.getPoints(),0);
		assertNull(Game.getInstance().getWinner());
	}
	
	@Test
	public void resetShapeAfterMoveTest(){
		ComputerPlayer p1 = new ComputerPlayer("M1");
		ComputerPlayer p2 = new ComputerPlayer("M2");
		Game.getInstance().addShape(new Paper()); //0
		Game.getInstance().addShape(new Rock()); //1
		Game.getInstance().addShape(new Scissors()); //2
		Game.getInstance().startGame(p1, p2);
		p1.randomizeShape();
		p2.randomizeShape();
		Game.getInstance().nextRound();
		assertNull(p1.getCurrentShape());
		assertNull(p2.getCurrentShape());
	}
	
	@Test
	public void shapeNotFoundTest(){
		assertNull(Game.getInstance().getShape(Object.class));
	}
	
	@Test
	public void playerNameTest(){
		Player p1 = new HumanPlayer("John");
		assertEquals("John", p1.getName());
	}
	
	@Test
	public void saveAndLoadTest(){
		humanComputerGame();
		try {
			Game.saveGame();
		} catch (IOException e) {
			fail();
		}
		try {
			Game.loadGame();
		} catch (ClassNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
		assertEquals(301, Game.getInstance().getRound());
	}
	
	@Test(expected=NullPointerException.class)
	public void ShouldNotAllowNextRoundTest(){
		Game.getInstance().nextRound();
	}
	
}
