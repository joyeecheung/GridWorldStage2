import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a zbug,
 * added at random locations.
 *
 * @author joyeecheung
 */
public class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug john = new ZBug(3);
        world.add(new Location(3, 3), john);
        world.add(new Location(3, 4), new Rock());
        world.show();
    }
}
