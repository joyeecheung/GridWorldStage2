package info.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * A Jumper is an actor that will jump over Rocks and Flowers
 * and turn.
 */
public class Jumper extends Actor
{

    /**
     * Constructs a yellow Jumper.
     */
    public Jumper()
    {
        setColor(Color.PINK);
    }

    /**
     * Constructs a Jumper with given color.
     */
    public Jumper(Color c)
    {
        setColor(c);
    }

    /**
     * Jump if it can.
     */
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

        // already out of the grids
        if (grid == null)
        {
            return;
        }

        Location current = getLocation();
        Location next = current.getAdjacentLocation(getDirection());
        Location dest = next.getAdjacentLocation(getDirection());

        // the destination is valid
        if (grid.isValid(dest))
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
     * Tests whether this Jumper can move forward into a location two grids
     * ahead
     * that is empty or contain a Rock or a Flower.
     * 
     * @return true if this Jumper can move.
     */
    public boolean canJump()
    {
        Grid<Actor> grid = getGrid();

        // already out of the grids
        if (grid == null)
        {
            return false;
        }

        Location current = getLocation();
        Location next = current.getAdjacentLocation(getDirection());

        // can't move if the next grid is out of bounds
        if (!grid.isValid(next))
        {
            return false;
        }

        Actor neighbor = grid.get(next);
        // if the next grid isn't empty or doesn't contains Flower or Rock
        if (!(neighbor == null || neighbor instanceof Flower
                || neighbor instanceof Rock))
        {
            return false;
        }

        Location dest = next.getAdjacentLocation(getDirection());

        // can't move if the destination is out of bounds
        if (!grid.isValid(dest))
        {
            return false;
        }

        // can only land on a grid that is empty or contains Flower.
        neighbor = grid.get(dest);
        return (neighbor == null || neighbor instanceof Flower);
    }
}