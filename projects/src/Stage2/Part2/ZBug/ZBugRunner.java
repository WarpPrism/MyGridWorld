import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;

public class ZBugRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        ZBug bugA = new ZBug(3);
        ZBug bugB = new ZBug(6);
        bugB.setColor(Color.BLUE);
        world.add(new Location(0, 3), bugA);
        world.add(new Location(4, 0), bugB);
        world.show();
    }
}
