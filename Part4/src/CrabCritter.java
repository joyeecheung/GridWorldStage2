import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats
 * and moves.
 * 
 * @author joyeecheung
 */
public class CrabCritter extends Critter
{
    private static final Color DEFAULT_COLOR = Color.RED;

    /**
     * Constructs a red crab critter.
     */
    public CrabCritter()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * A crab gets the actors in the three locations immediately in front, to
     * its front-right and to its front-left
     * 
     * @return a list of actors occupying these locations
     */
    @Override
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };

        // get neighbors in given directions.
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
     * @return list of empty locations immediately to the right and to the left
     */
    @Override
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
        { Location.LEFT, Location.RIGHT };

        // get available neighboring locations within given directions
        for (Location loc : getLocationsInDirections(dirs))
        {
            if (getGrid().get(loc) == null)
            {
                locs.add(loc);
            }
        }
        return locs;
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    @Override
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
            {
                angle = Location.LEFT;
            }
            else
            {
                angle = Location.RIGHT;
            }
            setDirection(getDirection() + angle);
        }
        else
        {
            super.makeMove(loc);
        }
    }

    /**
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

        // get locations within given directions
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
