package aiproj.td;

import aiproj.td.AI.AIPlayer;
import aiproj.td.SliderBoard.SliderBoard;
import aiproj.td.SliderBoard.SliderBoardPiece;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   21/04/2017
 */
public class World {

    public static void main(String args[]){
        AIPlayer ai = new AIPlayer();

        ai.init(4, "H + + +\nH + B +\nH B + +\n+ V V V", 'H');


    }

    /**
     * Print the board for visual aid
     * @param board     Board
     */
    public static void printBoard(SliderBoard board) {

        if(board == null){
            System.out.println("Error: Empty board!");
        }

        int dimension = board.dimension;

        for (int j = dimension - 1; j >= 0; j--) {
            for (int i = 0; i < dimension; i++) {
                // Find for the piece at this location
                SliderBoardPiece piece = board.findPiece(i, j);

                if(piece != null){
                    System.out.print(piece + " ");
                } else {
                    System.out.print("+ ");
                }

            }
            System.out.println();
        }

    }
}
