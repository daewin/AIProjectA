package aiproj.td.Feature;

import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * Created by daewin, tom
 */
public class BlockedPiecesFeature implements Feature {

	private float weight;
	public SliderBoardPiece.PieceType ourPlayerType;

	public BlockedPiecesFeature(SliderBoardPiece.PieceType playerType){
		this.ourPlayerType = playerType;
		weight = -1.0f;
	}

	public BlockedPiecesFeature(SliderBoardPiece.PieceType playerType, int weight){
		this.ourPlayerType = playerType;
		this.weight = weight;
	}

	/**
	 * Blocked Pieces Feature considers the pieces that are blocked by blocked pieces.
	 * The higher number of blocked pieces you have the worse off you are.
	 * @param board
	 * @return
	 */
	@Override
	public float evaluate(SliderBoard board) {
		int numBlocked = 0, x, y;
		SliderBoardPiece piece;

		// For each blocked piece, count the number of pieces that it blocks.
		for (SliderBoardPiece p : board.hashPieces.values()) {
			if (p.type == SliderBoardPiece.PieceType.B) {
				if (ourPlayerType == SliderBoardPiece.PieceType.H) {
					// Check pieces in the same row to the left.
					y = p.position.j;

					for (x = p.position.i; x >= 0; x--) {
						if ( (piece = board.findPiece(x, y)) != null && piece.type == SliderBoardPiece.PieceType.H)
							numBlocked++;
					}
				} else {
					// Check pieces in the same column below the blocked piece.
					x = p.position.i;
					for (y = p.position.j; y >= 0; y--) {
						if ( (piece = board.findPiece(x, y)) != null && piece.type == SliderBoardPiece.PieceType.V)
							numBlocked++;
					}
				}
			}
		}

		return numBlocked;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getWeight() {
		return weight;
	}
}
