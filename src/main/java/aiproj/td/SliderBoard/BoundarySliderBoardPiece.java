package aiproj.td.SliderBoard;

/**
 * Created by daewin on 0023, April, 23.
 */
public class BoundarySliderBoardPiece extends SliderBoardPiece {

    public BoundarySliderBoardPiece(int i, int j) {
        super(new SliderBoard.Position(i, j));

        type = PieceType.BOUNDARY;
    }
}
