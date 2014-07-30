package info.gridworld.actor;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * The bug that makes different turns before each move.
 */
public class DancingBug extends Bug
{
    private int steps;  // step in the current loop
    private int[] turns;  // number of turns for each step

    /**
     * Constructs a dancing bug that makes different turns before each move
     * @param turns number of turns for each step.
     */
    public DancingBug(int[] turns)
    {
        steps = 0;
        this.turns = turns.clone();
    }

    /**
     * Turns given number of times.
     * @param times number of turns for this move.
     */
    public void turn(int times)
    {
        for (int i = 0; i < times; i++)
        {
            super.turn();
        }
    }

    /**
     * Moves to the next location.
     */
    public void act()
    {
        if (canMove())
        {
            turn(turns[steps]);
            steps = (steps + 1) % turns.length;
            super.act();
        }
    }
}
