import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A SparseBoundedGrid is a rectangular grid with a finite
 * number of rows and columns and a sparse array implementation.
 * This is the HashMap version.
 * 
 * @author joyeecheung
 *
 * @param <E>
 *            Type of occupants in the grids.
 */
public class SparseBoundedGrid3<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int rows;
    private int cols;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid3(int rows, int cols)
    {
        if (rows <= 0)
        {
            throw new IllegalArgumentException("rows <= 0");
        }

        if (cols <= 0)
        {
            throw new IllegalArgumentException("cols <= 0");
        }

        this.cols = cols;
        this.rows = rows;

        occupantMap = new HashMap<Location, E>();
    }

    /**
     * Get number of rows.
     * 
     * @return number of rows.
     */
    @Override
    public int getNumRows()
    {
        return rows;
    }

    /**
     * Get number of columns.
     * 
     * @return number of columns.
     */
    @Override
    public int getNumCols()
    {
        return cols;
    }

    /**
     * Check if the given location is valid in this grid.
     * 
     * @return true if the given location is valid in this grid.
     */
    @Override
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
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
        for (Location loc : occupantMap.keySet())
        {
            theLocations.add(loc);
        }

        return theLocations;
    }

    /**
     * Get the content in given location.
     * 
     * @return the content in given location.
     */
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

        return occupantMap.get(loc);
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

        //Add the object to the grid.
        return occupantMap.put(loc, obj);
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

        // if the location is empty, return null
        if (obj == null)
        {
            return null;
        }

        return occupantMap.remove(loc);
    }
}
