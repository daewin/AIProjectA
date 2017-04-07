/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class EmptyCellException extends RuntimeException {

    public EmptyCellException(int row, int column) {
        super("Cell being accessed at \"(row, column): (" + row + ", " + column + ")\" is empty!");
    }
}
