import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.ZBug;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a zbug,
 * added at random locations.
 */
public class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug john = new ZBug(3);
        world.add(new Location(3, 3), john);
        world.show();
    }
}
