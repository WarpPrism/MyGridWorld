import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zhoujihao on 15-8-20.
 */
/*
Suppose that a program requires a very large bounded grid that contains very
few objects and that the program frequently calls the getOccupiedLocations method
 (as, for example, ActorWorld). Create a class SparseBoundedGrid that uses a
 sparse array implementation. Your solution need not be a generic class.
 you may simply store occupants of type Object.
*/

/*
The "sparse array" is an array list of linked lists.
Each linked list entry holds both a grid occupant and a column index.
 Each entry in the array list is a linked list or is null if that row is empty.
*/
/*For a grid with r rows and c columns, the sparse array has length r. Each of the linked lists has maximum length c.
Implement the methods specified by the Grid interface using this data structure.
Why is this a more time-efficient implementation than BoundedGrid?
The World has a public addGridClass method. Since the ActorWorld is a World,
you can call this method in a runner. Here is the code to add a new grid to the GUI.*/
public class SparseBoundedGrid extends AbstractGrid {
    private LinkedList<OccupantInCol>[] listArray;
    private int rRows;
    private int cColumns;

    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        this.rRows = rows;
        this.cColumns = cols;
        this.listArray = new LinkedList[rows];
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
        ArrayList<Location> locs = new ArrayList<>();
        for (int i = 0; i < this.getNumRows(); i++) {
            if (listArray[i] == null) {
                continue;
            }
            Iterator iter = listArray[i].iterator();
            while (iter.hasNext()) {
                OccupantInCol a = (OccupantInCol)iter.next();
                if (a.getOccupant() != null) {
                    locs.add(new Location(i, a.getCol()));
                }
            }
        }
        return locs;
    }

    public Object get(Location loc) {
        if (!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid!");
        } else {
            int row = loc.getRow();
            if (listArray[row] == null) {
                return null;
            }
            Iterator iter = listArray[row].iterator();
            Object b = null;
            while (iter.hasNext()) {
                OccupantInCol a = (OccupantInCol)iter.next();
                if (a == null) {
                    continue;
                } else if (a.getCol() == loc.getCol()) {
                    b = a.getOccupant();
                }
            }
            return b;
        }
    }

    public Object put(Location loc, Object obj) {
        if(!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        } else if(obj == null) {
            throw new NullPointerException("obj == null");
        } else {
            Object oldOccupant = this.get(loc);
            int row = loc.getRow();
            if (listArray[row] == null) {
                listArray[row] = new LinkedList<OccupantInCol>();
            }
            listArray[row].push(new OccupantInCol(obj, loc.getCol()));
            return oldOccupant;
        }
    }

    public Object remove(Location loc) {
        if(!this.isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        } else {
            Object r = this.get(loc);
            int row = loc.getRow();
            Iterator iter = listArray[row].listIterator();
            while (iter.hasNext()) {
                OccupantInCol a = (OccupantInCol)iter.next();
                if (a.getCol() == loc.getCol()) {
                    listArray[row].remove(a);
                    break;
                }
            }
            return r;
        }
    }
}
