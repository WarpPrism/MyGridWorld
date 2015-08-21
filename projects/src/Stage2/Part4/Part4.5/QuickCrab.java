import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-20.
 */
public class QuickCrab extends CrabCritter {
    public ArrayList<Location> getLocationsInDirections$(int[] directions) {
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
        for (Location loc : this.getLocationsInDirections$(directions)) {
            if (grid.get(loc) == null) {
                locs.add(loc);
            }
        }
        return locs;
    }
}
