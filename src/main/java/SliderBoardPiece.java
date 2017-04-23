/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class SliderBoardPiece {

    public enum PieceType { H, V, B, BOUNDARY }

    public PieceType type;
    public SliderBoard.Position position;

    public SliderBoardPiece(String t, int i, int j){

        switch(t){
            case "H":
                type = PieceType.H;
                break;

            case "V":
                type = PieceType.V;
                break;

            case "B":
                type = PieceType.B;
                break;
        }

        this.position = new SliderBoard.Position(i, j);
    }

    public SliderBoardPiece(int i, int j){
        this.position = new SliderBoard.Position(i, j);
    }


    @Override
    public String toString() {
        return type.toString();
    }
}
