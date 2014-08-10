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
public class SparseBoundedGrid1<E> extends AbstractBoundedGrid<E>
{
    // the array storing the occupants
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid1(int rows, int cols)
    {
        super(rows, cols);

        // initialize the occupant array with LinkedLists
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>();
        for (int i = 0; i < rows; i++)
        {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
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
        for (int r = 0; r < rows; r++)
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
        checkLocation(loc);

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
        checkLocation(loc);

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
        checkLocation(loc);

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
