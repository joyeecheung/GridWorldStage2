import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;

import info.gridworld.grid.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DarkCritterTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /** 
     * Test if the neighbors are turned black by the DarkCritter.
     */
    @Test
    public void testIfNeighborsTurnedBlack() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a DarckCritter in the world
        DarkCritter dc = new DarkCritter();
        world.add(new Location(4, 4), dc);

        // place some neighbors for the DarkCritter
        Flower flower = new Flower();
        Rock rock = new Rock();
        Bug bug = new Bug();
        Critter critter = new Critter();

        world.add(new Location(5, 4), flower);
        world.add(new Location(5, 5), rock);
        world.add(new Location(4, 5), bug);
        world.add(new Location(3, 5), critter);

        // test if the neighbors are darkened after act()
        dc.act();
        assertEquals("Flower is not turned black", flower.getColor(), dc.getColor()); 
        assertEquals("Rock is not turned black", rock.getColor(), dc.getColor()); 
        assertEquals("Bug is not turned black", bug.getColor(), dc.getColor()); 
        assertEquals("Critter is not turned black", critter.getColor(), dc.getColor()); 
    }
}
