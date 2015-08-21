import info.gridworld.actor.Bug;

/**
 * Created by zhoujh on 2015/7/22.
 */
public class DancingBug extends Bug {
    private int[] turnArr;
    private int index;
    private int times;
    // judge if turn is done.
    boolean flag;

    public DancingBug(int[] turnArr) {
        this.turnArr = turnArr;
        this.index = 0;
        times = 0;
        flag = false;
    }

    public void act() {
        // No matter if the bug can move just turn
        if (!flag && times < turnArr[index]) {
            turn();
            times++;
            return;
        }
        if (!flag && times >= turnArr[index]) {
            times = 0;
            // turn is done
            flag = true;
            // next array item
            index++;
            if (index == turnArr.length) {
                index = 0;
            }
        }
        if (canMove() && flag) {
            move();
            flag = false;
        } else if (!canMove() && flag) {
            turn();
        }
    }
}
