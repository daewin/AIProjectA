/**
 * Created by daewin on 0023, April, 23.
 */
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(SliderBoardPiece s) {
        super("Error! Invalid move for piece " + s + " at position (" + s.position.i + ", " + s.position.j + ")");
    }
}
