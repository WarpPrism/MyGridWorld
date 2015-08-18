import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zhoujihao on 15-8-18.
 */
public class RockHound extends Critter {
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            return;
        } else {
            Iterator i$ = actors.iterator();
            while (i$.hasNext()) {
                Actor a = (Actor)i$.next();
                if (a instanceof Rock) {
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
