import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains RockHounds.
 *
 * @author joyeecheung
 */
public class RockHoundRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Rock());
        world.add(new Rock());
        world.add(new Rock(Color.BLUE));
        world.add(new Rock(Color.PINK));
        world.add(new Rock(Color.RED));
        world.add(new Rock(Color.YELLOW));
        world.add(new RockHound());
        world.add(new RockHound());
        world.show();
    }
}
