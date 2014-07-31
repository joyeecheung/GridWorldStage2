import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains BlusterCritters.
 * 
 * @author joyeecheung
 */
public class CritterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Rock(Color.PINK));
        world.add(new Rock(Color.BLACK));
        world.add(new Rock(Color.WHITE));
        world.add(new Flower(Color.PINK));
        world.add(new Flower(Color.GREEN));
        world.add(new Critter());
        world.add(new BlusterCritter(1));
        world.add(new ChameleonCritter());
        world.add(new ChameleonKid());
        world.add(new CrabCritter());
        world.add(new KingCrab());
        world.add(new QuickCrab());
        world.show();
    }
}