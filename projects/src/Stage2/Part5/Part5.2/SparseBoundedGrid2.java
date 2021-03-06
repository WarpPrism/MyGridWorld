import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * Created by zhoujihao on 15-8-20.
 */
// Use HashMap to implement the SparseBoundedGrid
/*
Consider using a HashMap or TreeMap to implement the SparseBoundedGrid.
How could you use the UnboundedGrid class to accomplish this task?
Which methods of UnboundedGrid could be used without change?
*/
public class SparseBoundedGrid2 extends AbstractGrid {
    private HashMap<Location, Object> occupantMap;
    private int rRows;
    private int cColumns;

    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        this.rRows = rows;
        this.cColumns = cols;
        this.occupantMap = new HashMap<>();
    }

    public int getNumRows() {
        return rRows;
    }

    public int getNumCols() {
        return cColumns;
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < this.getNumRows() && loc.getCol() >= 0 && loc.getCol() < this.getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<>();
        // get all the keys: Location.
        Set keyset = occupantMap.keySet();
        for (Object obj : keyset) {
            Location loc = (Location)obj;
            if (occupantMap.get(loc) != null) {
                theLocations.add(loc);
            }
        }
        return theLocations;
    }

    public Object get(Location loc) {
        if (!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid!");
        } else {
            if (!this.occupantMap.containsKey(loc)) {
                return null;
            } else {
                return this.occupantMap.get(loc);
            }
        }
    }

    public Object put(Location loc, Object obj) {
        if(!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        } else if(obj == null) {
            throw new NullPointerException("obj == null");
        } else {
            Object oldOccupant = this.get(loc);
            occupantMap.put(loc, obj);
            return oldOccupant;
        }
    }

    public Object remove(Location loc) {
        if(!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        } else {
            Object r = this.get(loc);
            occupantMap.remove(loc);
            return r;
        }
    }
}
