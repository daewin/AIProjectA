import aiproj.slider.Move;
import aiproj.slider.SliderPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by daewin on 0021, April 21.
 */
public class AIPlayer implements SliderPlayer, PieceMovement {

    private int aiDimension;
    private SliderBoard aiBoard;
    private char aiPlayerType;


    @Override
    public void init(int dimension, String board, char player) {

        // An "iterable" list to work on
        List<String> iterable = new ArrayList<>();

        // Read from String, and also use a space-delimiter (with 0 or more spaces handled)
        Scanner inputScanner = new Scanner(board).useDelimiter("\\s*");

        while(inputScanner.hasNext()){
            iterable.add(inputScanner.next());
        }

        // Set the boards dimensions
        aiDimension = dimension;

        // Set our players type
        aiPlayerType = player;

        // Set up the bare-bones board
        aiBoard = new SliderBoard(dimension);

        // Prepare the List to conform to the input format. This modifies the list in-place.
        World.prepareList(iterable, aiDimension);

        World.initializeBoard(iterable, aiDimension, aiBoard);

        World.printBoard(aiBoard, dimension);

        // Lets move a piece
        Move m = new Move(5, 0, Move.Direction.RIGHT);

        update(m);

        System.out.println("New Board:");
        World.printBoard(aiBoard, dimension);
    }

    @Override
    public void update(Move move) {
        if(move == null){
            // Do Nothing?
            return;
        }

        SliderBoardPiece currentPiece = aiBoard.findPiece(move.i, move.j);
        SliderBoardPiece futurePiece;
        SliderBoard.Position futurePosition;

        if(currentPiece == null){
            // Moving a piece that doesn't exist
            return;
        }

        // Regardless if the future position is empty, we'll have a position to work with
        switch(move.d){
            case UP:
                if(PieceMovement.canMoveUp(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceAbove(aiBoard, currentPiece);

                    if(futurePiece == null){
                        futurePosition = SliderBoard.Position.getPositionAbove(move.i, move.j);
                    } else {
                        futurePosition = futurePiece.position;
                    }

                } else {
                    // Illegal move
                    return;
                }
                break;

            case DOWN:
                if(PieceMovement.canMoveDown(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceBelow(aiBoard, currentPiece);

                    if(futurePiece == null){
                        futurePosition = SliderBoard.Position.getPositionBelow(move.i, move.j);
                    } else {
                        futurePosition = futurePiece.position;
                    }
                } else {
                    // Illegal move
                    return;
                }
                break;

            case LEFT:
                if(PieceMovement.canMoveLeft(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceLeft(aiBoard, currentPiece);

                    if(futurePiece == null){
                        futurePosition = SliderBoard.Position.getPositionLeft(move.i, move.j);
                    } else {
                        futurePosition = futurePiece.position;
                    }
                } else {
                    // Illegal move
                    return;
                }
                break;

            case RIGHT:
                if(PieceMovement.canMoveRight(aiBoard, currentPiece)){

                    futurePiece = PieceMovement.getPieceRight(aiBoard, currentPiece);

                    if(futurePiece == null){
                        futurePosition = SliderBoard.Position.getPositionRight(move.i, move.j);
                    } else {
                        futurePosition = futurePiece.position;
                    }
                } else {
                    // Illegal move
                    return;
                }
                break;

            default:
                return;
        }

        // First we check if a key at our desired future position exists.
        if(aiBoard.hashPieces.containsKey(futurePosition)){

            // Replace the appropriate future piece with the current piece. The Replace method in the
            // HashMap replaces the value for the given key and oldValue
            if(!aiBoard.hashPieces.
                    replace(futurePiece.position, futurePiece, currentPiece)){

                System.out.println("Error: The pieces don't match up!");
                return;
            }
        } else {

            // We check if the futurePiece is past the boundary
            if(futurePiece.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
                // TODO: Do our necessary score keeping here

            } else {
                //If it does not, the cell is empty, so we create a new key-value pair.
                aiBoard.hashPieces.put(futurePosition, currentPiece);
            }
        }

        // Now we remove the old "current" piece from the HashMap
        if(!aiBoard.hashPieces.
                remove(currentPiece.position, currentPiece)){
            System.out.println("Error: The pieces don't match up!");
            return;
        }

    }

    @Override
    public Move move() {
        return null;
    }
}
