import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Created by zhoujh on 2015/7/23.
 */
public class JumperRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        Jumper jumper1 = new Jumper(Color.BLACK);
        Jumper jumper2 = new Jumper(Color.RED);
        Jumper jumper3 = new Jumper(Color.YELLOW);
        Rock rock = new Rock();

        world.add(new Location(5, 5),jumper1);
        world.add(new Location(4, 0), jumper2);
        world.add(new Location(6, 0), jumper3);
        world.add(new Location(5, 0), rock);

        world.show();
    }
}
