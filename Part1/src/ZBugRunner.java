import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.ZBug;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. 
 */
public class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new ZBug(3));
        world.show();
    }
}
