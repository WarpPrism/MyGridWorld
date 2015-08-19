import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */
public class NewChameleonCritter extends Critter {
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            // The color gets darken
            Color c = this.getColor();
            int red = (int)((double)c.getRed() * 0.95D);
            int blue = (int)((double)c.getBlue() * 0.95D);
            int green = (int)((double)c.getGreen() * 0.95D);
            this.setColor(new Color(red, green, blue));
            return;
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
