import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A Jumper is an actor that will jump over Rocks and Flowers
 * and turn.
 *
 * @author joyeecheung
 */
public class Jumper extends Actor
{
    private static final Color DEFAULT_COLOR = Color.YELLOW;

    /**
     * Constructs a yellow Jumper.
     */
    public Jumper()
    {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Constructs a Jumper with given color.
     */
    public Jumper(Color color)
    {
        setColor(color);
    }

    /**
     * Jump if it can.
     */
    @Override
    public void act()
    {
        if (canJump())
        {
            jump();
        }
        else
        {
            turn();
        }
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     * This method is copied from the Bug class in sample code.
     */
    public void turn()
    {
        setDirection(this.getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Move forward two cells in each move, jumping over rocks and flowers.
     * If it jumps out of the grids, it will be removed.
     */
    public void jump()
    {
        Grid<Actor> grid = getGrid();

        // not in any grid
        if (grid == null)
        {
            return;
        }

        Location next = getLocation().getAdjacentLocation(getDirection());
        Location dest = next.getAdjacentLocation(getDirection());

        // the next location and the destination is valid
        if (grid.isValid(next) && grid.isValid(dest))
        {
            moveTo(dest);
        }
        // out of the grids
        else
        {
            removeSelfFromGrid();
        }
    }

    /**
     * Check if this Jumper can move forward into a location two grids
     * ahead which is empty or contain a Rock or a Flower. The location
     * immediately ahead must also be empty or contains Rock or Flower.
     *
     * @return true if it can move.
     */
    public boolean canJump()
    {
        Grid<Actor> grid = getGrid();

        // not in any grid
        if (grid == null)
        {
            return false;
        }

        int direction = getDirection();
        Location next = getLocation().getAdjacentLocation(direction);
        Location dest = next.getAdjacentLocation(direction);

        // can't move if the next location
        // or the destination is out of bounds
        if (!grid.isValid(next) || !grid.isValid(dest))
        {
            return false;
        }

        // can only land on a grid that is empty or contains Flower.
        Actor neighbor = grid.get(dest);
        return (neighbor == null || neighbor instanceof Flower);
    }
}
