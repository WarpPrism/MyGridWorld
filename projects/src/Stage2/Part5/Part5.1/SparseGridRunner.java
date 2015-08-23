import info.gridworld.actor.*;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public class SparseGridRunner {
    /*The World has a public addGridClass method. Since the ActorWorld is a World,
    you can call this method in a runner. Here is the code to add a new grid to the GUI.*/
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("UnBoundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.add(new Location(3, 3), new Bug());
        world.add(new Location(4, 4), new NewChameleonCritter());
        world.show();
    }
}