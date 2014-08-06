import info.gridworld.actor.Bug;

/**
 * The bug moves with a octagonal path.
 *
 * @author joyeecheung
 */
public class CircleBug extends Bug
{
    // steps in current part
    private int steps;
    // side length of the octagon
    private int sideLength;

    /**
     * Constructs a circle bug that traces a octagon of a given side length
     *
     * @param length
     *            the side length
     */
    public CircleBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location.
     */
    @Override
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            steps = 0;
        }
    }
}
