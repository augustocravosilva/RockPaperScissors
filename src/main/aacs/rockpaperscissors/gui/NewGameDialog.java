package aacs.rockpaperscissors.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import aacs.rockpaperscissors.gamelogic.ComputerPlayer;
import aacs.rockpaperscissors.gamelogic.HumanPlayer;
import aacs.rockpaperscissors.gamelogic.Player;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGameDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField txtPlayer2;
	private JTextField txtPlayer1;
	private GameWindow parent;
	private String namePlayer1;
	private String namePlayer2;
	private boolean alreadyShowed;

	public NewGameDialog(GameWindow parent) {
		super(parent.getMainFrame());
		setResizable(false);
		this.parent = parent;
		setBounds(100, 100, 352, 155);
		getContentPane().setLayout(null);
		
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setOpaque(false);
		mainContainer.setBounds(0, 0, this.getWidth(),this.getHeight());
		
		contentPanel = new JPanel();		
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainContainer.add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		JRadioButton rdbtnHumanComputer = new JRadioButton("Human v Computer");
		rdbtnHumanComputer.setOpaque(false);
		rdbtnHumanComputer.setSelected(true);
		contentPanel.add(rdbtnHumanComputer);

		JRadioButton rdbtnComputerComputer = new JRadioButton("Computer v Computer");
		rdbtnComputerComputer.setOpaque(false);
		contentPanel.add(rdbtnComputerComputer);

		JLabel lblPlayer1Name = new JLabel("Player 1 Name:");
		lblPlayer1Name.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblPlayer1Name);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnHumanComputer);
		bg.add(rdbtnComputerComputer);
		txtPlayer1 = new JTextField();
		txtPlayer1.setText("Player 1");
		contentPanel.add(txtPlayer1);
		txtPlayer1.setColumns(10);
		JLabel lblPlayer2Name = new JLabel("Player 2 Name:");
		lblPlayer2Name.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblPlayer2Name);
		txtPlayer2 = new JTextField();
		txtPlayer2.setText("Player 2");
		contentPanel.add(txtPlayer2);
		txtPlayer2.setColumns(10);


		JPanel buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mainContainer.add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namePlayer1 = txtPlayer1.getText();
				namePlayer2 = txtPlayer2.getText();
				if(namePlayer1 == "")
					namePlayer1 = "Player 1";
				if(namePlayer2 == "")
					namePlayer2 = "Player 2";
				setVisible(false);
				NewGameDialog.this.parent.getMainFrame().setEnabled(true);
				Player p1;
				Player p2;
				if(rdbtnHumanComputer.isSelected())
					p1 = new HumanPlayer(namePlayer1);
				else
					p1 = new ComputerPlayer(namePlayer1);
				p2 = new ComputerPlayer(namePlayer2);
				NewGameDialog.this.parent.startGame(p1, p2);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NewGameDialog.this.parent.getMainFrame().setEnabled(true);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		getContentPane().add(mainContainer);

		JLabel background = new JLabel(new ImageIcon("data/background.png"));
		background.setBounds(0, 0, 400, 400);
		getContentPane().add(background);
	}
	
	public void openDialog(){
		this.setVisible(true);
		if (!alreadyShowed){
			this.setSize(this.getWidth(), this.getHeight()+this.getInsets().top);
			alreadyShowed = true;
		}
	}

}
