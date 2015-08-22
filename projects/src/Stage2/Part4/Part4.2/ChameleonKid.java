import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */
/*
Create a class called ChameleonKid that extends ChameleonCritter
 as modified in exercise 1. A ChameleonKid changes its color to
 the color of one of the actors immediately in front or behind.
 If there is no actor in either of these locations, then the
 ChameleonKid darkens like the modified ChameleonCritter.
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
