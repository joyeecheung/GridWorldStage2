package info.gridworld.actor;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * The bug that moves in a "Z" pattern, starting in the top left corner.
 */
public class ZBug extends Bug
{
    private int steps;  // steps finished in the current part
    private int sideLength;  // length of each part
    private int part;  // indicates which part the bug is in

    /**
     * Constructs a spiral bug that moves in a "Z" pattern.
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        part = 0;
        setDirection(Location.EAST);
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        // if the "Z" is not completed
        if (steps < sideLength && part <= 2)
        {
            if (canMove())
            {
                move();
                steps++;
            }
        }
        else if (part == 0)  // end of the first part --
        {
            setDirection(Location.SOUTHWEST); 
            part++;
            steps = 0;
        }
        else if (part == 1)  // end of the second part /
        {
            setDirection(Location.EAST); 
            part++;
            steps = 0;
        }
    }
}
