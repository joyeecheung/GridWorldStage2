import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A ChameleonKid changes its color to the color of one of the actors
 * immediately in front or behind. If there is no
 * actor in either of these locations, then the ChameleonKid darkens.
 * 
 * @author joyeecheung
 */
public class ChameleonKid extends ChameleonCritter
{
    /**
     * Gets the actors immediately in front or behind.
     * 
     * @return a list of actors occupying these locations
     */
    @Override
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };

        // get all actors in given directions
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
            {
                actors.add(a);
            }
        }

        return actors;
    }

    /**
     * This method is copied from the sample code of CrabCritter.
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * 
     * @param directions
     *            an array of directions (which are relative to the
     *            current direction)
     * @return a set of valid locations that are neighbors of the current
     *         location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> grid = getGrid();
        Location loc = getLocation();

        // get all valid neighboring locations in given directions
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (grid.isValid(neighborLoc))
            {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}
