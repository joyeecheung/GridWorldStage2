import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.*;

public class UnboundedGrid2Test {

    @Test
    public void testNumRowsAndCols() {
        Grid grid = new UnboundedGrid2<Actor>();
        assertEquals(grid.getNumCols(), -1);
        assertEquals(grid.getNumRows(), -1);
    }

    @Test
    public void testIsValid() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();

        // test valid locations
        assertFalse("Location (-1, -1) valid in unbounded grid 2", 
                grid.isValid(new Location(-1, -1)));

        // test valid locations
        assertTrue("Location (100, 100) invalid in unbounded grid", 
                grid.isValid(new Location(100, 100)));
        assertTrue("Location (0, 0) invalid in 10 x 10 grid", 
                grid.isValid(new Location(0, 0)));
    }

    @Test
    public void testGetOccupiedLocations() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();        

        // test if a new grid is empty
        assertTrue("A newly created grid is not empty",
                grid.getOccupiedLocations().size() == 0);

        Location loc1 = new Location(100, 10);
        Location loc2 = new Location(32, 32);
        Location loc3 = new Location(300, 3);
        // put some actors in the grid, and check its size and content
        grid.put(loc1, new Flower());
        grid.put(loc2, new Rock());
        grid.put(loc3, new Bug());

        ArrayList<Location> result = grid.getOccupiedLocations();

        assertTrue("Wrong size after put", result.size() == 3);
        assertTrue("An occupied location is not returned",
                result.contains(loc1));
        assertTrue("An occupied location is not returned",
                result.contains(loc2));
        assertTrue("An occupied location is not returned",
                result.contains(loc3));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullLocForGet() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        grid.get(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidLocForGet() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        grid.get(new Location(-1, -1));
    }

    @Test
    public void testGet() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        assertNull("Empty grid doesn't return null for get()",
                grid.get(new Location(1, 1)));
        assertNull("Empty grid doesn't return null for get()",
                grid.get(new Location(100, 100)));

        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(0, 2);
        Location loc3 = new Location(0, 3);
        Flower flower = new Flower();
        Rock rock = new Rock();
        Bug bug = new Bug();
        // put some actors in the grid, and check its size and content
        grid.put(loc1, flower);
        grid.put(loc2, rock);
        grid.put(loc3, bug);

        assertSame("Wrong occupant is returned",
                grid.get(loc1), flower); 
        assertSame("Wrong occupant is returned",
                grid.get(loc2), rock); 
        assertSame("Wrong occupant is returned",
                grid.get(loc3), bug); 
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullLocForPut() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>(); 
        Actor a = new Actor();
        grid.put(null, a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidLocForPut() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        Actor a = new Actor();
        grid.put(new Location(-1, 10), a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullOccupantForPut() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        grid.put(new Location(10, 10), null);
    }

    @Test
    public void testPut() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();

        Location loc = new Location(133, 223);
        Actor a = new Actor();
        Actor b = new Actor();

        // put an actor into the grid, see if it can be got back 
        grid.put(loc, a);
        assertSame("Different occupant is returned",
                grid.get(loc), a);

        // put another actor into the same location, see if
        // the old occupant is repleced and returned
        Actor old = grid.put(loc, b);
        assertSame("The old occupant is not returned correctly",
                old, a);
        assertNotSame("The old occupant is not replaced",
                grid.get(loc), a);
        assertSame("The old occupant is not replaced",
                grid.get(loc), b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullLocForRemove() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>(); 
        grid.remove(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidLocForRemove() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();
        grid.remove(new Location(-2, 10));
    }

    @Test
    public void testRemove() {
        Grid<Actor> grid = new UnboundedGrid2<Actor>();

        assertNull("Remove from empty location doesn't return null",
                grid.remove(new Location(20, 234)));

        Location loc = new Location(112, 232);
        Actor a = new Actor();

        // see if the target is removed and returned properly
        grid.put(loc, a);
        Actor old = grid.remove(loc);
        assertSame("remove() doesn't return the removed object"
                + " correctly", old, a);
        assertNull("The location where the object has been removed"
                + " is not empty", grid.get(loc));
    }
}
