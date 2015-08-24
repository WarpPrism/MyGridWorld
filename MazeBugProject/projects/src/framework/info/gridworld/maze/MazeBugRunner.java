package info.gridworld.maze;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        //UnboundedGrid ugr=new UnboundedGrid();
        ActorWorld world = new ActorWorld(); 
        /*world.add(new Location(-1,-1),new MazeBug());
        for(int i=0;i<=40;i++){
        	for(int j=0;j<=40;j+=40){
        		world.add(new Location(i,j),new Rock());
        	}        	
        }
        for(int i=0;i<=40;i+=40){
        	for(int j=0;j<=40;j++){
        		world.add(new Location(i,j),new Rock());
        	}        	
        }*/
        world.add(new Location(0,0), new MazeBug());
        world.add(new Location(1,1),new Rock());
        world.show();
        /*ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random() * 100));
        }
        Collections.sort(list);
        System.out.println(Arrays.toString(list.toArray()));*/
    }
}