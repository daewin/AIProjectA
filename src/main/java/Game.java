import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by daewin on 25/03/2017.
 */
public class Game {

    public static void main(String args[]) {

        List<String> iterable = new ArrayList<>();

        Collections.addAll(iterable, args);

        // Based on the format specified in the specs, the first element is the boards' dimension.
        int dimension = Integer.parseInt(iterable.remove(0));

        // Set up the bare-bones board
        SliderBoard board = new SliderBoard(dimension);

        // Prepare the List to conform to the input format. This modifies the list in-place.
        prepareList(iterable, dimension);

        // Initialize the board
        initializeBoard(board, dimension, iterable);

        // Print board
        printBoard(board, dimension);

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
                Piece piece = new Piece(iterable.remove(0));

                board.boardMap.put(cell, piece);
                board.boardCells.add(cell);
                board.pieces.add(piece);
            }
        }

    }

    /**
     * Prepare the List to conform to the input format. This modifies the list in-place.
     * The idea here is to reverse the rows, so that (row, column) = (0, column) will now be
     * at the front of the list, instead of (dimension-1, column)
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

            lists.add(list.subList(fromIndex, toIndex));

            pointer = toIndex;
            count++;
        }

        // Reverse the list to achieve our goal
        Collections.reverse(lists);

        // We need a temporary list as subList only gives us a View of 'list'; i.e. any changes to the subList
        // also affects 'list'.
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
     * Print the board for visual aid
     * @param board     Board
     * @param dimension Dimension of the board
     */
    private static void printBoard(SliderBoard board, int dimension) {
        for (int row = dimension - 1; row >= 0; row--) {
            for (int column = 0; column < dimension; column++) {

                // Variables in Lambda expressions need to be final
                final int rowFinal = row;
                final int columnFinal = column;

                SliderBoardCell currentSBC = board.boardCells.stream()
                        .filter(c -> c.isPosition(rowFinal, columnFinal))
                        .collect(singletonCollector());

                if(board.boardMap.containsKey(currentSBC)){
                    System.out.print(board.boardMap.get(currentSBC) + " ");
                }

            }
            System.out.println();
        }


    }


    /**
     * Singleton Collector which is customized for single result filters
     * Obtained from: http://stackoverflow.com/questions/22694884/filter-java-stream-to-1-and-only-1-element
     * Written by: skiwi
     * @param <T>
     * @return Singleton element from list
     */
    public static <T> Collector<T, ?, T> singletonCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
