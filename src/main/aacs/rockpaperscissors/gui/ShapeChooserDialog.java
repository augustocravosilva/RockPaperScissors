package aacs.rockpaperscissors.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aacs.rockpaperscissors.gamelogic.Game;
import aacs.rockpaperscissors.gamelogic.HumanPlayer;
import aacs.rockpaperscissors.gamelogic.Paper;
import aacs.rockpaperscissors.gamelogic.Rock;
import aacs.rockpaperscissors.gamelogic.Scissors;
import aacs.rockpaperscissors.gamelogic.Shape;

public class ShapeChooserDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCircle;
	private JButton btnScissors;
	private JButton btnPaper;
	private JButton btnRock;
	private GameWindow parent;
	private HumanPlayer currentPlayer;
	
	public ShapeChooserDialog(GameWindow parent) {
		super(parent.getMainFrame());
		this.parent = parent;
		setResizable(false);
		setBounds(100, 100, 309, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		btnScissors = new JButton("");
		btnScissors.setIcon(new ImageIcon("data/scissors_on.png"));
		btnScissors.setBounds(6, 76, 89, 91);
		GameWindow.makeButtonRound(btnScissors);
		contentPane.add(btnScissors);
		
		btnPaper = new JButton("");
		btnPaper.setIcon(new ImageIcon("data/paper_on.png"));
		btnPaper.setBounds(212, 66, 89, 91);
		GameWindow.makeButtonRound(btnPaper);
		contentPane.add(btnPaper);
		
		btnRock = new JButton("");
		btnRock.setIcon(new ImageIcon("data/rock_on.png"));
		btnRock.setBounds(112, 262, 89, 91);
		GameWindow.makeButtonRound(btnRock);
		contentPane.add(btnRock);
		
		lblCircle = new JLabel(new ImageIcon("data/game.png"));
		lblCircle.setBounds(8, 51, 280, 300);
		contentPane.add(lblCircle);
		
		JLabel background = new JLabel(new ImageIcon("data/background.png"));
		background.setBounds(0, 0, 323, 400);
		contentPane.add(background);
		
		btnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectShape(Game.getInstance().getShape(Scissors.class));
			}
		});
		
		btnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectShape(Game.getInstance().getShape(Paper.class));

			}
		});
		
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectShape(Game.getInstance().getShape(Rock.class));
			}
		});
		
	}
	
	public void chooseMove(HumanPlayer player){
		this.setVisible(true);
		currentPlayer = player;		
	}
	
	private void selectShape(Shape shape) {
		currentPlayer.setCurrentShape(shape);
		ShapeChooserDialog.this.setVisible(false);
		ShapeChooserDialog.this.parent.humanHasChosen(currentPlayer, shape);
	}
	
}
