package aiproj.td.SliderBoard;

/**
 * COMP30024 Artificial Intelligence - Project A
 * By:              Daewin SV Lingam (dsv)
 * Last modified:   07/04/2017
 */
public class SliderBoardPiece {

    public enum PieceType { H, V, B, BOUNDARY }

    public PieceType type;
    public SliderBoard.Position position;

    public SliderBoardPiece(char t, int i, int j){

        switch(t){
            case 'H':
                type = PieceType.H;
                break;

            case 'V':
                type = PieceType.V;
                break;

            case 'B':
                type = PieceType.B;
                break;
        }

        this.position = new SliderBoard.Position(i, j);
    }

    public SliderBoardPiece(SliderBoardPiece oldPiece){
        this.type = oldPiece.type;
        this.position = oldPiece.position;
    }

    /**
     * For use by the Boundary piece. Its type will be set there
     * @param position
     */
    public SliderBoardPiece(SliderBoard.Position position){ this.position = position; }

    /**
     * Updates the position when moving
     * @param position
     */
    public void setPosition(SliderBoard.Position position) {
        this.position = position;
    }


    /**
     * Gives us the opponents piece type, in most use-cases
     * @param type
     * @return
     */
    public static PieceType oppositePieceType(PieceType type){
        if(type.equals(PieceType.H)){
            return PieceType.V;
        } else {
            return PieceType.H;
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
