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
public class SparseBoundedGrid<E> extends AbstractBoundedGrid<E>
{
    // the array storing the occupants
    private SparseGridNode[] occupantArray;

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
        super(rows, cols);
        occupantArray = new SparseGridNode[rows];
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
        int rows = getNumRows();

        // Look at all grid locations.
        for (int i = 0; i < rows; i++)
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
        checkLocation(loc);
        
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
        checkLocation(loc);

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
        checkLocation(loc);

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
