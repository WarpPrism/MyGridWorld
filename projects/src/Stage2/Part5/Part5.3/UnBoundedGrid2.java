// Created By ZhouJihao

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UnBoundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    private int gridsize;

    public UnBoundedGrid2() {
        gridsize = 16;
        occupantArray = new Object[gridsize][gridsize];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc) {
        return true;
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<>();
        for (int i = 0; i < gridsize; ++i) {
            for (int j = 0; j < gridsize; ++j) {
                Location loc = new Location(i, j);
                if (this.get(loc) != null) {
                    a.add(loc);
                }
            }
        }
        return a;
    }

    public E get(Location loc) {
        if(loc == null) {
            throw new NullPointerException("loc == null");
        } else {
            /*int row = loc.getRow();
            int col = loc.getCol();
            if (row >= gridsize || col >= gridsize) {
                gridsize *= 2;
                Object[][] temp = new Object[gridsize][gridsize];
                ArrayList<Location> locs = this.getOccupiedLocations();
                for (Location l$ : locs) {
                    temp[l$.getRow()][l$.getCol()] = occupantArray[l$.getRow()][l$.getCol()];
                }
                this.occupantArray = temp;
            }*/
            E eobj = null;
            try {
                eobj = (E)this.occupantArray[loc.getRow()][loc.getCol()];
            } catch (Exception e) {
                /*System.out.println("Exception.");*/
                // Do Nothing will ignore unimportant Exception.
                ;
            }
            return eobj;
        }
    }

    public E put(Location loc, E obj) {
        if(loc == null) {
            throw new NullPointerException("loc == null");
        } else if(obj == null) {
            throw new NullPointerException("obj == null");
        } else {
            E old = this.get(loc);
            int row = loc.getRow();
            int col = loc.getCol();
            if (row < gridsize && col < gridsize) {
                this.occupantArray[row][col] = obj;
            } else if (row >= gridsize || col >= gridsize) {
                //int oldgridsize = gridsize;
                gridsize *= 2;
                Object[][] temp = new Object[gridsize][gridsize];
                // Too complete.
                /*for (int i = 0; i < oldgridsize; i++) {
                    for (int j = 0; j < oldgridsize; j++) {
                        temp[i][j] = occupantArray[i][j];
                    }
                }*/
                ArrayList<Location> locs = this.getOccupiedLocations();
                for (Location l$ : locs) {
                    temp[l$.getRow()][l$.getCol()] = occupantArray[l$.getRow()][l$.getCol()];
                }
                this.occupantArray = temp;
                this.occupantArray[row][col] = obj;
            }
            return old;
        }
    }

    public E remove(Location loc) {
        if(loc == null) {
            throw new NullPointerException("loc == null");
        } else {
            E r = this.get(loc);
            this.occupantArray[loc.getRow()][loc.getCol()] = null;
            return r;
        }
    }
}
