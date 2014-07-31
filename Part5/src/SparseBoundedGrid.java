import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A SparseBoundedGrid is a rectangular grid with a finite
 * number of rows and columns and a sparse array implementation.
 * This is the hand written linked list version.
 * 
 * @author joyeecheung
 *
 * @param <E>
 *            Type of occupants in the grids.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    // the array storing the occupants
    private SparseGridNode[] occupantArray;
    // size of the grid
    private int cols;
    private int rows;

    /**
     * Constructs an empty sparse bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
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
        occupantArray = new SparseGridNode[rows];
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
            SparseGridNode p = occupantArray[r];
            while (p != null)
            {
                Location loc = new Location(r, p.getCol());
                theLocations.add(loc);
                p = p.getNext();
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

        SparseGridNode p = occupantArray[loc.getRow()];

        // find the object in the linked list
        while (p != null)
        {
            if (loc.getCol() == p.getCol())
            {
                return (E) p.getOccupant();
            }
            p = p.getNext();
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

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        SparseGridNode p = occupantArray[loc.getRow()];
        occupantArray[loc.getRow()] = new SparseGridNode(obj,
                loc.getCol(), p);

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

        // Remove the object from the grid.
        SparseGridNode p = occupantArray[loc.getRow()];

        // assert: p != null
        // The head of the linked list is the object
        if (p.getCol() == loc.getCol())
        {
            // move the next one ahead
            occupantArray[loc.getRow()] = p.getNext();
        }
        else
        {
            // p marks the previous node of the node being checked
            // q is the node being checked
            SparseGridNode q = p.getNext();
            while (q != null && q.getCol() != loc.getCol())
            {
                p = p.getNext();
                q = q.getNext();
            }

            // remove the found occupant
            if (q != null)
            {
                // let the previous node point to the next node
                p.setNext(q.getNext());
            }
        }

        return obj;
    }
}
