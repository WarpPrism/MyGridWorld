import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-20.
 */
/*
Create a class QuickCrab that extends CrabCritter. A QuickCrab processes
 actors the same way a CrabCritter does. A QuickCrab moves to one of the
 two locations, randomly selected, that are two spaces to its right or left,
 if that location and the intervening location are both empty. Otherwise,
 a QuickCrab moves like a CrabCritter.
*/
public class QuickCrab extends CrabCritter {
    public ArrayList<Location> getLocationsInDirections1(int[] directions) {
        ArrayList<Location> locs = new ArrayList<>();
        Location nowloc = this.getLocation();
        Grid grid = this.getGrid();
        for (int d : directions) {
            Location next1 = nowloc.getAdjacentLocation(this.getDirection() + d);
            Location next2 = next1.getAdjacentLocation(this.getDirection() + d);
            if (grid.isValid(next2)) {
                locs.add(next2);
            }
        }
        return locs;
    }

    @Override
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<>();
        Grid grid = this.getGrid();
        int[] directions = {Location.LEFT, Location.RIGHT};
        for (Location loc : this.getLocationsInDirections1(directions)) {
            if (grid.get(loc) == null) {
                locs.add(loc);
            }
        }
        return locs;
    }
}
