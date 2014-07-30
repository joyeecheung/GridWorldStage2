import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.CircleBug;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. 
 */
public class CircleBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new CircleBug(2));
        world.add(new Rock());
        world.show();
    }
}
