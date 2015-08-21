import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Created by zhoujh on 2015/7/23.
 */
public class JumperRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        Jumper jumper1 = new Jumper(Color.black);
        Rock rock = new Rock();
        Bug bug = new Bug();
        world.add(new Location(7, 5),jumper1);
        world.add(new Location(6, 5), rock);
        world.add(new Location(3, 3), bug);

        world.show();
    }
}
