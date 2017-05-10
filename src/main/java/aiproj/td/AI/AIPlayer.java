package aiproj.td.AI;

import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;
import aiproj.td.InvalidMoveException;
import aiproj.td.SearchStrategy.*;
import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardMutator;
import aiproj.td.SliderBoard.SliderBoardPiece;


/**
 * Created by daewin on 0021, April 21.
 */
public class AIPlayer implements SliderPlayer, PieceMovement {

    // Instance variables for our agent
    private SliderBoard aiBoard;
    private SliderBoardPiece.PieceType aiPlayerType;
    private SearchStrategy currentStrategy;

    /**
     *
     * @param dimension The width and height of the board in cells
     * @param board A string representation of the initial state of the board,
     * as described in the part B specification
     * @param player 'H' or 'V', corresponding to which pieces the player will
     */
    @Override
    public void init(int dimension, String board, char player) {

        // Set our players type
        if(player == 'H'){
            aiPlayerType = SliderBoardPiece.PieceType.H;
        } else {
            aiPlayerType = SliderBoardPiece.PieceType.V;
        }


        // Create and initialize a new board
        aiBoard = new SliderBoard(dimension, board);
    }

    /**
     *
     * @param move A Move object representing the previous move made by the
     * opponent, which may be null (indicating a pass). Also, before the first
     * move at the beginning of the game, move = null.
     * @throws InvalidMoveException
     */
    @Override
    public void update(Move move) throws InvalidMoveException {
        if(move == null){
            // TODO: Do Nothing or something?
            return;
        }

        aiBoard = SliderBoardMutator.mutateBoard(aiBoard, move);
    }

    /**
     *
     * @return
     */
    @Override
    public Move move() {

        // We can first call an opponent analyser to see if our current strategy
        // is the most optimal for them.
        currentStrategy = new MinimaxSearchStrategy(aiPlayerType);

        Move bestMove = currentStrategy.getBestMove(aiBoard);

        update(bestMove);

        return bestMove;
    }
}
