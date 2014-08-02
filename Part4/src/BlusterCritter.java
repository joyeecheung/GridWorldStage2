import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A BlusterCritter gets brighter if there are fewer than c critters,
 * darker if there are c or more than c critters.
 * 
 * @author joyeecheung
 */
public class BlusterCritter extends Critter
{
    // it will get brighter if there are fewer than c critters,
    // darker if there are c or more than c critters.
    private final int critters;
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    /**
     * Constructs a BlusterCritter with c as color changing factor.
     * 
     * @param c
     *            the given number of critters.
     */
    public BlusterCritter(int critters)
    {
        this.critters = critters;
        setColor(DEFAULT_COLOR);
    }

    /**
     * Gets the actors for processing. Looks at all of the neighbors
     * within two steps of its current location.
     * 
     * @return a list of actors that are neighbors of this critter
     */
    @Override
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        Grid<Actor> grid = getGrid();
        if (grid == null)
        {
            return actors;
        }

        // get the bounds of the area to check
        Location current = getLocation();
        int left = current.getRow() - 2;
        int right = current.getRow() + 2;
        int top = current.getCol() - 2;
        int bottom = current.getCol() + 2;

        // check the neighboring area for actors
        for (int i = left; i <= right; i++)
        {
            for (int j = top; j <= bottom; j++)
            {
                Location checkedLoc = new Location(i, j);
                if (grid.isValid(checkedLoc))
                {
                    Actor neighbor = grid.get(checkedLoc);
                    if (neighbor != null && neighbor != this)
                    {
                        actors.add(neighbor);
                    }
                }
            }
        }

        return actors;
    }

    /**
     * A BlusterCritter gets brighter if there are fewer than c critters,
     * darker if there are c or more than c critters
     * 
     * @param actors
     *            the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors)
    {
        // count how many actors are in the area within two steps
        int count = 0;
        for (Actor neighbor : actors)
        {
            if (neighbor instanceof Critter)
            {
                count++;
            }
        }

        changeColor(count < this.critters);
    }

    /**
     * Change the color of the BlusterCritter to brighter or darker.
     * 
     * @param brighter
     *            if the BlusterCritter should be brighter.
     */
    private void changeColor(boolean brighter)
    {
        int delta = brighter ? 10 : -10;

        Color color = getColor();
        int red = channelFilter(color.getRed(), delta);
        int blue = channelFilter(color.getBlue(), delta);
        int green = channelFilter(color.getGreen(), delta);

        setColor(new Color(red, green, blue));
        return;
    }

    /**
     * Filter a channel and control the value between 0 ~ 255
     * 
     * @param channel
     *            value of the channel
     * @param delta
     *            delta of the value
     */
    private int channelFilter(int channel, int delta)
    {
        int result = channel + delta;
        result = result >= 255 ? 255 : result;
        result = result < 0 ? 0 : result;
        return result;
    }
}
