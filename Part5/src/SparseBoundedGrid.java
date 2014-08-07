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
        for (int i = 0; i < getNumRows(); i++)
        {
            SparseGridNode node = occupantArray[i];
            while (node != null)
            {
                Location loc = new Location(i, node.getCol());
                theLocations.add(loc);
                node = node.getNext();
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

        SparseGridNode node = occupantArray[loc.getRow()];

        // find the object in the linked list
        while (node != null)
        {
            if (loc.getCol() == node.getCol())
            {
                return (E) node.getOccupant();
            }
            node = node.getNext();
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
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        occupantArray[row] = new SparseGridNode(obj, col, node);

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
        int targetRow = loc.getRow();
        int targetCol = loc.getCol();
        SparseGridNode target = occupantArray[targetRow];

        // assert: target != null
        // The head of the linked list is the object
        if (target.getCol() == targetCol)
        {
            // move the next one ahead
            occupantArray[targetRow] = target.getNext();
        }
        else
        {
            // target marks the previous node of the node being checked
            // cur is the node being checked
            SparseGridNode cur = target.getNext();
            while (cur != null && cur.getCol() != targetCol)
            {
                target = target.getNext();
                cur = cur.getNext();
            }

            // remove the found occupant
            if (cur != null)
            {
                // let the previous node point to the next node
                target.setNext(cur.getNext());
            }
        }

        return obj;
    }
}
