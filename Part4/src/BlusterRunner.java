import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains BlusterCritters.
 */
public class BlusterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Critter());
        world.add(new Critter());
        world.add(new Critter());
        world.add(new Critter());
        world.add(new Critter());
        world.add(new BlusterCritter(1));
        world.show();
    }
}
