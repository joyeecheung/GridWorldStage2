import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an
 * unbounded number of rows and columns. <br />
 * This is the resizable version.
 * 
 * @author joyeecheung
 *
 * @param <E>
 *            Type of occupants in the grids.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    // current size
    private int size;
    // default size
    private static final int DEFAULT_SIZE = 16;

    /**
     * Constructs an empty unbounded grid.
     * The default size is 16 x 16;
     */
    public UnboundedGrid2()
    {
        size = DEFAULT_SIZE;
        occupantArray = new Object[size][size];
    }

    /**
     * Get number of rows.
     * 
     * @return number of rows.
     */
    @Override
    public int getNumRows()
    {
        return -1;
    }

    /**
     * Get number of columns.
     * 
     * @return number of columns.
     */
    @Override
    public int getNumCols()
    {
        return -1;
    }

    /**
     * Check if the given location is valid in this grid.
     * 
     * @return true if the given location is valid in this grid.
     */
    @Override
    public boolean isValid(Location loc)
    {
        // All valid locations have non-negative row and
        // column values.

        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    /**
     * Get occupied locations in this grid.
     * 
     * @return an ArrayList of Location containing occupied
     *         locations in this grid.
     */
    @Override
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

    /**
     * Get the content in given location.
     * 
     * @return the content in given location.
     */
    @SuppressWarnings("unchecked")
    @Override
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

    /**
     * Put the given object in given location.
     * 
     * @return the original content in the location.
     */
    @Override
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

    /**
     * Remove the occupants in given location.
     * 
     * @return the original content in the location.
     */
    @Override
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
        for (int r = 0; r < size; r++)
        {
            for (int c = 0; c < size; c++)
            {
                temp[r][c] = occupantArray[r][c];
            }
        }

        // update the occupantArray and the zie
        occupantArray = temp;
        size = sizeNeeded;
    }
}
