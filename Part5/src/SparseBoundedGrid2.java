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
public class SparseBoundedGrid2<E> extends AbstractBoundedGrid<E>
{
    private Map<Location, E> occupantMap;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        super(rows, cols);
        occupantMap = new HashMap<Location, E>();
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
        checkLocation(loc);
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
        checkLocation(loc);

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
        checkLocation(loc);

        E obj = get(loc);

        // if the location is empty, return null
        if (obj == null)
        {
            return null;
        }

        return occupantMap.remove(loc);
    }
}
