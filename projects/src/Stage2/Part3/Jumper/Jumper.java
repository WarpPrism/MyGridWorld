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
            if (!grid.isValid(next1)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void move() {
        Grid grid = this.getGrid();
        nowLoc = this.getLocation();
        next1 = nowLoc.getAdjacentLocation(getDirection());
        next2 = next1.getAdjacentLocation(getDirection());
        Actor next1obj = (Actor)grid.get(next1);
        // next2 is out of grid, move one step
        if (!grid.isValid(next2) && next1obj == null) {
            this.moveTo(next1);
            return;
        }

        if (!grid.isValid(next2) && next1obj != null) {
            turn();
            return;
        }
        Actor next2obj = (Actor)grid.get(next2);

        // next2obj is flower or rock, move one step
        if (next2obj instanceof Flower || next2obj instanceof Rock) {
            this.moveTo(next1);
            return;
        }
        // next2obj is Bug, wait
        if (next2obj instanceof Bug) {
            turn();
            return;
        }
        // next2obj is Jumper
        if (next2obj instanceof Jumper && next1obj == null) {
            this.moveTo(next1);
            return;
        }

        // jump over flower and rock
        if (next1obj instanceof  Flower || next1obj instanceof Rock) {
            this.moveTo(next2);
            return;
        }

        if (next2obj == null) {
            this.moveTo(next2);
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
