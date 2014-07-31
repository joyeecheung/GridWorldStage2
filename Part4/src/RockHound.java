import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A RockHound gets the actors to be processed in the
 * same way as a Critter. It removes any rocks in that list from the grid.
 * 
 * @author joyeecheung
 */
public class RockHound extends Critter
{
    private static final Color DEFAULT_COLOR = Color.GRAY;

    /**
     * Constructs a red crab critter.
     */
    public RockHound()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Removes any rocks in neighborhood.
     */
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Rock)
            {
                a.removeSelfFromGrid();
            }
        }
    }
}
