import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import org.junit.Test;

public class SparseBoundedGrid3Test
{

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgsForConstructor()
    {
        // test illegal size
        Grid grid = new SparseBoundedGrid3<Actor>(0, 0);
    }

    @Test
    public void testIsValid()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);

        // test invalid locations
        assertFalse("Location (16, 16) valid in 10 x 10 grid",
                grid.isValid(new Location(16, 16)));
        assertFalse("Location (10, 10) valid in 10 x 10 grid",
                grid.isValid(new Location(10, 10)));

        // test valid locations
        assertTrue("Location (1, 1) invalid in 10 x 10 grid",
                grid.isValid(new Location(1, 1)));
        assertTrue("Location (0, 0) invalid in 10 x 10 grid",
                grid.isValid(new Location(0, 0)));
    }

    @Test
    public void testGetOccupiedLocations()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);

        // test if a new grid is empty
        assertTrue("A newly created grid is not empty",
                grid.getOccupiedLocations().size() == 0);

        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(0, 2);
        Location loc3 = new Location(0, 3);
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

    @Test(expected = NullPointerException.class)
    public void testNullLocForGet()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);
        grid.get(null);
    }

    @Test
    public void testGet()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);
        assertNull("Empty grid doesn't return null for get()",
                grid.get(new Location(1, 1)));

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

    @Test(expected = NullPointerException.class)
    public void testNullLocForPut()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);
        Actor a = new Actor();
        grid.put(null, a);
    }

    @Test(expected = NullPointerException.class)
    public void testNullOccupantForPut()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);
        grid.put(new Location(0, 0), null);
    }

    @Test
    public void testPut()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);

        Location loc = new Location(1, 2);
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

    @Test(expected = NullPointerException.class)
    public void testNullLocForRemove()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);
        grid.remove(null);
    }

    @Test
    public void testRemove()
    {
        Grid<Actor> grid = new SparseBoundedGrid3<Actor>(10, 10);

        assertNull("Remove from empty location doesn't return null",
                grid.remove(new Location(2, 2)));

        Location loc = new Location(1, 2);
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
