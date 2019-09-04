package game;
import java.awt.event.MouseListener;
import java.util.ArrayList;



public class GomokuBoard {
	
	private PlayingBoard gui;
	private int[][] boardMatrix;
	
	
	public GomokuBoard(int boardSize, int noOfCell) {
		gui = new PlayingBoard(boardSize, noOfCell);
		boardMatrix = new int[noOfCell][noOfCell];
		
	}
	
	public GomokuBoard(GomokuBoard board) {
		
		int[][] matrixToCopy = board.getBoardMatrix();
		boardMatrix = new int[matrixToCopy.length][matrixToCopy.length];
		
		for(int i=0;i<matrixToCopy.length; i++) {
			for(int j=0; j<matrixToCopy.length; j++) {
				boardMatrix[i][j] = matrixToCopy[i][j];
			}
		}
	}
	public int getnoOfCell() {
		return boardMatrix.length;
	}
	public void addStoneNoGUI(int X, int Y, boolean black) {
		boardMatrix[Y][X] = black ? 2 : 1;
	}
	public boolean addStone(int X, int Y, boolean black) {
		
		
		if(boardMatrix[Y][X] != 0) return false;
		
		gui.drawStone(X, Y, black);
		boardMatrix[Y][X] = black ? 2 : 1;
		return true;
		
	}
	
	public ArrayList<int[]> generateMoves() {
		
		ArrayList<int[]> moveList = new ArrayList<int[]>();
		
		int noOfCell = boardMatrix.length;
		
		
		
		for(int i=0; i<noOfCell; i++) {
			for(int j=0; j<noOfCell; j++) {
				
				if(boardMatrix[i][j] > 0) continue;
				
				if(i > 0) {
					if(j > 0) {
						if(boardMatrix[i-1][j-1] > 0 ||
						   boardMatrix[i][j-1] > 0) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(j < noOfCell-1) {
						if(boardMatrix[i-1][j+1] > 0 ||
						   boardMatrix[i][j+1] > 0) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(boardMatrix[i-1][j] > 0) {
						int[] move = {i,j};
						moveList.add(move);
						continue;
					}
				}
				if( i < noOfCell-1) {
					if(j > 0) {
						if(boardMatrix[i+1][j-1] > 0 ||
						   boardMatrix[i][j-1] > 0) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(j < noOfCell-1) {
						if(boardMatrix[i+1][j+1] > 0 ||
						   boardMatrix[i][j+1] > 0) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(boardMatrix[i+1][j] > 0) {
						int[] move = {i,j};
						moveList.add(move);
						continue;
					}
				}
				
			}
		}

		return moveList;
		
	}
	public int[][] getBoardMatrix() {
		return boardMatrix;
	}
	
	public void startListening(MouseListener listener) {
		gui.attachListener(listener);
	}
	public PlayingBoard getGUI() {
		return gui;
	}
	public int getRelativePos(int x) {
		return gui.getRelativePos(x);
	}
	
}