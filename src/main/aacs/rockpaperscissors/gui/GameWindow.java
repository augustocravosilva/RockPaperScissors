package aacs.rockpaperscissors.gui;

import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import aacs.rockpaperscissors.gamelogic.ComputerPlayer;
import aacs.rockpaperscissors.gamelogic.Game;
import aacs.rockpaperscissors.gamelogic.HumanPlayer;
import aacs.rockpaperscissors.gamelogic.Paper;
import aacs.rockpaperscissors.gamelogic.Player;
import aacs.rockpaperscissors.gamelogic.Rock;
import aacs.rockpaperscissors.gamelogic.Scissors;
import aacs.rockpaperscissors.gamelogic.Shape;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GameWindow {

	private JFrame frame;
	private ShapeChooserDialog shapeChooserD;
	private NewGameDialog newGameD;
	private Player p1;
	private Player p2;
	private JButton btnNewGame;
	private JButton btnPlayer1;
	private JButton btnPlayer2;
	private JButton btnEndGame;
	private JButton btnGo;
	private JButton btnSave;
	private JLabel lblUserWon;
	private JLabel lblPointsPlayer2;
	private JLabel lblPointsPlayer1;
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
					window.frame.setSize(window.frame.getWidth(), window.frame.getHeight()+window.frame.getInsets().top);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameWindow() {
		initialize();
		shapeChooserD = new ShapeChooserDialog(this);
		newGameD = new NewGameDialog(this);
		checkForSavedGame();
	}

	private void initialize() {
		frame = new JFrame("Rock Paper Scissors");
		frame.setResizable(false);
		frame.setBounds(100, 100, 750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameD.openDialog();
				frame.setEnabled(false);
			}
		});
		
		btnGo = new JButton("Go!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goAction();
			}
		});
		btnGo.setEnabled(false);
		btnGo.setBounds(347, 279, 167, 41);
		frame.getContentPane().add(btnGo);
		btnNewGame.setBounds(6, 338, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		btnSave = new JButton("Save & Close");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(false);
				try {
					Game.saveGame();
					System.exit(0);
				} catch (IOException e1) {
					System.err.println("Unable to save");
				}
			}
		});
		btnSave.setBounds(6, 309, 117, 29);
		frame.getContentPane().add(btnSave);
		
		btnPlayer1 = new JButton("");
		btnPlayer1.setEnabled(false);
		btnPlayer1.setIcon(loadIcon("question"));
		btnPlayer1.setBounds(263, 114, 89, 91);
		GameWindow.makeButtonRound(btnPlayer1);
		frame.getContentPane().add(btnPlayer1);
		
		btnPlayer2 = new JButton("");
		btnPlayer2.setEnabled(false);
		btnPlayer2.setIcon(loadIcon("question"));
		btnPlayer2.setBounds(513, 114, 89, 91);
		GameWindow.makeButtonRound(btnPlayer2);
		frame.getContentPane().add(btnPlayer2);
		
		btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}
		});
		btnEndGame.setEnabled(false);
		btnEndGame.setBounds(347, 319, 167, 41);
		frame.getContentPane().add(btnEndGame);
		
		lblUserWon = new JLabel("");
		lblUserWon.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUserWon.setForeground(Color.WHITE);
		lblUserWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserWon.setBounds(263, 245, 339, 35);
		frame.getContentPane().add(lblUserWon);
		
		lblPointsPlayer2 = new JLabel("Points: 0");
		lblPointsPlayer2.setForeground(Color.WHITE);
		lblPointsPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsPlayer2.setBounds(513, 217, 89, 16);
		frame.getContentPane().add(lblPointsPlayer2);
		
		lblPointsPlayer1 = new JLabel("Points: 0");
		lblPointsPlayer1.setForeground(Color.WHITE);
		lblPointsPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointsPlayer1.setBounds(263, 217, 89, 16);
		frame.getContentPane().add(lblPointsPlayer1);
		
		lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1.setForeground(Color.WHITE);
		lblPlayer1.setBounds(263, 87, 89, 16);
		frame.getContentPane().add(lblPlayer1);
		
		lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2.setForeground(Color.WHITE);
		lblPlayer2.setBounds(513, 87, 89, 16);
		frame.getContentPane().add(lblPlayer2);
		
		JLabel background = new JLabel(loadIcon("background"));
		background.setBounds(0, 0, frame.getWidth(),frame.getHeight());
		frame.getContentPane().add(background);
	}

	static public void makeButtonRound(JButton btn) {
		btn.setBorder (BorderFactory.createEmptyBorder (1, 1, 1, 1));
		btn.setBackground (Color.BLACK);
		btn.setContentAreaFilled (false);
		btn.setFocusPainted (false);
	}
	
	public JFrame getMainFrame(){
		return frame;
	}
	
	public void startGame(Player p1, Player p2){
		customizeUIForGame(p1, p2);
		Game.getInstance().addShape(new Paper());
		Game.getInstance().addShape(new Rock());
		Game.getInstance().addShape(new Scissors());
		Game.getInstance().startGame(p1, p2);
	}

	private void customizeUIForGame(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		if(p1 instanceof HumanPlayer){
			btnPlayer1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shapeChooserD.chooseMove((HumanPlayer) GameWindow.this.p1);
				}
			});
			btnPlayer1.setEnabled(true);
			btnGo.setText("Next Round");
		}else
			btnGo.setText("Go!");
		
		setPlayersBtnAsQuestion();
		btnNewGame.setEnabled(false);
		btnSave.setEnabled(true);
		btnGo.setEnabled(true);
		btnEndGame.setEnabled(true);
		lblPlayer1.setText(p1.getName());
		lblPlayer2.setText(p2.getName());
		lblUserWon.setText("");
		lblUserWon.setFont(null);
		updatePointslbls();
	}

	private void setPlayersBtnAsQuestion() {
		btnPlayer1.setIcon(loadIcon("question_on"));
		btnPlayer2.setIcon(loadIcon("question_on"));
		btnPlayer1.setDisabledIcon(loadIcon("question"));
		btnPlayer2.setDisabledIcon(loadIcon("question"));
	}

	private void updatePointslbls() {
		lblPointsPlayer1.setText("Points: "+p1.getPoints());
		lblPointsPlayer2.setText("Points: "+p2.getPoints());
	}
	
	public void humanHasChosen(Player player, Shape shape){
		setShapeForPlayer(player, shape);
		goAction();
	}
	
	private void setShapeForPlayer(Player player, Shape shape){
		if(player == p1){
			btnPlayer1.setIcon(loadIcon(shape.getClass().getSimpleName()+"_on"));
			btnPlayer1.setDisabledIcon(loadIcon(shape.getClass().getSimpleName()));
		}else if(player == p2){
			btnPlayer2.setIcon(loadIcon(shape.getClass().getSimpleName()+"_on"));
			btnPlayer2.setDisabledIcon(loadIcon(shape.getClass().getSimpleName()));
		}
	}
	
	public ImageIcon loadIcon(String name){
		return new ImageIcon("data/"+name.toLowerCase() + ".png");
	}
	
	private void goAction() {
		Player winner = null;
		if(p1 instanceof HumanPlayer){
			if(p1.getCurrentShape() == null){
				setPlayersBtnAsQuestion();
				lblUserWon.setText("");
				return;
			}
		}
		else
		{
			((ComputerPlayer)p1).randomizeShape();
			setShapeForPlayer(p1,p1.getCurrentShape());
		}
		((ComputerPlayer)p2).randomizeShape();
		setShapeForPlayer(p2,p2.getCurrentShape());
		winner = Game.getInstance().nextRound();
		
		if(winner!=null)
			lblUserWon.setText(winner.getName()+ " won round "+(Game.getInstance().getRound()-1)+"!");
		else lblUserWon.setText("Round "+(Game.getInstance().getRound()-1)+" ended with a tie.");
		
		updatePointslbls();
	}
	
	private void endGame() {
		Player winner = Game.getInstance().getWinner();
		if(winner!=null)
			lblUserWon.setText(winner.getName()+ " won this game!");
		else lblUserWon.setText("This game ended with a tie.");
		lblUserWon.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewGame.setEnabled(true);
		btnPlayer1.setEnabled(false);
		if(btnPlayer1.getActionListeners().length > 0)
			btnPlayer1.removeActionListener(btnPlayer1.getActionListeners()[0]);
		btnPlayer2.setEnabled(false);
		btnPlayer1.setIcon(loadIcon("question"));
		btnPlayer2.setIcon(loadIcon("question"));
		btnPlayer1.setDisabledIcon(null);
		btnPlayer2.setDisabledIcon(null);
		btnEndGame.setEnabled(false);
		btnGo.setEnabled(false);
		btnGo.setVisible(true);
		btnSave.setEnabled(false);
		Game.clearSavedGame();
		Game.resetGame();
	}
	
	private void checkForSavedGame() {
		try {
			Game.loadGame();
		} catch (ClassNotFoundException | IOException e) {
			return;
		}
		customizeUIForGame(Game.getInstance().getPlayers()[0], Game.getInstance().getPlayers()[1]);
	}
}
