import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * Created by zhoujihao on 15-8-20.
 */
// Use TreeMap to implement the SparseBoundedGrid

public class SparseBoundedGrid3 extends AbstractGrid {
    private TreeMap<Location, Object> occupantMap;
    private int Rows;
    private int Columns;

    public SparseBoundedGrid3(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        this.Rows = rows;
        this.Columns = cols;
        this.occupantMap = new TreeMap<>();
    }

    public int getNumRows() {
        return Rows;
    }

    public int getNumCols() {
        return Columns;
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < this.getNumRows() && loc.getCol() >= 0 && loc.getCol() < this.getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<>();

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
            if (loc == null) {
                throw new NullPointerException("Location is null");
            }
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
