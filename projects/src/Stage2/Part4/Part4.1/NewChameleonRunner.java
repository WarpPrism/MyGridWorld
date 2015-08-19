import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Created by zhoujihao on 15-8-18.
 */
public class NewChameleonRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(4, 4), new NewChameleonCritter());
        NewChameleonCritter another = new NewChameleonCritter();
        another.setColor(Color.red);
        world.add(new Location(7, 3), another);
        world.show();
    }
}
