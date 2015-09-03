package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	private Integer stepCount = 0;
    //final message has been shown
	private boolean hasShown = false;

    private boolean returnflag = false;
    private HashMap<Integer, Integer> probability;


	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
        // Define probability Array.
        int d = 4;
        probability = new HashMap<Integer, Integer>();
        probability.put(0, 1);
        probability.put(90, 1);
        probability.put(180, 1);
        probability.put(270, 1);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
        // If the bug complete the move,return.
        if (hasShown) {
            return;
        }
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		}
        // After Every Move, judge if the bug gets the target.
        Grid<Actor> gr = this.getGrid();
        Location loc = this.getLocation();
        int[] directions = { Location.NORTH, Location.SOUTH, Location.EAST, Location.WEST };
        for (int dir : directions) {
            Location temp = loc.getAdjacentLocation(dir);
            if (gr.isValid(temp)) {
                Actor target = gr.get(temp);
                if (target != null && target instanceof Rock && (target.getColor().getRed() == 255)) {
                    System.out.println("Nice Move!");
                    isEnd = true;
                    return;
                }
            }
        }
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
            return null;
        }
		ArrayList<Location> valid = new ArrayList<Location>();
        int[] directions = {Location.EAST, Location.SOUTH, Location.WEST, Location.NORTH};
        for (int dir : directions) {
            Location temp = loc.getAdjacentLocation(dir);
            if (gr.isValid(temp)) {
                if (gr.get(temp) == null) {
                    valid.add(temp);
                }
            }
        }
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
            return;
        }
		Location loc = getLocation();
        last = loc;
        usingDFSGetNext();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
            removeSelfFromGrid();
        }
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

    // DFS Algorithm to get the next position.
    public void usingDFSGetNext() {
        Location current = this.getLocation();
        // get all null position.
        ArrayList<Location> valid = this.getValid(current);
        ArrayList<Location> stacknode = new ArrayList<>();
        // Using stacknode[0] to store the last position.
        stacknode.add(0, last);
        stacknode.addAll(valid);
        if (!returnflag) {
            crossLocation.push(stacknode);
            /*System.out.println(crossLocation)*/
        }

        if (valid.size() == 0) {
            // The bug returns because there is no way to go.
            if (!crossLocation.empty()) {
                crossLocation.pop();
            }
            if (!crossLocation.empty()) {
                next = crossLocation.peek().get(0);
                int wrongdir = next.getDirectionToward(last);
                int count = probability.get(wrongdir) - 1;
                probability.remove(wrongdir);
                probability.put(wrongdir, count);
            }
            // set returnflag as true
            returnflag = true;

        } else {
            // Choose a random location from valide location as the next.
            // random Move!!
            /*int random = 0
            random = (int)(Math.random() * valid.size())*/

            // Probability Move!!
            ArrayList<Integer> dirs = new ArrayList<Integer>();
            for (Location loc : valid) {
                int dir = last.getDirectionToward(loc);
                dirs.add(dir);
            }
            // select max from the probability map
            int correctdir = dirs.get(0);
            int maxargs = probability.get(correctdir);
            for (Integer dir : dirs) {
                int temp = probability.get(dir);
                if (temp >= maxargs) {
                    maxargs = temp;
                    correctdir = (int) dir;
                }
            }
            /*Collections.sort(args)
            int sum = 0
            for (Integer i : args) {
                sum += i
            }
            int random = (int) (((double)sum) * Math.random())
            int down = 0
            int up = 0
            int correctdir = dirs.get(0)
            int args_choosed = 0
            for (int j = 0; j < args.size(); j++) {
                up = args.get(j) + down
                if (random < up && random >= down) {
                    args_choosed = up
                    break
                } else {
                	down = up
                }
            }
            Iterator iter = probability.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next()
                if (entry.getValue() == args_choosed) {
                    correctdir = (int) entry.getKey()
                    if (!dirs.contains(correctdir)) {
                        correctdir = dirs.get(0)
                    }
                }
            }*/

            int count = probability.get(correctdir) + 1;
            probability.remove(correctdir);
            probability.put(correctdir, count);
            next = last.getAdjacentLocation(correctdir);
            // Probability Move!!

            /*next = valid.get(random)*/
            returnflag = false;
        }
    }
}
