
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class GAME_BOARD {
	char[][] board;
	int n;
	int m;
	char nextPlayer;
	char prevPlayer;
	char winner;
	String lastMove;

	
	public GAME_BOARD(int n, int m) {
		this.n = n;
		this.m = m;
		this.board = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = '.';
			}
		}
		this.winner = '.';
	}

	
	public GAME_BOARD(GAME_BOARD other) {
		this.n = other.n;
		this.m = other.m;
		this.board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
		this.nextPlayer = other.nextPlayer;
		this.prevPlayer = other.prevPlayer;
	}

	
	Set<String> getEmpties() {
		Set<String> ems = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					ems.add(i + " " + j);
				}

			}
		}
		return ems;
	}


	
}
