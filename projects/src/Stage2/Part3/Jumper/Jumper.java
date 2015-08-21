import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

public class Jumper extends Actor {

    private Location nowLoc;
    private Location next1;
    private Location next2;

    public Jumper() {}

    public Jumper(Color c) {
        this.setColor(c);
    }

    public void turn() {
        this.setDirection(this.getDirection() + 45);
    }

    public boolean canMove() {
        Grid grid = this.getGrid();
        if (grid == null) {
            return false;
        } else {
            nowLoc = this.getLocation();
            next1 = nowLoc.getAdjacentLocation(getDirection());
            return grid.isValid(next1);
        }
    }

    public void move() {
        Grid grid = this.getGrid();
        nowLoc = this.getLocation();
        next1 = nowLoc.getAdjacentLocation(getDirection());
        // next1 is not valid
        if (!grid.isValid(next1)) {
            turn();
            return;
        }
        Actor next1obj = (Actor)grid.get(next1);

        next2 = next1.getAdjacentLocation(getDirection());
        // next1 is valid, but next2 is not valid.
        if (!grid.isValid((next2)) && next1obj == null) {
            this.moveTo(next1);
            return;
        }
        if (!grid.isValid((next2)) && next1obj != null) {
            turn();
            return;
        }
        Actor next2obj = (Actor)grid.get(next2);
        // next1 and next2 are both valid.
        if (next2obj == null) {
            this.moveTo(next2);
        } else if (next1obj == null){
            this.moveTo(next1);
        } else {
            turn();
        }
    }

    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();
        }
    }
}
