import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */

/*
Modify the processActors method in ChameleonCritter so that
if the list of actors to process is empty,the color of the
ChameleonCritter will darken (like a flower).
*/
public class NewChameleonCritter extends Critter {
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            // The color gets darken
            Color c = this.getColor();
            double factor = 0.95D;
            int red = (int)((double)c.getRed() * factor);
            int blue = (int)((double)c.getBlue() * factor);
            int green = (int)((double)c.getGreen() * factor);
            this.setColor(new Color(red, green, blue));
        } else {
            int r = (int)(Math.random() * n);
            Actor other = actors.get(r);
            this.setColor(other.getColor());
        }
    }

    @Override
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
