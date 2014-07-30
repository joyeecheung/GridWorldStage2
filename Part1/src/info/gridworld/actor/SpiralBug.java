package info.gridworld.actor;

import info.gridworld.actor.Bug;

/**
 * The bug drops flowers in a spiral pattern. 
 */
public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a spiral bug that drops flowers in a spiral pattern.
     * @param length the side length
     */
    public SpiralBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
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
            turn();
            steps = 0;
            // increase the side length after turning to get sprial pattern.
            sideLength++;
        }
    }
}
