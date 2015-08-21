import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */
public class ChameleonKid extends NewChameleonCritter {
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        int[] dirs = {Location.AHEAD, Location.FULL_CIRCLE};
        for (Location loc:getLocationsInDirections(dirs)) {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<>();
        Grid gr = getGrid();
        for (int dir:directions) {
            Location loc = getLocation().getAdjacentLocation(getDirection() + dir);
            if (gr.isValid(loc)) {
                locs.add(loc);
            }
        }
        return locs;
    }
}
