import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a spiral bug,
 * added a given location.
 */
public class SpiralBugRunner
{
    public static void main(String[] args)
    {
        UnboundedGrid<Actor> grid = new UnboundedGrid<Actor>();
        ActorWorld world = new ActorWorld(grid);
        world.add(new Location(8, 8), new SpiralBug(2));
        world.show();
    }
}
