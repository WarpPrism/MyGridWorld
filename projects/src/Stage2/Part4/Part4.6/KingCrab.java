import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-20.
 */
/*
Create a class KingCrab that extends CrabCritter. A KingCrab gets the
actors to be processed in the same way a CrabCritter does. A KingCrab
causes each actor that it processes to move one location further away
from the KingCrab. If the actor cannot move away, the KingCrab removes
it from the grid. When the KingCrab has completed processing the actors,
it moves like a CrabCritter.
*/
public class KingCrab extends CrabCritter {
    @Override
    public void processActors(ArrayList<Actor> actors) {
        Location kingloc = this.getLocation();
        Grid grid = this.getGrid();
        for (Actor a : actors) {
            Location aloc = a.getLocation();
            int awaydirection = aloc.getDirectionToward(kingloc) + Location.HALF_CIRCLE;
            Location awayloc = aloc.getAdjacentLocation(awaydirection);
            if (!grid.isValid(awayloc)) {
                a.removeSelfFromGrid();
                continue;
            }
            Actor b = (Actor)grid.get(awayloc);
            if (b != null) {
                a.removeSelfFromGrid();
            } else {
                a.moveTo(awayloc);
            }
        }
    }
}
