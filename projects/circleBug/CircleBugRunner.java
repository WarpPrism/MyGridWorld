import info.gridworld.actor.ActorWorld;

import java.awt.*;

public class CircleBugRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        CircleBug bugA = new CircleBug(5);
        CircleBug bugB = new CircleBug(3);
        bugA.setColor(Color.BLUE);
        // random location
        world.add(bugA);
        world.add(bugB);
        world.show();
    }
}
