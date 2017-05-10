package aiproj.td.Feature;

import aiproj.td.AI.PieceMovement;
import aiproj.td.SearchStrategy.SearchStrategy;
import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * Created by daewin on 0004, May, 4.
 */
public class MobilityWeightedFeature implements Feature, PieceMovement {

    private float weight;
    public SliderBoardPiece.PieceType ourPlayerType;

    public MobilityWeightedFeature(SliderBoardPiece.PieceType playerType){
        this.ourPlayerType = playerType;
        weight = 0.6f;
    }

    public MobilityWeightedFeature(SliderBoardPiece.PieceType playerType, float weight){
        this.ourPlayerType = playerType;
        this.weight = weight;
    }


    /**
     * Mobility is a potential candidate where it looks at the number of collective moves that the pieces can make.
     * Subtract our opponents’ mobility value from this, so that we have a gauge of whether this move benefits us
     * more than our opponent.
     * @param board
     * @return
     */
    @Override
    public float evaluate(SliderBoard board) {

        int ourMoves = SearchStrategy.getAllPossibleMoves(board, ourPlayerType).size();
        int opponentMoves = SearchStrategy.
                getAllPossibleMoves(board, SliderBoardPiece.oppositePieceType(ourPlayerType)).size();

        // Normalize it
        float normalizedEvaluationValue = (ourMoves - opponentMoves)/(float)(ourMoves+opponentMoves);

        return normalizedEvaluationValue;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
