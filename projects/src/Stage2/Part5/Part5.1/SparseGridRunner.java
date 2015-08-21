import info.gridworld.actor.*;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public class SparseGridRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        /*world.addGridClass("SparseBoundedGrid3");*/
        world.addGridClass("UnBoundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.add(new Location(3, 3), new Bug());
        world.add(new Location(4, 4), new NewChameleonCritter());
        world.show();
    }
}