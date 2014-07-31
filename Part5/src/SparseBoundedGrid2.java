import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A SparseBoundedGrid is a rectangular grid with a finite
 * number of rows and columns and a sparse array implementation. 
 * This is the LinkedList version.
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
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
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
        for(int i = 0; i < rows; i++)
        {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
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

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            LinkedList<OccupantInCol> row = occupantArray.get(r);
            if (row != null)
            {
                for (OccupantInCol occ: row)
                {
                    Location loc = new Location(r, occ.getCol());
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        if (row != null)
        {
            for (OccupantInCol occ: row)
            {
                if (loc.getCol() == occ.getCol())
                {
                    return (E) occ.getOccupant();
                }
            }
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
        {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
 
        if (obj == null)
        {
            throw new NullPointerException("obj == null");
        }

        //Add the object to the grid.
        E oldOccupant = remove(loc);
        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        row.add(new OccupantInCol(obj,loc.getCol()));
        return oldOccupant;
    }

    public E remove(Location loc)
    {
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

        // assert: row != null
       Iterator<OccupantInCol> it = row.iterator();
       while(it.hasNext())
       {
           if(it.next().getCol() == loc.getCol())
           {
               it.remove();
               break;
           }
       }

        return obj;
    }
}


