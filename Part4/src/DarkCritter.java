import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A DarkCritter will turn every actor around black,
 * but it can not step on any other actors including flowers
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
     * @param actors
     *            the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            a.setColor(DEFAULT_COLOR);
        }
    }
}
