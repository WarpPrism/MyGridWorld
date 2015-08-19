import info.gridworld.actor.Bug;

public class ZBug extends Bug {
    private int steps;
    private int sideLength;
    private int sideNum;

    public ZBug(int length) {
        sideLength = length;
        steps = 0;
        sideNum = 0;
        while (this.getDirection() != 90) {
            turn();
        }
    }

    public void act() {

        if ((sideNum < 3 && !canMove()) || sideNum == 3) {
            // stop move
        } else if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else if (sideNum == 0){
            sideNum++;
            turn();
            turn();
            turn();
            steps = 0;
        } else if (sideNum == 1) {
            sideNum++;
            turn();
            turn();
            turn();
            turn();
            turn();
            steps = 0;
        } else {
            sideNum++;
        }
    }
}
