/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public interface PieceMovement {
    /*
        We split up the 'H' and 'V' play rules.
        A 'H' piece (1) can only move up, down and to the right, and
                    (2) can only cross the right-side boundary

        A 'V' piece (1) can only move up, left and to the right, and
                    (2) can only cross the top boundary


        By first checking if we are allowed to move our piece in a certain direction, we can be assured when
        we actually move the piece that:
            (1) The cell is "empty" (using the '+' notation)
            (2) We are not moving past any boundaries that we should not cross
        Checking if we have successfully crossed the boundary should be handled later, along with the points awarded etc.
    */


    /**
     * Check if we can move our piece up
     * @param board
     * @param piece
     * @return
     * @throws Exception
     */
    static boolean canMoveUp(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        String upPieceType = piece.type;

        // First we get the piece above. Note: even if the return value is a null, we will allow the respective
        // "H" or "V" logic control statements to decide how to proceed as null is allowed in certain cases.
        SliderBoardPiece pieceAbove = getPieceAbove(board, piece);

        if(upPieceType.equals("H")){

            // If it's null, then the boundary has been reached;
            if(pieceAbove == null){
                // Since 'H' cannot move past the top boundary, we return false
                return false;
            }

            // Now we check if the piece above is considered blocked. If it is, we return false
            if(isBlocked(board, pieceAbove.position)){
                return false;
            }

            // It's safe to move up
            return true;

        } else if (upPieceType.equals("V")){

            // If it's null, then the boundary has been reached;
            if(pieceAbove == null){
                // Since 'V' can move past the top boundary, we return true
                return true;
            }

            // Now we check if the piece above is considered blocked. If it is, we return false
            if(isBlocked(board, pieceAbove.position)){
                return false;
            }

            // It's safe to move up
            return true;



        } else {
            // We should not be moving other "pieces"
            return false;
        }
    }

    /**
     * Check if we can move our piece down
     * @param board
     * @param piece
     * @return
     * @throws Exception
     */
    static boolean canMoveDown(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        String downPieceType = piece.type;

        // First we get the piece below
        SliderBoardPiece pieceBelow = getPieceBelow(board, piece);

        if(downPieceType.equals("H")){

            // If it's null, then the boundary has been reached;
            if(pieceBelow == null){
                // Since 'H' cannot move past the bottom boundary, we return false
                return false;
            }

            // Now we check if the piece below is considered blocked. If it is, we return false
            if(isBlocked(board, pieceBelow.position)){
                return false;
            }

            // It's safe to move down
            return true;

        } else if (downPieceType.equals("V")){
            // 'V' is not allowed to move down, return false
            return false;

        } else {
            // We should not be moving other "pieces"
            return false;
        }
    }


    /**
     * Check if we can move our piece to the left
     * @param board
     * @param piece
     * @return
     * @throws Exception
     */
    static boolean canMoveLeft(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        String leftPieceType = piece.type;

        // First we get the piece to the left
        SliderBoardPiece pieceLeft = getPieceLeft(board, piece);

        if(leftPieceType.equals("H")){
            // 'H' is not allowed to move to the left, return false
            return false;

        } else if (leftPieceType.equals("V")){

            // If it's null, then the boundary has been reached;
            if(pieceLeft == null){
                // Since 'V' cannot move past the left boundary, we return false
                return false;
            }

            // Now we check if the piece to the left is considered blocked. If it is, we return false
            if(isBlocked(board, pieceLeft.position)){
                return false;
            }

            // It's safe to move to the left
            return true;

        } else {
            // We should not be moving other "pieces"
            return false;
        }
    }


    /**
     * Check if we can move our piece to the right
     * @param board
     * @param piece
     * @return
     * @throws Exception
     */
    static boolean canMoveRight(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        String rightPieceType = piece.type;

        // First we get the piece to the right
        SliderBoardPiece pieceRight = getPieceRight(board, piece);

        if(rightPieceType.equals("H")){
            // If it's null, then the boundary has been reached;
            if(pieceRight == null){
                // Since 'H' can move past the right boundary, we return true
                return true;
            }

            // Now we check if the piece to the right is considered blocked. If it is, we return false
            if(isBlocked(board, pieceRight.position)){
                return false;
            }

            // It's safe to move to the right
            return true;

        } else if (rightPieceType.equals("V")){

            // If it's null, then the boundary has been reached;
            if(pieceRight == null){
                // Since 'V' cannot move past the right boundary, we return false
                return false;
            }

            // Now we check if the piece to the right is considered blocked. If it is, we return false
            if(isBlocked(board, pieceRight.position)){
                return false;
            }

            // It's safe to move to the right
            return true;

        } else {
            // We should not be moving other "pieces"
            return false;
        }
    }

    /**
     * Check if the particular position is blocked, i.e. anything other than '+'
     * @param board
     * @param position
     * @return
     * @throws Exception
     */
    static boolean isBlocked(SliderBoard board, SliderBoard.Position position) throws EmptyCellException {

        String pieceType = board.findPiece(position.row, position.column).type;

        if(!pieceType.equals("+")){
            // If it's any case other than an empty cell
            return true;
        }

        return false;
    }


    /**
     * Return the "piece" above our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceAbove(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        // Get the position above
        int row = piece.position.row + 1;
        int column = piece.position.column;

        if(row >= board.dimension){
            // Check that we have not exceeded the boards' boundaries
            return null;
        }

        // Find and return the piece at that position. If there is no such piece, an EmptyCellException is thrown,
        // and propagated up the stack till main, where it exits.
        return board.findPiece(row, column);
    }

    /**
     * Return the "piece" below our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceBelow(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        // Get the position below
        int row = piece.position.row - 1;
        int column = piece.position.column;

        if(row < 0){
            return null;
        }

        return board.findPiece(row, column);
    }


    /**
     * Return the "piece" to the right of our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceRight(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        // Get the position to the right
        int row = piece.position.row;
        int column = piece.position.column + 1;

        if(column >= board.dimension){
            return null;
        }

        return board.findPiece(row, column);
    }


    /**
     * Return the "piece" to the left of our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceLeft(SliderBoard board, SliderBoardPiece piece) throws EmptyCellException {

        // Get the position to the left
        int row = piece.position.row;
        int column = piece.position.column - 1;

        if(column < 0){
            return null;
        }

        return board.findPiece(row, column);
    }

}
