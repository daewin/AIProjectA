/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class Game implements PieceMovement {

    SliderBoard board;

    public Game(SliderBoard board){

        this.board = board;
    }

    public int countLegalMoves(String type) throws EmptyCellException {

        int count = 0;

        for (SliderBoardPiece piece : board.pieces) {
            if (type.equals("H") && piece.type.equals("H")) {
                if (PieceMovement.canMoveUp(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveDown(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveLeft(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveRight(board, piece)) {
                    count++;
                }
            } else if (type.equals("V") && piece.type.equals("V")) {
                if (PieceMovement.canMoveUp(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveDown(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveLeft(board, piece)) {
                    count++;
                }

                if (PieceMovement.canMoveRight(board, piece)) {
                    count++;
                }
            }
        }
        return count;
    }
}
