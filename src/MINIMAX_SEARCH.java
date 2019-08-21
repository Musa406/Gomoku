import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MINIMAX_SEARCH{

	
	Object[] mmab(GAME_BOARD board, int d, double myBest, double theirBest) {
		ArrayList<String> moveList;
		Set<String> moves = new HashSet<String>();
		
		moves.retainAll(board.getEmpties());
		
		if (moves.isEmpty())
			moveList = new ArrayList<String>(board.getEmpties());
		else
			moveList = new ArrayList<String>(moves);

		Double bestScore;
		Object[] temp;
		Double tempScore;
		String bestMove = "";

		
		
		bestScore = myBest;
		while (moveList.size() > 0) {
			GAME_BOARD newBoard = new GAME_BOARD(board);
			String newMove = moveList.get(0);
			
			temp = mmab(newBoard, d - 1, -theirBest, -bestScore);
			tempScore = -(Double) temp[0];
			if (tempScore > bestScore) {
				bestScore = tempScore;
				bestMove = newMove;
			}
			if (bestScore > theirBest) {
				Object[] x = { bestScore, bestMove };
				return x;
			}
			moveList.remove(0);
		}
		Object[] x = { bestScore, bestMove };
		return x;
	}
}