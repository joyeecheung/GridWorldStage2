import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A QuickCrab moves to one of the two locations,
 * randomly selected, that are two spaces to its right or left,
 * if that location and the intervening location are both empty.
 * 
 * @author joyeecheung
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of empty locations two spaces to the right and to the left
     */
    @Override
    public ArrayList<Location> getMoveLocations()
    {
        int dir = getDirection();
        int[] dirs = { dir + Location.LEFT, dir + Location.RIGHT };
        ArrayList<Location> locs = getLocationsTwoSpaceAway(dirs);

        if (locs.size() == 0)
        {
            return super.getMoveLocations();
        }

        return locs;
    }

    /**
     * Finds the valid locations of this critter two spaces in different
     * directions.
     * 
     * @param directions
     *            - an array of directions (which are relative to the
     *            current direction)
     * @return a set of valid locations that are neighbors of the current
     *         location in the given directions
     */
    private ArrayList<Location> getLocationsTwoSpaceAway(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> grid = getGrid();
        Location current = getLocation();

        for (int d : directions)
        {
            Location loc = current.getAdjacentLocation(d);

            // if intervening location is valid and empty
            if (grid.isValid(loc) && grid.get(loc) == null)
            {
                Location dest = loc.getAdjacentLocation(d);

                // if the destination is valid and empty
                if (grid.isValid(dest) && grid.get(dest) == null)
                {
                    locs.add(dest);
                }
            }
        }

        return locs;
    }
}
