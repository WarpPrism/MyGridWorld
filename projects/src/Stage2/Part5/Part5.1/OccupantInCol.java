/**
 * Created by zhoujihao on 15-8-20.
 */
public class OccupantInCol {
    private Object occupant;
    private int col;

    public OccupantInCol(Object occupant, int col) {
        this.occupant = occupant;
        this.col = col;
    }

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;

    }
}
