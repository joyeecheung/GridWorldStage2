import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.CircleBug;

/**
 * This class runs a world that contains a circle bug,
 * added at random locations.
 */
public class CircleBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new CircleBug(2));
        world.show();
    }
}
