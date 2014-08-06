import info.gridworld.actor.Bug;

/**
 * The bug that drops flowers in a spiral pattern.
 *
 * @author joyeecheung
 */
public class SpiralBug extends Bug
{
    // steps in current part
    private int steps;
    // side length of current part
    private int sideLength;

    /**
     * Constructs a spiral bug that drops flowers in a spiral pattern.
     *
     * @param length
     *            the side length
     */
    public SpiralBug(int length)
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
        if (canMove() && steps < sideLength)
        {
            move();
            steps++;
        }
        else
        {
            turn();
            turn();
            // increase the side length after turning to get sprial pattern.
            sideLength++;
            // start over using the new side length
            steps = 0;

        }
    }
}
