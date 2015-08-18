import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhoujihao on 15-8-18.
 */
public class BlusterCritter extends Critter {
    private int courage;

    public BlusterCritter() {}
    public BlusterCritter(int c) {
        this.courage = c;
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
        System.out.println(count);
        return actors;
    }

   /* @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n >= courage) {
            // The color gets darken
            Color c = this.getColor();
            int red = (int)((double)c.getRed() * 0.95D);
            int blue = (int)((double)c.getBlue() * 0.95D);
            int green = (int)((double)c.getGreen() * 0.95D);
            this.setColor(new Color(red, green, blue));
        } else if (n < courage) {
            // The color gets brighten
            Color c = this.getColor();
            int red = (int)((double)c.getRed() * 1.05D);
            int blue = (int)((double)c.getBlue() * 1.05D);
            int green = (int)((double)c.getGreen() * 1.05D);
            this.setColor(new Color(red, green, blue));
        }
    }*/
}
