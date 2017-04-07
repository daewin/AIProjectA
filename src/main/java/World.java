import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class World {

    public static void main(String args[]) {

        // An "iterable" list to work on
        List<String> iterable = new ArrayList<>();

        // Read from redirection, and also use a space-delimiter (with 0 or more spaces handled)
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\s*");

        while(inputScanner.hasNext()){
            iterable.add(inputScanner.next());
        }

        // Based on the format specified in the specs, the first element is the boards' dimension.
        int dimension = Integer.parseInt(iterable.remove(0));

        // Set up the bare-bones board
        SliderBoard board = new SliderBoard(dimension);

        // Prepare the List to conform to the input format. This modifies the list in-place.
        prepareList(iterable, dimension);

        initializeBoard(board, dimension, iterable);

        // Prepare game
        Game game = new Game(board);

        // Let's see our results
        System.out.println("numLegalHMoves: " + game.countLegalMoves("H"));
        System.out.println("numLegalVMoves: " + game.countLegalMoves("V"));
    }


    /**
     * Prepare the List to conform to the input format. This modifies the list in-place.
     * The idea here is to reverse the rows, so that (0, column) will now be at the front of the list,
     * instead of (dimension-1, column).
     * @param list      Initial List read in
     * @param dimension Dimension of the board
     */
    private static void prepareList(List<String> list, int dimension) {
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
    private static void initializeBoard(SliderBoard board, int dimension, List<String> iterable) {
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {

                SliderBoardCell cell = new SliderBoardCell(row, column);
                SliderBoardPiece piece = new SliderBoardPiece(iterable.remove(0), row, column);

                board.boardCells.add(cell);
                board.pieces.add(piece);
            }
        }
    }


    /**
     * Print the board for visual aid
     * @param board     Board
     * @param dimension Dimension of the board
     */
    private static void printBoard(SliderBoard board, int dimension) throws EmptyCellException {
        for (int row = dimension - 1; row >= 0; row--) {
            for (int column = 0; column < dimension; column++) {
                // Find for the piece at this location
                SliderBoardPiece piece = board.findPiece(row, column);

                if(piece != null){
                    System.out.print(piece + " ");
                }

            }
            System.out.println();
        }

    }
}
