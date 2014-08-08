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

    @Test
    public void testIfCanBeBlockedByFlowers() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a DarckCritter in the world
        DarkCritter dc = new DarkCritter();
        Location oldLoc = new Location(4, 4);
        world.add(oldLoc, dc);

        world.add(new Location(3, 3), new Flower());
        world.add(new Location(3, 4), new Flower());
        world.add(new Location(3, 5), new Flower());
        Flower togo = new Flower();
        world.add(new Location(4, 3), togo);
        world.add(new Location(4, 5), new Flower());
        world.add(new Location(5, 3), new Flower());
        world.add(new Location(5, 4), new Flower());
        world.add(new Location(5, 5), new Flower());

        dc.act();
        assertEquals("DarkCritter cannot be blocked by flowers",
                dc.getLocation(), oldLoc);

        togo.removeSelfFromGrid();
        dc.act();

        assertFalse("DarkCritter cannot move to the right location",
                dc.getLocation().equals(oldLoc));
    }
}
