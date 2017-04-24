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

        System.out.println("Number of pieces: " + aiBoard.hashPieces.size());

        // Lets move a piece
        Move m = new Move(5, 0, Move.Direction.RIGHT);

        update(m);

        System.out.println();
        System.out.println("New Move: " + m);
        System.out.println("New Board:");
        World.printBoard(aiBoard, dimension);

        System.out.println("Number of pieces: " + aiBoard.hashPieces.size());
    }

    @Override
    public void update(Move move) throws InvalidMoveException {
        if(move == null){
            // Do Nothing?
            return;
        }

        SliderBoardPiece currentPiece = aiBoard.findPiece(move.i, move.j);
        SliderBoardPiece futurePiece;

        if(currentPiece == null){
            // Moving a piece that doesn't exist
            return;
        }

        // We check if we can move in the intended direction. There are only two cases possible:
        // Either the future position is empty, or it is past the boundary where allowed as stated
        // in the rules of the game for that particular piece type.
        switch(move.d){
            case UP:
                if(!PieceMovement.canMoveUp(aiBoard, currentPiece)){

                    throw new InvalidMoveException(currentPiece);

                } else {
                    futurePiece = PieceMovement.getPieceAbove(aiBoard, currentPiece);
                }
                break;

            case DOWN:
                if(!PieceMovement.canMoveDown(aiBoard, currentPiece)){

                    throw new InvalidMoveException(currentPiece);
                } else {
                    futurePiece = PieceMovement.getPieceBelow(aiBoard, currentPiece);
                }
                break;

            case LEFT:
                if(!PieceMovement.canMoveLeft(aiBoard, currentPiece)){

                    throw new InvalidMoveException(currentPiece);
                } else {
                    futurePiece = PieceMovement.getPieceLeft(aiBoard, currentPiece);
                }
                break;

            case RIGHT:
                if(!PieceMovement.canMoveRight(aiBoard, currentPiece)){

                    throw new InvalidMoveException(currentPiece);
                } else {
                    futurePiece = PieceMovement.getPieceRight(aiBoard, currentPiece);
                }
                break;

            default:
                return;
        }

        // We check if the futurePiece is past the boundary
        if(futurePiece.type.equals(SliderBoardPiece.PieceType.BOUNDARY)){
            // TODO: Do our necessary score keeping here

        } else {
            //If it does not, the cell is empty, so we create a new key-value pair.
            aiBoard.hashPieces.put(futurePiece.position, currentPiece);
        }

        // Now we remove the old "current" piece from the HashMap
        aiBoard.hashPieces.remove(currentPiece.position, currentPiece);

    }

    @Override
    public Move move() {
        return null;
    }
}
