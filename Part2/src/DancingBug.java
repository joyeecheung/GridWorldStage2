import info.gridworld.actor.Bug;

/**
 * The bug that makes different turns before each move.
 *
 * @author joyeecheung
 */
public class DancingBug extends Bug
{
    // step in the current loop
    private int steps;
    // number of turns for each step
    private int[] turns;

    /**
     * Constructs a dancing bug that makes different turns before each move.
     *
     * @param turns
     *            number of turns for each step.
     */
    public DancingBug(int[] turns)
    {
        steps = 0;
        this.turns = turns.clone();
    }

    /**
     * Turns given number of times.
     *
     * @param times
     *            number of turns for this move.
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
    @Override
    public void act()
    {
        if (canMove())
        {
            // turn and set next step
            turn(turns[steps]);
            steps = (steps + 1) % turns.length;
            super.act();
        }
    }
}
