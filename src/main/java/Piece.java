/**
 * Created by daewin on 25/03/2017.
 */
public class Piece {

    public String type;

    public Piece(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "" + type;
    }
}
