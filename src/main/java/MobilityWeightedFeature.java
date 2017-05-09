/**
 * Created by daewin on 0004, May, 4.
 */
public class MobilityWeightedFeature implements Feature, PieceMovement {

    private int weight;
    public SliderBoardPiece.PieceType ourPlayerType;

    public MobilityWeightedFeature(SliderBoardPiece.PieceType playerType){
        this.ourPlayerType = playerType;
    }


    /**
     * Mobility is a potential candidate where it looks at the number of collective moves that the pieces can make.
     * Subtract our opponents’ mobility value from this, so that we have a gauge of whether this move benefits us
     * more than our opponent.
     * @param board
     * @return
     */
    @Override
    public int evaluate(SliderBoard board) {

        int ourMoves = SearchStrategy.getAllPossibleMoves(board, ourPlayerType).size();
        int opponentMoves = SearchStrategy.
                getAllPossibleMoves(board, SliderBoardPiece.oppositePieceType(ourPlayerType)).size();

        return ourMoves - opponentMoves;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
