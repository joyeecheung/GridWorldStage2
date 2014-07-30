import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A BlusterCritter gets brighter if there are fewer than c critters,
 * darker if there are c or more than c critters
 */
public class BlusterCritter extends Critter
{
    private final int c;

    public BlusterCritter(int c)
    {
       this.c = c; 
    }

    /**
     * Gets the actors for processing. Looks at all of the neighbors
     * within two steps of its current location.
     * @return a list of actors that are neighbors of this critter
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        Grid<Actor> grid = getGrid();
        if (grid == null)
        {
            return actors;
        }

        Location current = getLocation();
        int left = current.getRow() - 2;
        int right = current.getRow() + 2;
        int top = current.getCol() - 2; 
        int bottom = current.getCol() + 2;

        for(int i = left; i <= right; i++)
        {
            for(int j = top; j <= bottom; j++)
            {
                Location checkedLoc = new Location(i, j);
                if(grid.isValid(checkedLoc))
                {
                    Actor a = grid.get(checkedLoc);
                    if(a != null && a != this)
                        actors.add(a);
                }
            }
        }

        return actors;
    }

    /**
     * A BlusterCritter gets brighter if there are fewer than c critters,
     * darker if there are c or more than c critters
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int count = 0;
        for (Actor a: actors)
        {
            if (a instanceof Critter)
            {
                count++;
            }
        }
        
        changeColor(count < this.c);
    }

    /**
     * Change the color of the BlusterCritter to brighter or darker.
     * @param brighter if the BlusterCritter should be brighter.
     */
    private void changeColor(boolean brighter)
    {
        int delta = brighter ? 10 : -10;

        Color c = getColor();
        int red = channelFilter(c.getRed(), delta);
        int blue = channelFilter(c.getBlue(), delta);
        int green = channelFilter(c.getGreen(), delta);

        setColor(new Color(red, green, blue));
        return;
    }

    /**
     * Filter a channel and control the value between 0 ~ 255
     * @param channel value of the channel
     * @param delta delta of the value
     */
    private int channelFilter(int channel, int delta)
    {
        channel = channel + delta;
        channel = channel >= 255 ? 255 : channel;
        channel = channel < 0 ? 0 : channel;
        return channel;
    }
}
