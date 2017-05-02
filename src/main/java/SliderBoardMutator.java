import aiproj.slider.Move;

/**
 * Created by daewi on 0001, May, 1.
 */
public class SliderBoardMutator {

    public static SliderBoard mutateBoard(SliderBoard board, Move move){

        if(move == null){
            // TODO: Do Nothing or something?
            return null;
        }

        SliderBoard aiBoard = new SliderBoard(board);

        SliderBoardPiece currentPiece = aiBoard.findPiece(move.i, move.j);
        SliderBoardPiece futurePiece;
        SliderBoard.Position futurePosition;

        if(currentPiece == null){
            // Moving a piece that doesn't exist
            throw new InvalidMoveException();
        }

        // We check if we can move in the intended direction. There are only two cases possible:
        // Either the future position is empty, or it is past the boundary where allowed as stated
        // in the rules of the game for that particular piece type.
        switch(move.d){
            case UP:
                if(PieceMovement.canMoveUp(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceAbove(aiBoard, currentPiece);
                    futurePosition = SliderBoard.Position.getPositionAbove(move.i, move.j);

                } else {
                    throw new InvalidMoveException(currentPiece, "It can't move up!");
                }
                break;

            case DOWN:
                if(PieceMovement.canMoveDown(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceBelow(aiBoard, currentPiece);
                    futurePosition = SliderBoard.Position.getPositionBelow(move.i, move.j);

                } else {
                    throw new InvalidMoveException(currentPiece, "It can't move down!");
                }
                break;

            case LEFT:
                if(PieceMovement.canMoveLeft(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceLeft(aiBoard, currentPiece);
                    futurePosition = SliderBoard.Position.getPositionLeft(move.i, move.j);

                } else {
                    throw new InvalidMoveException(currentPiece, "It can't move left!");
                }
                break;

            case RIGHT:
                if(PieceMovement.canMoveRight(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceRight(aiBoard, currentPiece);
                    futurePosition = SliderBoard.Position.getPositionRight(move.i, move.j);

                } else {
                    throw new InvalidMoveException(currentPiece, "It can't move right!");
                }
                break;

            default:
                return null;
        }

        // There are two things that can happen here. Either:
        // a) futurePiece is past the boundary and is allowed to be so. And as such, the respective
        //     getPiece function will return a fabricated piece with type 'BOUNDARY'.
        // b) futurePiece is null, as its position on the board is not occupied by a piece, thus we
        //     can add our currentPiece to that position accordingly.

        // We check if the futurePiece is past the boundary
        if(futurePiece != null && futurePiece.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            // TODO: Do our necessary score keeping here

        } else {
            //If it does not, the cell is empty, so we create a new key-value pair.
            aiBoard.hashPieces.put(futurePosition, currentPiece);
        }

        // Now we remove the old "current" piece from the HashMap regardless of the previous cases
        aiBoard.hashPieces.remove(currentPiece.position, currentPiece);

        // We also update the new Position variables in the piece
        currentPiece.setPosition(futurePosition);

        return aiBoard;
    }
}
