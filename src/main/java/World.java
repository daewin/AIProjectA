import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   21/04/2017
 */
public class World {

    public static void main(String args[]){
        AIPlayer ai = new AIPlayer();

        ai.init(6, "H + + + + + + H B + + + H + + + + + H + V B V + + + + + H + + V + + V H ", 'H');
    }

    /**
     * Prepare the List to conform to the input format. This modifies the list in-place.
     * The idea here is to reverse the rows, so that (i, 0) will now be at the front of the list,
     * instead of (i, dimension-1).
     * @param list      Initial List read in
     * @param dimension Dimension of the board
     */
    public static void prepareList(List<String> list, int dimension) {
        int pointer = 0, count = 0;
        List<List<String>> lists = new ArrayList<>();

        while (count < dimension) {
            // We iterate over the rows, adding each row to lists
            int fromIndex = pointer;
            int toIndex = pointer + dimension;

            // Second argument of subList is non-inclusive
            lists.add(list.subList(fromIndex, toIndex));

            pointer = toIndex;
            count++;
        }

        // Reverse the list to achieve our goal
        Collections.reverse(lists);

        // We need a temporary list as subList only gives us a View of 'list', i.e. any changes
        // to the subList, such as flattening, also affects 'list'.
        List<String> tempList = new ArrayList<>();

        // Obtained this code online for the proper use of lambdas (multiple sources)
        // We use flatMap to give us a continuous one-level structure for our list
        tempList.addAll(lists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        // For convenience sake, and since we don't need our initial list any longer, we apply our changes in-place.
        list.clear();
        list.addAll(tempList);

    }

    /**
     * Initialize the board with the values from standard input
     *
     * @param board     Board
     * @param dimension Dimension of the board
     */
    public static void initializeBoard(List<String> iterable, int dimension, SliderBoard board) {
        for (int j = 0; j < dimension; j++) {
            for (int i = 0; i < dimension; i++) {

                String potentialPiece = iterable.remove(0);

                if(potentialPiece.equals("+")){
                    // For better search performance, we refrain from adding empty positions as pieces
                    continue;
                }
                SliderBoardPiece piece = new SliderBoardPiece(potentialPiece, i, j);

                board.hashPieces.put(piece.position, piece);
            }
        }
    }





    /**
     * Print the board for visual aid
     * @param board     Board
     * @param dimension Dimension of the board
     */
    public static void printBoard(SliderBoard board, int dimension) {
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
