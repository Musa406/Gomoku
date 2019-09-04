 package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameClass extends JFrame{

	private GomokuBoard board;
	private boolean isPlayersTurn = true;
	private boolean gameFinished = false;
	private int minimaxDepth = 3;
	private AlphaBeta computer;
	private int winner; 
	
	
	public GameClass(GomokuBoard board) {
		this.board = board;
		computer = new AlphaBeta(board);
		winner = 0;
	}
	
	public void start() {
				
		
		board.startListening(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				if(isPlayersTurn) {
					isPlayersTurn = false;
					
					Thread mouseClickThread = new Thread(new MouseClickHandler(arg0));
					mouseClickThread.start();
					
					
				}
			}
  
			public void mouseEntered(MouseEvent arg0) {}

			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent arg0) {}
			
			
			
		});
		
		
		
	}
	

	public class MouseClickHandler implements Runnable{
		MouseEvent e;
		public MouseClickHandler(MouseEvent e) {
			this.e = e;
		}
		public void run() {
			if(gameFinished) return;
			
	
			int X = board.getRelativePos( e.getX() );
			int Y = board.getRelativePos( e.getY() );
			
			// Place a black stone(2) to that cell.
			if(!playMove(X, Y, true)) {
				
				isPlayersTurn = true;
				return;
			}
			
			
			winner = checkingWinner();
			
			if(winner == 2) {
				System.out.println("Player WON!");
			
				JOptionPane.showMessageDialog(null, "Player WON!");
				
				gameFinished = true;
				
				return;
			}
			
			
			int[] computerMove = computer.calculateNextMove(minimaxDepth);
			
			if(computerMove == null) {
				System.out.println("No possible moves left. Game Over.");
				//board.printWinner(0); // Prints "TIED!"
				JOptionPane.showMessageDialog(null, "No possible moves left. Game Over.!");
				gameFinished = true;
				return;
			}
			
			
			// Place a white stone to the found cell.
			playMove(computerMove[1], computerMove[0], false);
			
			System.out.println("Black: " + AlphaBeta.getScore(board,true,true) + " White: " + AlphaBeta.getScore(board,false,true));
			
			winner = checkingWinner();
			
			if(winner == 1) {
				System.out.println("Computer WON!");
				//board.printWinner(winner);
				gameFinished = true;
				JOptionPane.showMessageDialog(null, "Computer Won!!!");
				return;
			}
			
			if(board.generateMoves().size() == 0) {
				System.out.println("No possible moves left. Game Over.");
				//board.printWinner(0); // Prints "TIED!"
				gameFinished = true;
				JOptionPane.showMessageDialog(null, "No possible moves left. Game Over");
				return;
				
			}
			
			isPlayersTurn = true;
		}
		
	}
	
	private int checkingWinner() {
		
		if(isPlayersTurn) {
			if(AlphaBeta.getScore(board, true, false) >= AlphaBeta.getWinScore()) return 2;
		}
		
		else 
			if(AlphaBeta.getScore(board, false, true) >= AlphaBeta.getWinScore()) return 1;
		return 0;
	}
	private boolean playMove(int X, int Y, boolean is_black) {
		return board.addStone(X, Y, is_black);
	}
	
}