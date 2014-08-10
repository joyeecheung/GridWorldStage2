import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.util.ArrayList;

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
public class SparseBoundedGrid3<E> extends AbstractBoundedGrid<E>
{
    private UnboundedGrid<E> innerGrid;

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
        super(rows, cols);
        this.innerGrid = new UnboundedGrid<E>();
    }

    @Override
    public E get(Location loc)
    {
        return innerGrid.get(loc);
    }

    @Override
    public ArrayList<Location> getOccupiedLocations()
    {
        return innerGrid.getOccupiedLocations();
    }

    @Override
    public E put(Location loc, E obj)
    {
        return innerGrid.put(loc, obj);
    }

    @Override
    public E remove(Location loc)
    {
        return innerGrid.remove(loc);
    }
}
