import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * The bug that moves in a "Z" pattern, starting in the top left corner.
 * 
 * @author joyeecheung
 */
public class ZBug extends Bug
{
    // steps finished in the current part
    private int steps;
    // length of each part
    private int sideLength;
    // indicates which part the bug is in
    private int part;

    /**
     * Constructs a z bug that moves in a "Z" pattern.
     * 
     * @param length
     *            the side length
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
        // end of the first part --
        else if (part == 0)
        {
            setDirection(Location.SOUTHWEST);
            part++;
            steps = 0;
        }
        // end of the second part /
        else if (part == 1)
        {
            setDirection(Location.EAST);
            part++;
            steps = 0;
        }
    }
}
