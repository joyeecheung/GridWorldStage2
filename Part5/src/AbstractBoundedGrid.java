import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

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
public abstract class AbstractBoundedGrid<E> extends AbstractGrid<E>
{
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
    public AbstractBoundedGrid(int rows, int cols)
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
     * Check if a location is valid.
     *
     * @param loc
     *            the location to check.
     *
     */
    protected void checkLocation(Location loc)
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
    }
}
