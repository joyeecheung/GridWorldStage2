import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A DarkCritter will turn every actor around black.
 * 
 * @author joyeecheung
 */
public class DarkCritter extends Critter
{
    private static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * Constructs a black critter.
     */
    public DarkCritter()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Processes the elements of actors, turn them black.
     *
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            a.setColor(DEFAULT_COLOR);
        }
    }
}
