package aiproj.td.Testers;

import java.util.ArrayList;
import java.util.Random;

import aiproj.slider.Move;
import aiproj.td.SearchStrategy.SearchStrategy;
import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

public class RandomSearchStrategy extends SearchStrategy {

	private SliderBoardPiece.PieceType team;
	
	public RandomSearchStrategy(SliderBoardPiece.PieceType team)
	{
		this.team = team;
	}
	
	@Override
	public Move getBestMove(SliderBoard board) {
		ArrayList<Move> moves = getAllPossibleMoves(board, team);
		
		Random r = new Random();
		
		if (moves.size() != 0)
			return moves.get(r.nextInt(moves.size()));
		return null;
	}

}
