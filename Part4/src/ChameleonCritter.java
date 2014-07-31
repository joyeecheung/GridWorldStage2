import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * 
 * @author joyeecheung
 */
public class ChameleonCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.1;
    private static final Color DEFAULT_COLOR = Color.CYAN;

    /**
     * Constructs a red crab critter.
     */
    public ChameleonCritter()
    {
        setColor(DEFAULT_COLOR);
    }
    
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, it will darken.
     */
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();

        // if there are no neighbors, the critter darkens.
        if (n == 0)
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

            setColor(new Color(red, green, blue));
            return;
        }

        // randomly select a neighbor and put on its color
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }

    /**
     * Turns towards the new location as it moves.
     * 
     * @param loc
     *            the new location.
     */
    @Override
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
