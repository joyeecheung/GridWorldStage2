import info.gridworld.grid.*;
import java.util.ArrayList;
import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an
 * unbounded number of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB Exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    // current size
    private int size;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        size = 16;
        occupantArray = new Object[size][size];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        // All valid locations have non-negative row and
        // column values. 

        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < size; r++)
        {
            for (int c = 0; c < size; c++)
            {
                Location loc = new Location(r, c);
                if (get(loc) != null)
                {
                    theLocations.add(loc);
                }
            }
        }
        return theLocations;
    }

    public E get(Location loc)
    {
        if (loc == null)
        {
            throw new IllegalArgumentException("loc == null");
        }

        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        // The location is out of the grid
        if (loc.getRow() >= size || loc.getCol() >= size)
        {
            return null;
        }

        Object obj = occupantArray[loc.getRow()][loc.getCol()];
        if (obj == null)
        {
            return null;
        }
        else
        {
            return (E) obj;
        }
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
        {
            throw new IllegalArgumentException("loc == null");
        }

        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
     
        if (obj == null)
        {
            throw new IllegalArgumentException("obj == null");
        }        

        // if the Location is out of the grid, resize the grid
        if (loc.getRow() >= size || loc.getCol() >= size)
        {
            resize(loc);
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (loc == null)
        {
            throw new IllegalArgumentException("loc == null");
        }

        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        E obj = get(loc);

        // if the location is empty or not in the grid, return null
        if (obj == null)
        {
            return null;
        }

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    private void resize(Location loc)
    {
        // double both array bounds until they are large enough
        int sizeNeeded = size;
        while (loc.getRow() >= sizeNeeded || loc.getCol() >= sizeNeeded)
        {
            sizeNeeded *= 2;
        }

        Object[][] temp = new Object[sizeNeeded][sizeNeeded];

        // copy the occupants over
        for(int r = 0; r < size; r++)
        {
            for(int c = 0; c < size; c++)
            {
                temp[r][c] = occupantArray[r][c];
            }
        }

        // update the occupantArray and the zie
        occupantArray = temp;
        size = sizeNeeded;
    }
}
