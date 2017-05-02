import java.util.HashMap;
import java.util.Objects;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class SliderBoard {

    public int dimension;

    // It also contains all pieces in play during game-play
    public HashMap<Position, SliderBoardPiece> hashPieces;


    public SliderBoard(int dimension, String board) {
        this.dimension = dimension;
        this.hashPieces = new HashMap<>();
        initialiseBoard(board);
    }

    public SliderBoard(SliderBoard oldBoard){
        this.dimension = oldBoard.dimension;
        this.hashPieces = new HashMap<>();
        this.hashPieces.putAll(oldBoard.hashPieces);
    }

    private void initialiseBoard(String board)
    {
    	char c;
    	int row=0, col;
    	SliderBoardPiece piece;
    	
    	for (int i=0; i<board.length(); i+=2)
    	{
    		col = (i/2) % dimension;
    		c = board.charAt(i);
    		
    		// Only need to add non-empty pieces to the board.
    		if (c != '+')
    		{
    			piece = new SliderBoardPiece(c, col, dimension-row-1);
    			hashPieces.put(piece.position, piece);
    		}
    		
    		// This was the last column in the row.
    		if (col == dimension - 1)
    			row++;
    	}
    	
    }

    /**
     * Find the particular piece based on its location on the board
     * @param i
     * @param j
     * @return
     */
    public SliderBoardPiece findPiece(int i, int j) {

        SliderBoardPiece sbp = hashPieces.get(new Position(i, j));

        // We get our piece in O(1) time complexity.
        if(sbp == null){
            // If there is no piece found, then this cell is empty
            return null;
        }

        return sbp;
    }

    /**
     * Generic position class for use on our Board
     */
    public static class Position {
        public final int i;
        public final int j;


        public Position(int i, int j){
            this.i = i;
            this.j = j;
        }

        // Helper Functions
        public static Position getPositionAbove(int i, int j){ return new Position(i, j+1); }
        public static Position getPositionBelow(int i, int j){ return new Position(i, j-1); }
        public static Position getPositionRight(int i, int j){ return new Position(i+1, j); }
        public static Position getPositionLeft(int i, int j){ return new Position(i-1, j); }


        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;

            if(!(obj instanceof Position)){
                return false;
            }

            Position pos = (Position) obj;

            return i == pos.i && j == pos.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        @Override
        public String toString() {
            return "(i, j) : (" + i + ", " + j + ")";
        }
    }



}
