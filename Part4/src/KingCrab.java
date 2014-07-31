import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A KingCrab causes each actor that it processes
 * to move one location further away from the KingCrab.
 * If the actor cannot move away, the KingCrab removes it from the grid.
 * 
 * @author joyeecheung
 */
public class KingCrab extends CrabCritter
{
    /**
     * Check if two location is adjacent (in eight directions).
     * 
     * @param loca
     *            first of the location
     * @param locb
     *            second of the location
     */
    private boolean isAdjacent(Location loca, Location locb)
    {
        int x1 = loca.getRow();
        int y1 = loca.getCol();
        int x2 = locb.getRow();
        int y2 = locb.getCol();
        double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return ((int) Math.floor(dist + 0.5)) <= 1;
    }

    /**
     * Move the actor away if it can.
     * 
     * @param a
     *            the poor actor.
     */
    private boolean moveAway(Actor a)
    {
        if (a instanceof Rock || a instanceof Flower)
        {
            return false;
        }

        // get all locations that a can run away to
        ArrayList<Location> locs =
                getGrid().getEmptyAdjacentLocations(a.getLocation());

        // check if there is a location away from the king crab
        for (Location loc : locs)
        {
            // there is such a location, run away!
            if (!isAdjacent(getLocation(), loc))
            {
                a.moveTo(loc);
                return true;
            }
        }

        // no where to run away to
        return false;
    }

    /**
     * Causes each actor that it processes to move one location further
     * away from the KingCrab. If the actor cannot move away,
     * the KingCrab removes it from the grid.
     */
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!moveAway(a))
            {
                a.removeSelfFromGrid();
            }
        }
    }
}
