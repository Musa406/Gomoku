package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public  class WelcomeBoard extends JFrame{
	
	public static JPanel boardPanel;
	public static JPanel setupPanel;

	
	private final JButton buttonStart;

	public WelcomeBoard(int width, int height, String title) {
		
		
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setupPanel = new JPanel();
		setupPanel.setLayout(null);
		buttonStart = new JButton("Start Game");
		setupPanel.setSize(width, height);
		buttonStart.setBounds(185, 120, 100, 50);
		setupPanel.add(buttonStart);
		
		JTextField t = new JTextField("Team_Muhabbat");
		t.setBounds(135, 300, 220, 50);
		t.setBackground(new Color(180,156,111));
		t.setFont(new Font("Tahoma", Font.BOLD, 25));
		setupPanel.add(t);
		
		setupPanel.setBackground(new Color(180,156,111));
		
		
		
		add(setupPanel);	
	
	}

	
	public void listenGameStartButton(ActionListener listener) {
		buttonStart.addActionListener(listener);
	}
	
	public void attachBoard(JPanel board) {
		boardPanel = board;
	}
	
	public void showBoard() {
		setContentPane(boardPanel);
		invalidate();
		validate();
	}	
}
