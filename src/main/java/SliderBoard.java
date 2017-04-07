import java.util.ArrayList;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class SliderBoard {

    public int dimension;

    // The Board contains a list of all its cells
    public ArrayList<SliderBoardCell> boardCells;

    // It also contains all pieces in play during game-play
    public ArrayList<SliderBoardPiece> pieces;


    public SliderBoard(int dimension) {
        this.dimension = dimension;

        boardCells = new ArrayList<>();
        pieces = new ArrayList<>();
    }


    /**
     * Find the particular piece based on its location on the board
     * @param row
     * @param column
     * @return
     */
    public SliderBoardPiece findPiece(int row, int column) throws EmptyCellException {

        for(SliderBoardPiece piece : pieces){
            if(piece.isPosition(row, column)){
                return piece;
            }
        }

        // There should never be an empty cell.
        throw new EmptyCellException(row, column);
    }


    /**
     * Generic position class for use on our Board
     */
    public static class Position {
        public final int row;
        public final int column;

        public Position(int row, int column){
            this.row = row;
            this.column = column;
        }
    }



}
