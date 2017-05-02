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
            (1) The cell is "empty"
            (2) We are not moving past any boundaries that we should not cross
        Checking if we have successfully crossed the boundary should be handled later, along with the points awarded etc.
    */


    /**
     * Check if we can move our piece up
     * @param board
     * @param piece
     * @return if we can move up
     */
    static boolean canMoveUp(SliderBoard board, SliderBoardPiece piece) throws InvalidMoveException {

        if(isInvalidPiece(piece)){
            throw new InvalidMoveException(piece, "Trying to move a piece that is not H or V!");
        }

        // First we get the piece above our current piece
        SliderBoardPiece pieceAbove = getPieceAbove(board, piece);

        if(pieceAbove != null && pieceAbove.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            // The boundary has been reached
            if(piece.type.equals(SliderBoardPiece.PieceType.H)){
                // Since 'H' cannot move past the top boundary, we return false
                return false;

            } else {
                // Since 'V' can move past the top boundary, we return true
                return true;
            }
        }

        // Now we check if the piece above is considered blocked. If it is, we return false
        if(isBlocked(pieceAbove)){
            return false;
        }

        // It's safe to move up for both cases
        return true;
    }

    /**
     * Check if we can move our piece down
     * @param board
     * @param piece
     * @return if we can move down
     */
    static boolean canMoveDown(SliderBoard board, SliderBoardPiece piece) throws InvalidMoveException {

        if(isInvalidPiece(piece)){
            throw new InvalidMoveException(piece, "Trying to move a piece that is not H or V!");
        }

        // First we get the piece below
        SliderBoardPiece pieceBelow = getPieceBelow(board, piece);

        if(pieceBelow != null && pieceBelow.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            // The boundary has been reached
            if(piece.type.equals(SliderBoardPiece.PieceType.H)){
                // Since 'H' cannot move past the bottom boundary, we return false
                return false;
            } else {
                // Since 'V' cannot move past the bottom boundary, we return true
                return false;
            }
        }

        if(piece.type.equals(SliderBoardPiece.PieceType.H)){
            // Now we check if the piece below is considered blocked. If it is, we return false
            if(isBlocked(pieceBelow)){
                return false;
            }

            // It's safe to move down
            return true;

        } else if (piece.type.equals(SliderBoardPiece.PieceType.V)){
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
     * @return if we can move to the left
     */
    static boolean canMoveLeft(SliderBoard board, SliderBoardPiece piece) throws InvalidMoveException {

        if(isInvalidPiece(piece)){
            throw new InvalidMoveException(piece, "Trying to move a piece that is not H or V!");
        }

        // First we get the piece to the left
        SliderBoardPiece pieceLeft = getPieceLeft(board, piece);

        if(pieceLeft != null && pieceLeft.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            if(piece.type.equals(SliderBoardPiece.PieceType.H)){
                // Since 'H' cannot move past the left boundary, we return false
                return false;
            } else {
                // Since 'V' cannot move past the left boundary, we return false
                return false;
            }
        }

        if(piece.type.equals(SliderBoardPiece.PieceType.H)){
            // 'H' is not allowed to move to the left, return false
            return false;

        } else if (piece.type.equals(SliderBoardPiece.PieceType.V)){
            // We check if the piece to the left is considered blocked. If it is, we return false
            if(isBlocked(pieceLeft)){
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
    static boolean canMoveRight(SliderBoard board, SliderBoardPiece piece) throws InvalidMoveException {

        if(isInvalidPiece(piece)){
            throw new InvalidMoveException(piece, "Trying to move a piece that is not H or V!");
        }

        // First we get the piece to the right
        SliderBoardPiece pieceRight = getPieceRight(board, piece);

        if(pieceRight != null && pieceRight.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            // The boundary has been reached;
            if(piece.type.equals(SliderBoardPiece.PieceType.H)){
                // Since 'H' can move past the right boundary, we return true
                return true;
            } else {
                // Since 'V' cannot move past the right boundary, we return false
                return false;
            }
        }

        // Now we check if the piece to the right is considered blocked. If it is, we return false
        if(isBlocked(pieceRight)){
            return false;
        }

        // It's safe to move to the right for both cases
        return true;
    }

    /**
     * Check if the particular position is blocked, i.e. anything other than '+'
     * @param piece
     * @return
     */
    static boolean isBlocked(SliderBoardPiece piece) {

        // If there is no piece found, then this cell is empty (i.e. not blocked)
        if(piece == null){
            return false;
        }

        return true;
    }

    /**
     * Checks if the piece is valid for a move (i.e. H or V)
     * @param piece
     * @return
     */
    static boolean isInvalidPiece(SliderBoardPiece piece){
        return !(piece.type.equals(SliderBoardPiece.PieceType.V) ||
                piece.type.equals(SliderBoardPiece.PieceType.H));
    }


    /**
     * Return the "piece" above our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceAbove(SliderBoard board, SliderBoardPiece piece) {

        // Get the position above
        int i = piece.position.i;
        int j = piece.position.j + 1;

        if(j >= board.dimension){
            // Check that we have not exceeded the boards' boundaries
            return new BoundarySliderBoardPiece(i, j);
        }

        // Find and return the piece at that position. If there is no such piece, a null is returned
        return board.findPiece(i, j);
    }

    /**
     * Return the "piece" below our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceBelow(SliderBoard board, SliderBoardPiece piece) {

        // Get the position below
        int i = piece.position.i;
        int j = piece.position.j - 1;

        if(j < 0){
            return new BoundarySliderBoardPiece(i, j);
        }

        return board.findPiece(i, j);
    }


    /**
     * Return the "piece" to the right of our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceRight(SliderBoard board, SliderBoardPiece piece) {

        // Get the position to the right
        int i = piece.position.i + 1;
        int j = piece.position.j;

        if(i >= board.dimension){
            return new BoundarySliderBoardPiece(i, j);
        }

        return board.findPiece(i, j);
    }


    /**
     * Return the "piece" to the left of our parameter piece
     * @param piece
     */
    static SliderBoardPiece getPieceLeft(SliderBoard board, SliderBoardPiece piece) {

        // Get the position to the left
        int i = piece.position.i - 1;
        int j = piece.position.j;

        if(i < 0){
            return new BoundarySliderBoardPiece(i, j);
        }

        return board.findPiece(i, j);
    }

}
