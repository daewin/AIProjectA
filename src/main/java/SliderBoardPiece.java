/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class SliderBoardPiece {

    public String type;
    public SliderBoard.Position position;

    public SliderBoardPiece(String type, int row, int column){
        this.type = type;
        this.position = new SliderBoard.Position(row, column);
    }

    /**
     * Check if the position parameters are the same as our position
     * @param row
     * @param column
     * @return
     */
    public boolean isPosition(int row, int column) {
        if (row == position.row && column == position.column) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + type;
    }
}
