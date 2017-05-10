package aiproj.td.Feature;

import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * Created by Daewin, Tom
 */
public class NumPiecesFeature implements Feature {

    private float weight;
    public SliderBoardPiece.PieceType ourPlayerType;

    public NumPiecesFeature(SliderBoardPiece.PieceType playerType){
        this.ourPlayerType = playerType;
        weight = 1.0f;
    }

    public NumPiecesFeature(SliderBoardPiece.PieceType playerType, float weight){
        this.ourPlayerType = playerType;
        this.weight = weight;
    }

    /**
     * Looks at the number of pieces each team has left. Less pieces the better.
     * @param board
     * @return
     */
    @Override
    public float evaluate(SliderBoard board) {

    	int numV = 0, numH = 0;

		for (SliderBoardPiece piece : board.hashPieces.values())
		{
			if (piece.type == SliderBoardPiece.PieceType.V)
				numV++;
			else if (piece.type == SliderBoardPiece.PieceType.H)
				numH++;
		}


        // Normalize it
        float normalizedEvaluationValue;

        if (ourPlayerType == SliderBoardPiece.PieceType.V) {
            normalizedEvaluationValue = (numH - numV)/(float)(numV + numH);
        } else {
            normalizedEvaluationValue = (numV - numH)/(float)(numV + numH);
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
