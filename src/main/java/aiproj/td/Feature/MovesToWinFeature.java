package aiproj.td.Feature;

import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * Created by daewin, tdove 
 */
public class MovesToWinFeature implements Feature {

    private float weight;
    public SliderBoardPiece.PieceType ourPlayerType;

    public MovesToWinFeature(SliderBoardPiece.PieceType playerType){
        this.ourPlayerType = playerType;
        weight = 1.0f;
    }

    public MovesToWinFeature(SliderBoardPiece.PieceType playerType, float weight){
        this.ourPlayerType = playerType;
        this.weight = weight;
    }

    /**
     * Moves to win considers the minimum number of moves that each team needs to get all
     * of their pieces to the end of the board, without considering blocked pieces.
     * Takes the difference to compare with our opponent.
     * @param board
     * @return
     */
    @Override
    public float evaluate(SliderBoard board) {

    	int distV = 0;
		int distH = 0;

		for (SliderBoardPiece piece : board.hashPieces.values())
		{
			if (piece.type == SliderBoardPiece.PieceType.V)
			{
				distV += board.dimension - piece.position.j; 
			}
			else if (piece.type == SliderBoardPiece.PieceType.H)
			{
				distH += board.dimension - piece.position.i;
			}	
		}

        // Normalize it
        float normalizedEvaluationValue;

        if (ourPlayerType == SliderBoardPiece.PieceType.V) {
            normalizedEvaluationValue = (distH - distV)/(float)(distV + distH);
        } else {
            normalizedEvaluationValue = (distV - distH)/(float)(distV + distH);
        }


        return normalizedEvaluationValue;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

	public float getWeight() {
		return weight;
	}
}
