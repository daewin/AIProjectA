import aiproj.slider.Move;

import java.util.ArrayList;

/**
 * Created by daewi on 0001, May, 1.
 */
public class MinimaxSearchStrategy extends SearchStrategy {

    // List of our desired features to build our evaluation function
    private ArrayList<Feature> evaluationFunction = new ArrayList<>();

    private SliderBoardPiece.PieceType ourPlayerType;
    private SliderBoardPiece.PieceType opponentPlayerType;

    public MinimaxSearchStrategy(SliderBoardPiece.PieceType playerType){

        ourPlayerType = playerType;
        opponentPlayerType = SliderBoardPiece.oppositePieceType(playerType);

        evaluationFunction.add(new MobilityWeightedFeature(ourPlayerType));
    }


    @Override
    public Move getBestMove(SliderBoard initialBoard) {

        World.printBoard(initialBoard);

        ComplexSearchObjectMaxi bestMove = maxi(initialBoard, 10);

        if(bestMove != null){
            return bestMove.getMove();
        } else {
            return null;
        }

    }

    /**
     *
     * @param board
     * @param level
     * @return
     */
    private ComplexSearchObjectMaxi maxi(SliderBoard board, int level){

        if(level == 0){
            int evaluationValue = getNormalizedEvaluation(board, evaluationFunction);
            return new ComplexSearchObjectMaxi(evaluationValue);
        }

        ComplexSearchObjectMaxi max = new ComplexSearchObjectMaxi(Integer.MIN_VALUE);

        for(Move nextMove : getAllPossibleMoves(board, ourPlayerType)){

            SliderBoard mutatedBoard = SliderBoardMutator.mutateBoard(board, nextMove);

            ComplexSearchObjectMini score = mini(mutatedBoard, level-1);

            if(score.miniValue > max.maxiValue){
                max.maxiValue = score.miniValue;
                max.setMove(nextMove);
            }
        }

        return max;
    }

    /**
     *
     * @param board
     * @param level
     * @return
     */
    private ComplexSearchObjectMini mini(SliderBoard board, int level){

        if(level == 0){
            int evaluationValue = getNormalizedEvaluation(board, evaluationFunction);
            return new ComplexSearchObjectMini(evaluationValue);
        }

        ComplexSearchObjectMini min = new ComplexSearchObjectMini(Integer.MAX_VALUE);

        for(Move nextMove : getAllPossibleMoves(board, opponentPlayerType)){

            SliderBoard mutatedBoard = SliderBoardMutator.mutateBoard(board, nextMove);

            ComplexSearchObjectMaxi score = maxi(mutatedBoard, level-1);

            if(score.maxiValue < min.miniValue){
                min.miniValue = score.maxiValue;
            }
        }

        return min;
    }



    private class ComplexMinimaxSearchObject {

        private Move move;

        public ComplexMinimaxSearchObject(){
            this.move = null;
        }

        public void setMove(Move move) {
            this.move = move;
        }

        public Move getMove() {
            return move;
        }
    }


    private class ComplexSearchObjectMini extends ComplexMinimaxSearchObject {
        private int miniValue;

        public ComplexSearchObjectMini(int miniValue){
            super();
            this.miniValue = miniValue;
        }

    }

    private class ComplexSearchObjectMaxi extends ComplexMinimaxSearchObject {
        private int maxiValue;

        public ComplexSearchObjectMaxi(int maxValue){
            super();
            this.maxiValue = maxValue;
        }
    }
}























