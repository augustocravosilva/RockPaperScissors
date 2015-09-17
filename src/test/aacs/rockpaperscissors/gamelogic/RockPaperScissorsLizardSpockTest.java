package aacs.rockpaperscissors.gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * The only purpose of this test is to show that we can easily extend the game
 * by overriding the play method of the Shapes
 * Because of this not all the possible outcomes are tested - Just Rock v all the other Shapes
 */

public class RockPaperScissorsLizardSpockTest {
	@SuppressWarnings("serial")
	private class NewPaper extends Paper {
		public int play(Shape opponent) {
			if(opponent.getClass() == NewRock.class || opponent.getClass() == Spock.class)
				return 1;
			if(opponent.getClass() == NewScissors.class || opponent.getClass() == Lizard.class)
				return -1;
			return 0;
		}
	}
	@SuppressWarnings("serial")
	private class NewScissors extends Scissors {
		public int play(Shape opponent) {
			if(opponent.getClass() == NewPaper.class || opponent.getClass() == Lizard.class)
				return 1;
			if(opponent.getClass() == NewRock.class || opponent.getClass() == Spock.class)
				return -1;
			return 0;
		}
	}
	@SuppressWarnings("serial")
	private class NewRock extends Rock {
		public int play(Shape opponent) {
			if(opponent.getClass() == NewScissors.class || opponent.getClass() == Lizard.class)
				return 1;
			if(opponent.getClass() == NewPaper.class || opponent.getClass() == Spock.class)
				return -1;
			return 0;
		}
	}
	private class Lizard implements Shape {
		public int play(Shape opponent) {
			if(opponent.getClass() == NewPaper.class || opponent.getClass() == Spock.class)
				return 1;
			if(opponent.getClass() == NewScissors.class || opponent.getClass() == NewRock.class)
				return -1;
			return 0;
		}
	}
	private class Spock implements Shape {
		public int play(Shape opponent) {
			if(opponent.getClass() == NewRock.class || opponent.getClass() == NewScissors.class)
				return 1;
			if(opponent.getClass() == NewPaper.class || opponent.getClass() == Lizard.class)
				return -1;
			return 0;
		}
	}
	
	@Test
	public void testRockVAll() {
		int p1Points = 0;
		int p2Points = 0;
		HumanPlayer p1 = new HumanPlayer("Mr John");
		ComputerPlayer p2 = new ComputerPlayer("TheMachine");
		
		Game.getInstance().addShape(new NewPaper());
		Game.getInstance().addShape(new NewRock());
		Game.getInstance().addShape(new NewScissors());
		Game.getInstance().addShape(new Lizard());
		Game.getInstance().addShape(new Spock());
		
		Game.getInstance().startGame(p1, p2);
		for(int i = 0; i < 100; i++)
		{
			p1.setCurrentShape(Game.getInstance().getShape(NewRock.class));
			p2.randomizeShape();
			
			if(p2.getCurrentShape().getClass() == NewPaper.class)
			{
				assertEquals(Game.getInstance().nextRound(), p2);
				p2Points++;
			}else if(p2.getCurrentShape().getClass() == NewScissors.class)
			{
				assertEquals(Game.getInstance().nextRound(), p1);
				p1Points++;
			}else if(p2.getCurrentShape().getClass() == Lizard.class)
			{
				assertEquals(Game.getInstance().nextRound(), p1);
				p1Points++;
			}else if(p2.getCurrentShape().getClass() == Spock.class)
			{
				assertEquals(Game.getInstance().nextRound(), p2);
				p2Points++;
			}
			else{
				assertNull(Game.getInstance().nextRound());
			}
			
		}		
		
		assertEquals(p1.getPoints(), p1Points);
		assertEquals(p2.getPoints(), p2Points);
	}

}
