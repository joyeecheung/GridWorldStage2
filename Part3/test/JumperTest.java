import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import info.gridworld.grid.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JumperTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /** 
     * a. What will a jumper do if the location in front of it is empty,
     * but the location two cells in front contains a flower or a rock？
     *
     * Answer: turn for a Rock, remove the flower.
     */
    @Test
    public void testEmptyFrontWithBlockingFlower() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a jumper with empty front and a flower in destination
        Jumper jumper = new Jumper();
        Flower flower = new Flower();
        world.add(new Location(4, 4), jumper);
        world.add(new Location(2, 4), flower);

        // test if it reach the destination after act()
        jumper.act();
        assertEquals("Jumper doesn't jump on top of the flower: wrong row",
                jumper.getLocation().getRow(), 2);
        assertEquals("Jumper doesn't jump on top of the flower: wrong column",
                jumper.getLocation().getCol(), 4);
        
        // test if the flower is removed after act()
        assertNull("Flower isn't removed", flower.getGrid());
    }

    /** 
     * a. What will a jumper do if the location in front of it is empty,
     * but the location two cells in front contains a flower or a rock？
     *
     * Answer: turn for a Rock, remove the flower.
     */
    @Test
    public void testEmptyFrontWithBlockingRock() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a jumper with empty front and a rock in destination
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(4, 2), jumper);
        world.add(new Location(2, 2), rock);

        // test if it turns after act()
        int previousDir = jumper.getDirection();
        jumper.act();
        assertFalse("Jumper doesn't turn for the rock.",
                jumper.getDirection() == previousDir);
        
        // test if the Rock is still in the same location
        assertNotNull("Rock is not in the grid", rock.getGrid());
        assertEquals("Rock is not in the same row",
                rock.getLocation().getRow(), 2);
        assertEquals("Rock is not in the same column",
                rock.getLocation().getCol(), 2);
    }

    /**
     * b. What will a jumper do if the location two cells in front of
     * the jumper is out of the grid?
     * 
     * Answer: turn.
     */
    @Test
    public void testInvalidDestination() {
        // set up the world
        ActorWorld world = new ActorWorld();
        
        // place a jumper one way away from the edge 
        Jumper jumper = new Jumper();
        world.add(new Location(1, 1), jumper);

        // test if it turns after act()
        int previousDir = jumper.getDirection();
        jumper.act();
        assertFalse("Jumper doesn't turn for invalid destination.",
                jumper.getDirection() == previousDir);
        
    }

    /** 
     * c. What will a jumper do if it is facing an edge of the grid?
     *
     * Answer: turn.
     */
    @Test
    public void testFacingEdge() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a jumper facing the edge
        Jumper jumper = new Jumper();
        world.add(new Location(0, 3), jumper);

        // test if it turns after act()
        int previousDir = jumper.getDirection();
        jumper.act();
        assertFalse("Jumper doesn't turn for invalid destination.",
                jumper.getDirection() == previousDir);
    }

    /**
     * d. What will a jumper do if another actor (not a flower or a rock)
     * is in the cell that is two cells in front of the jumper?
     *
     * Answer: turn
     */
    @Test
    public void testNonStaticBlocker() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a jumper with empty front and a bug in destination
        Jumper jumper = new Jumper();
        Bug bug = new Bug();
        world.add(new Location(4, 5), jumper);
        world.add(new Location(2, 5), bug);

        // test if it turns after act()
        int previousDir = jumper.getDirection();
        jumper.act();
        assertFalse("Jumper doesn't turn for the bug.",
                jumper.getDirection() == previousDir);
        
        // test if the bug is still in the same location
        assertNotNull("bug is not in the grid", bug.getGrid());
        assertEquals("bug is not in the same row",
                bug.getLocation().getRow(), 2);
        assertEquals("bug is not in the same column",
                bug.getLocation().getCol(), 5);
    }

    /**
     * e. What will a jumper do if it encounters another jumper in its path?
     *
     * Answer: turn
     */
    @Test
    public void testEncounterAnotherJumper() {
        // set up the world
        ActorWorld world = new ActorWorld();

        // place a jumper with empty front and a jumper2 in destination
        Jumper jumper = new Jumper();
        Jumper jumper2 = new Jumper();
        world.add(new Location(4, 6), jumper);
        world.add(new Location(2, 6), jumper2);

        // test if it turns after act()
        int previousDir = jumper.getDirection();
        jumper.act();
        assertFalse("Jumper doesn't turn for the jumper2.",
                jumper.getDirection() == previousDir);
        
        // test if the jumper2 is still in the same location
        assertNotNull("jumper2 is not in the grid", jumper2.getGrid());
        assertEquals("jumper2 is not in the same row",
                jumper2.getLocation().getRow(), 2);
        assertEquals("jumper2 is not in the same column",
                jumper2.getLocation().getCol(), 6);
    }
}
