package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
	public static void main(String[] args) {
		
		final int width = 500;
		final WelcomeBoard gui = new WelcomeBoard(width,width, "GoMoku");
		
		GomokuBoard board = new GomokuBoard(width, 10);
		
		final GameClass game = new GameClass(board);
		
		gui.attachBoard(board.getGUI());
		
		gui.setVisible(true);
		gui.setResizable(true);
	
		gui.listenGameStartButton(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {	
				  
				gui.showBoard();
				
				game.start();
				
			}
			
		});
	
	}
}
