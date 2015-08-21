import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import java.awt.*;

/**
 * Created by zhoujihao on 15-8-21.
 */
public class JumperTest {
    private ActorWorld world;
    private Jumper jumper = new Jumper(Color.CYAN);
    private Rock rock = new Rock();
    private Flower flower = new Flower();

    @Before
    public void setUp() throws Exception {
        world = new ActorWorld();
        world.add(new Location(0, 0), jumper);
        jumper.setDirection(Location.NORTH);
    }

    // Jumper try to jump out of the grid, next2 is not valid
    @Test
    public void testJumpOutTheGrid() {
        jumper.act();
        assertEquals(new Location(0, 0), jumper.getLocation());
        assertEquals(Location.NORTHEAST, jumper.getDirection());
        jumper.act();
        assertEquals(new Location(0, 0), jumper.getLocation());
        assertEquals(Location.EAST, jumper.getDirection());
        jumper.moveTo(new Location(1, 0));
        jumper.setDirection(Location.NORTH);
        jumper.act();
        assertEquals(new Location(0, 0), jumper.getLocation());
        assertEquals(Location.NORTH, jumper.getDirection());
    }

    // Jumper jumps over flower and rocks, next2 is empty
    @Test
    public void testJumperJumps() {
        jumper.moveTo(new Location(3, 0));
        world.add(new Location(2, 0), rock);
        jumper.act();
        assertEquals(new Location(1, 0), jumper.getLocation());
        assertEquals(Location.NORTH, jumper.getDirection());
        jumper.moveTo(new Location(3, 0));
        world.add(new Location(2, 0), flower);
        jumper.act();
        assertEquals(new Location(1, 0), jumper.getLocation());
        assertEquals(Location.NORTH, jumper.getDirection());
        jumper.moveTo(new Location(3, 0));
        jumper.act();
        assertEquals(new Location(1, 0), jumper.getLocation());
        assertEquals(Location.NORTH, jumper.getDirection());

    }

    // next1 is empty while next2 is not empty.
    @Test
    public void testMoveToNext1() {
        jumper.moveTo(new Location(5, 5));
        world.add(new Location(3, 5), rock);
        jumper.act();
        assertEquals(new Location(4, 5), jumper.getLocation());
        assertEquals(Location.NORTH, jumper.getDirection());
    }

    // next1 is not empty and next2 neither.
    @Test
    public void testTurn() {
        jumper.moveTo(new Location(5, 5));
        world.add(new Location(3, 5), rock);
        world.add(new Location(4, 5), flower);
        jumper.act();
        assertEquals(new Location(5, 5), jumper.getLocation());
        assertEquals(Location.NORTHEAST, jumper.getDirection());
    }

    @After
    public void end() {
        jumper.removeSelfFromGrid();
    }
}
