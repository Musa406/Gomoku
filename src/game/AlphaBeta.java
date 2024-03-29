package game;

import java.util.ArrayList;


public class AlphaBeta {
	
	
	private GomokuBoard board;
	private static final int winScoreValue = 1000;
	
	  
	public AlphaBeta(GomokuBoard board) {
		this.board = board;
	}  
	
	public static int getWinScore() {
		return winScoreValue;
	}
	public static double evaluateBoardForWhite(GomokuBoard board, boolean blacksTurn) {
		
		double blackScore = getScore(board, true, blacksTurn);
		double whiteScore = getScore(board, false, blacksTurn);
		
		if(blackScore == 0) blackScore = 1.0;
		
		return whiteScore / blackScore;
		
	}
	public static int getScore(GomokuBoard board, boolean forBlack, boolean blacksTurn) {
		
		
		int[][] boardMatrix = board.getBoardMatrix();
		
		return evaluateHorizontal(boardMatrix, forBlack, blacksTurn) +
				evaluateVertical(boardMatrix, forBlack, blacksTurn) +
				evaluateDiagonal(boardMatrix, forBlack, blacksTurn);
	}
	
	public int[] calculateNextMove(int depth) {
		
		int[] move = new int[2];
		
		
		Object[] bestMove = searchWinningMove(board); 
		
		if(bestMove != null ) {
			System.out.println("Finisher!");
			move[0] = (Integer)(bestMove[1]);
			move[1] = (Integer)(bestMove[2]);
			
		} else {
			
			bestMove = minimaxSearchAB(depth, board, true, -1.0, getWinScore());
			
			if(bestMove[1] == null) {
				move = null;
			} else {
				move[0] = (Integer)(bestMove[1]);
				move[1] = (Integer)(bestMove[2]);
			}
		}
		
		
		return move;
		
		
	}
	
	
	
	private static Object[] minimaxSearchAB(int depth, GomokuBoard board, boolean max, double alpha, double beta) {
		
		Object[] bestMove = new Object[3];
		
		if(depth == 0) {
			
			Object[] x = {evaluateBoardForWhite(board,!max), null, null};
			return x;
		}
		
		ArrayList<int[]> allPossibleMoves = board.generateMoves();
		 
		if(allPossibleMoves.size() == 0) {
			
			Object[] x = {evaluateBoardForWhite(board, !max), null, null};
			return x;
		}
		
		
		
		
		if(max) {
			bestMove[0] = -1.0;
			
			for(int[] move : allPossibleMoves) {
				
				GomokuBoard dummyBoard = new GomokuBoard(board);
				
				dummyBoard.addStoneNoGUI(move[1], move[0], false);
				
				
				Object[] tempMove = minimaxSearchAB(depth-1, dummyBoard, !max, alpha, beta);
				
				
				if((Double)(tempMove[0]) > alpha) {
					alpha = (Double)(tempMove[0]);
				}
				
				if((Double)(tempMove[0]) >= beta) {
					return tempMove;
				}
				if((Double)tempMove[0] > (Double)bestMove[0]) {
					bestMove = tempMove;
					
					bestMove[1] = move[0];
					bestMove[2] = move[1];
				}
			}
			
		}
		else {
			bestMove[0] = 100000000.0;
			bestMove[1] = allPossibleMoves.get(0)[0];
			bestMove[2] = allPossibleMoves.get(0)[1];
			for(int[] move : allPossibleMoves) {
				
				GomokuBoard dummyBoard = new GomokuBoard(board);
				dummyBoard.addStoneNoGUI(move[1], move[0], true);
				
				Object[] tempMove = minimaxSearchAB(depth-1, dummyBoard, !max, alpha, beta);
				
				
				if(((Double)tempMove[0]) < beta) {
					beta = (Double)(tempMove[0]);
				}
				// Pruning with alpha
				if((Double)(tempMove[0]) <= alpha) {
					return tempMove;
				}
				if((Double)tempMove[0] < (Double)bestMove[0]) {
					bestMove = tempMove;
					
					bestMove[1] = move[0];
					bestMove[2] = move[1];
				}
			}
		}
		
		return bestMove;
	}
	
	private static Object[] searchWinningMove(GomokuBoard board) {
		
		ArrayList<int[]> allPossibleMoves = board.generateMoves();
		
		Object[] winningMove = new Object[3];
		
		
		for(int[] move : allPossibleMoves) {
			
			
			GomokuBoard tempBoard = new GomokuBoard(board);
			
			tempBoard.addStoneNoGUI(move[1], move[0], false);
			
			
			if(getScore(tempBoard,false,false) >= winScoreValue) {
				winningMove[1] = move[0];
				winningMove[2] = move[1];
				return winningMove;
			}
		}
		return null;
		
	}

