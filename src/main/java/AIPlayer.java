import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

/**
 * Created by daewin on 0021, April 21.
 */
public class AIPlayer implements SliderPlayer, PieceMovement {

    // Instance variables for our agent
    private SliderBoard aiBoard;
    private char aiPlayerType;

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
        aiPlayerType = player;

        // Create and initialize a new board
        aiBoard = new SliderBoard(dimension, board);

        World.printBoard(aiBoard, dimension);

        System.out.println("Number of pieces: " + aiBoard.hashPieces.size());

        // Lets move a piece
        Move m = new Move(3, 0, Move.Direction.UP);

        update(m);

        System.out.println();
        System.out.println("New Move: " + m);
        System.out.println("New Board:");
        World.printBoard(aiBoard, dimension);

        System.out.println("Number of pieces: " + aiBoard.hashPieces.size());
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
            // Do Nothing or something?
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
        return null;
    }
}
