import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by daewin on 25/03/2017.
 */
public class SliderBoard {

    public int dimension;

    // The Board contains a list of all its cells
    public ArrayList<SliderBoardCell> boardCells;

    // It also contains all pieces in play during game-play
    public ArrayList<Piece> pieces;

    // TODO: Change to a normal map
    public HashMap<SliderBoardCell, Piece> boardMap;


    public SliderBoard(int dimension) {
        this.dimension = dimension;

        boardCells = new ArrayList<>();
        pieces = new ArrayList<>();
        boardMap = new HashMap<>(dimension*dimension);
    }




}
