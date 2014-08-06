import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a jumper, a bug, a flower
 * and a rock.
 *
 * @author joyeecheung
 */
public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

        world.add(new Location(1, 1), new Bug());
        world.add(new Location(2, 2), new Rock());
        world.add(new Location(1, 2), new Flower());
        world.add(new Location(3, 2), new Jumper());
        world.show();
    }
}
