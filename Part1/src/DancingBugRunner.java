import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.DancingBug;

/**
 * This class runs a world that contains a dancing bug,
 * added at random locations.
 */
public class DancingBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] turns = { 1, 2, 1, 2 };
        world.add(new DancingBug(turns));
        world.show();
    }
}