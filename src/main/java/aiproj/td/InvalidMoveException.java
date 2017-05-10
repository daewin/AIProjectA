package aiproj.td;

import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * Created by daewin on 0023, April, 23.
 */
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(SliderBoardPiece s, String reason) {

        super
                ("\nError: Invalid move for piece " + s + " at position " +
                        "(" + s.position.i + ", " + s.position.j + "). " + reason);

    }

    public InvalidMoveException(){

        super("Error: Moving a piece that does not exist!");
    }
}
