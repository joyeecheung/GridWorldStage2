import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.SpiralBug;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. 
 */
public class SpiralBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(4, 4), new SpiralBug(2));
        world.show();
    }
}
