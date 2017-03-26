/**
 * Created by daewin on 25/03/2017.
 */
public class SliderBoardCell {

    public RowColumn position;

    public SliderBoardCell(int row, int column){
        position = new RowColumn(row, column);
    }

    public boolean isPosition(int row, int column){
        if(row == position.row && column == position.column){
            return true;
        }
        return false;
    }


    public class RowColumn {
        private final int row;
        private final int column;

        private RowColumn(int row, int column){
            this.row = row;
            this.column = column;
        }
    }


}
