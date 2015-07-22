import info.gridworld.actor.ActorWorld;

public class DancingBugRunner {
    public static void main(String args[]) {
        ActorWorld world = new ActorWorld();
        int[] turnArr = {2, 3, 5, 1, 0, 6, 2};
        DancingBug bug = new DancingBug(turnArr);
        world.add(bug);
        world.show();
    }
}
