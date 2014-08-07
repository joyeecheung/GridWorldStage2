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

public class SparseBoundedGridTest {

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgsForConstructor() {
        // test illegal size
        Grid grid = new SparseBoundedGrid(0, 0);
    }

    @Test
    public void testIsValid() {
        Grid<Actor> grid = new SparseBoundedGrid(10, 10);

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
    public void testGetOccupiedLocations() {
        Grid<Actor> grid = new SparseBoundedGrid(10, 10);        

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

    @Test(expected=IllegalArgumentException.class)
    public void testNullLocForGet() {
        Grid<Actor> grid = new SparseBoundedGrid(10, 10);        
        grid.get(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidLocForGet() {
        Grid<Actor> grid = new SparseBoundedGrid(10, 10);        
        grid.get(new Location(10, 10));
    }

    @Test
    public void testGet() {
        Grid<Actor> grid = new SparseBoundedGrid(10, 10);        
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

    @Test
    public void testPut() {
    
    }

    @Test
    public void testRemove() {
    
    }
}
