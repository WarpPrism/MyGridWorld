import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;

public class BlusterRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(5, 7), new Critter());
        world.add(new Location(5, 8), new Critter());
        world.add(new Location(5, 9), new Critter());
        world.add(new Location(5, 2), new Critter());
        world.add(new Location(5, 1), new Critter());
        world.add(new Location(4, 4), new BlusterCritter(0, Color.WHITE));
        world.add(new Location(4, 6), new BlusterCritter(7, Color.BLACK));
        world.show();
    }
}