	public static int evaluateHorizontal(int[][] boardMatrix, boolean forBlack, boolean playersTurn ) {
		
		int consecutive = 0;
		int blocks = 2;
		int score = 0;
		
		for(int i=0; i<boardMatrix.length; i++) {
			for(int j=0; j<boardMatrix[0].length; j++) {
				
				if(boardMatrix[i][j] == (forBlack ? 2 : 1)) {
					consecutive++;
				}
				
				else if(boardMatrix[i][j] == 0) {
					if(consecutive > 0) {
						blocks--;
						score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
						consecutive = 0;
						blocks = 1;
					}
					else {
						blocks = 1;
					}
				}
				else if(consecutive > 0) {
					score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
					consecutive = 0;
					blocks = 2;
				}
				else {
					blocks = 2;
				}
			}
			if(consecutive > 0) {
				score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
				
			}
			consecutive = 0;
			blocks = 2;
			
		}
		return score;
	}
	
	public static  int evaluateVertical(int[][] boardMatrix, boolean forBlack, boolean playersTurn ) {
		
		int consecutive = 0;
		int blocks = 2;
		int score = 0;
		
		for(int j=0; j<boardMatrix[0].length; j++) {
			for(int i=0; i<boardMatrix.length; i++) {
				
				if(boardMatrix[i][j] == (forBlack ? 2 : 1)) {
					consecutive++;
				}
				  
				else if(boardMatrix[i][j] == 0) {
					if(consecutive > 0) {
						blocks--;
						score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
						consecutive = 0;
						blocks = 1;
					}
					else {
						blocks = 1;
					}
				}
				
				else if(consecutive > 0) {
					score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
					consecutive = 0;
					blocks = 2;
				}
				else {
					blocks = 2;
				}
			}
			if(consecutive > 0) {
				score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
				
			}
			consecutive = 0;
			blocks = 2;
			
		}
		return score;
	}
	public static  int evaluateDiagonal(int[][] boardMatrix, boolean forBlack, boolean playersTurn ) {
		
		int consecutive = 0;
		int blocks = 2;
		int score = 0;
		
		for (int k = 0; k <= 2 * (boardMatrix.length - 1); k++) {
		    int iStart = Math.max(0, k - boardMatrix.length + 1);
		    int iEnd = Math.min(boardMatrix.length - 1, k);
		    for (int i = iStart; i <= iEnd; ++i) {
		        int j = k - i;
		        
		        if(boardMatrix[i][j] == (forBlack ? 2 : 1)) {
					consecutive++;
				}
				else if(boardMatrix[i][j] == 0) {
					if(consecutive > 0) {
						blocks--;
						score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
						consecutive = 0;
						blocks = 1;
					}
					else {
						blocks = 1;
					}
				}
				else if(consecutive > 0) {
					score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
					consecutive = 0;
					blocks = 2;
				}
				else {
					blocks = 2;
				}
		        
		    }
		    if(consecutive > 0) {
				score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
				
			}
			consecutive = 0;
			blocks = 2;
		}
		// From top-left to bottom-right diagonally
		for (int k = 1-boardMatrix.length; k < boardMatrix.length; k++) {
		    int iStart = Math.max(0, k);
		    int iEnd = Math.min(boardMatrix.length + k - 1, boardMatrix.length-1);
		    for (int i = iStart; i <= iEnd; ++i) {
		        int j = i - k;
		        
		        if(boardMatrix[i][j] == (forBlack ? 2 : 1)) {
					consecutive++;
				}
				else if(boardMatrix[i][j] == 0) {
					if(consecutive > 0) {
						blocks--;
						score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
						consecutive = 0;
						blocks = 1;
					}
					else {
						blocks = 1;
					}
				}
				else if(consecutive > 0) {
					score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
					consecutive = 0;
					blocks = 2;
				}
				else {
					blocks = 2;
				}
		        
		    }
		    if(consecutive > 0) {
				score += getConsecutiveScore(consecutive, blocks, forBlack == playersTurn);
				
			}
			consecutive = 0;
			blocks = 2;
		}
		return score;
	}
	public static  int getConsecutiveScore(int count, int blocks, boolean currentTurn) {
	
		
		if(blocks == 2 && count < 5) return 0;
	
		switch(count) {
		case 5: {
			return winScoreValue;
		}
		case 4: {
			if(currentTurn) return 100;
			else {
				if(blocks == 0) return 75;
				else return 20;
			}
		}
		case 3: {
			if(blocks == 0) {
				if(currentTurn) return 50;
				else return 20;
			}
			else {
				if(currentTurn) return 10;
				else return 5;
			}
		}
		case 2: {
			if(blocks == 0) {
				if(currentTurn) return 7;
				else return 5;
			}
			else {
				return 3;
			}
		}
		case 1: {
			return 1;
		}
		}
		return winScoreValue*2;
	}
}