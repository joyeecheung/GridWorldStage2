import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * A SparseBoundedGrid is a rectangular grid with a finite
 * number of rows and columns and a sparse array implementation. 
 * This is the HashMap version.
 */
public class SparseBoundedGrid3<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int rows;
    private int cols;

    /**
     * Constructs an empty unbounded grid.
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

    public int getNumRows()
    {
        return rows;
    }

    public int getNumCols()
    {
        return cols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
        {
            theLocations.add(loc);
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

        return occupantMap.get(loc);
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
        
        //Add the object to the grid.
        return occupantMap.put(loc, obj);
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

        // if the location is empty, return null
        if (obj == null)
        {
            return null;
        }

        return occupantMap.remove(loc);
    }
}


