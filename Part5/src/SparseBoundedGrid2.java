import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A SparseBoundedGrid is a rectangular grid with a finite
 * number of rows and columns and a sparse array implementation.
 * This is the LinkedList version.
 * 
 * @author joyeecheung
 *
 * @param <E>
 *            Type of occupants in the grids.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    // the array storing the occupants
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;
    private int cols;
    private int rows;

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

        // initialize the occupant array with LinkedLists
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>();
        for (int i = 0; i < rows; i++)
        {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
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

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            LinkedList<OccupantInCol> row = occupantArray.get(r);
            if (row != null)
            {
                for (OccupantInCol occ : row)
                {
                    theLocations.add(new Location(r, occ.getCol()));
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

        int targetCol = loc.getCol();

        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        if (row != null)
        {
            for (OccupantInCol occ : row)
            {
                if (occ.getCol() == targetCol)
                {
                    return (E) occ.getOccupant();
                }
            }
        }
        return null;
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
        E oldOccupant = remove(loc);
        int targetRow = loc.getRow();
        int targetCol = loc.getCol();
        LinkedList<OccupantInCol> row = occupantArray.get(targetRow);
        row.add(new OccupantInCol(obj, targetCol));
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

        // if the location is empty, return null
        if (obj == null)
        {
            return null;
        }

        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());

        int targetCol = loc.getCol();

        // assert: row != null
        Iterator<OccupantInCol> it = row.iterator();
        while (it.hasNext())
        {
            if (it.next().getCol() == targetCol)
            {
                it.remove();
                break;
            }
        }

        return obj;
    }
}
