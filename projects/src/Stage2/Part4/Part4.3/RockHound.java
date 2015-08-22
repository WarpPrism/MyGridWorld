import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zhoujihao on 15-8-18.
 */
/*
Create a class called RockHound that extends Critter. A RockHound
 gets the actors to be processed in the same way as a Critter. It
  removes any rocks in that list from the grid. A RockHound moves
  like a Critter.
*/
public class RockHound extends Critter {
    @Override
    public void processActors(ArrayList<Actor> actors) {
        Iterator i = actors.iterator();
        while (i.hasNext()) {
            Actor a = (Actor)i.next();
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }
}
