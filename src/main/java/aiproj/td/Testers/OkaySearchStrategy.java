package aiproj.td.Testers;

import java.util.ArrayList;
import java.util.Random;

import aiproj.slider.Move;
import aiproj.td.SearchStrategy.SearchStrategy;
import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

public class OkaySearchStrategy extends SearchStrategy {

	private SliderBoardPiece.PieceType team;
	
	public OkaySearchStrategy(SliderBoardPiece.PieceType team)
	{
		this.team = team;
	}
	
	@Override
	public Move getBestMove(SliderBoard board) {
		ArrayList<Move> moves = getAllPossibleMoves(board, team);
		
		Random r = new Random();
		
		// Return a forward move if we can.
		for (Move m : moves)
		{
			if (team == SliderBoardPiece.PieceType.V && m.d == Move.Direction.UP)
				return m;
			if (team == SliderBoardPiece.PieceType.H && m.d == Move.Direction.RIGHT)
				return m;
		}
		
		if (moves.size() != 0)
			return moves.get(r.nextInt(moves.size()));
		return null;
	}

}
