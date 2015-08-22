import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */
/*
Create a class BlusterCritter that extends Critter. A BlusterCritter
looks at all of the neighbors within two steps of its current location.
(For a BlusterCritter not near an edge, this includes 24 locations).
It counts the number of critters in those locations. If there are fewer
than c critters, the BlusterCritter's color gets brighter (color values increase).
If there are c or more critters, the BlusterCritter's color darkens (color values decrease).
 Here, c is a value that indicates the courage of the critter. It should be set
 in the constructor.
*/
public class BlusterCritter extends Critter {
    private int courage;

    public BlusterCritter() {}
    public BlusterCritter(int c, Color color) {
        this.courage = c;
        this.setColor(color);
    }

    @Override
    public ArrayList<Actor> getActors() {
        int count = 0;
        ArrayList<Actor> actors = new ArrayList<>();
        Location loc = this.getLocation();
        int row = loc.getRow();
        int column = loc .getCol();
        Grid grid = this.getGrid();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i == 0  && j == 0) {
                    // do nothing
                    ;
                } else {
                    Location newloc = new Location(row + i, column + j);
                    if (grid.isValid(newloc)) {
                        Actor a = (Actor)grid.get(newloc);
                        if (a != null && a instanceof Critter) {
                            actors.add(a);
                            count++;
                        }
                    }
                }
            }
        }
        return actors;
    }

    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n >= courage) {
            // The color gets darken
            Color c = this.getColor();
            double factor1 = 0.95D;
            int red = (int)((double)c.getRed() * factor1);
            int blue = (int)((double)c.getBlue() * factor1);
            int green = (int)((double)c.getGreen() * factor1);
            if (red < 0) {
                red = 0;
            }
            if (green < 0) {
                green = 0;
            }
            if (blue < 0) {
                blue = 0;
            }
            this.setColor(new Color(red, green, blue));
        } else if (n < courage) {
            // The color gets brighten
            Color c = this.getColor();
            int factor2 = 15;
            int red = (int)((double)c.getRed() + factor2);
            int blue = (int)((double)c.getBlue() + factor2);
            int green = (int)((double)c.getGreen() + factor2);
            if (red > 255) {
                red = 255;
            }
            if (green > 255) {
                green = 255;
            }
            if (blue > 255) {
                blue = 255;
            }
            this.setColor(new Color(red, green, blue));
        }
    }
}
