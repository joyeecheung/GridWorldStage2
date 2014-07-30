import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A RockHound gets the actors to be processed in the
 * same way as a Critter. It removes any rocks in that list from the grid.
 */
public class RockHound extends Critter
{
    /**
     * Removes any rocks in neighborhood.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a: actors)
        {
            if (a instanceof Rock)
            {
                a.removeSelfFromGrid();
            }
        }
    }
}
