import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

/**
 * This class runs a world that contains a circle bug,
 * added at random locations.
 *
 * @author joyeecheung
 */
public class TestRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.setGrid(new UnboundedGrid());

        world.add(new Location(1, 1), new ChameleonCritter());
        world.add(new Location(1, 15), new ChameleonKid());
        world.add(new Location(15, 1), new BlusterCritter(1));
        world.add(new Location(15, 15), new RockHound());
        world.add(new Location(30, 1), new RockHound());
        world.add(new Location(30, 2), new Rock());
        world.add(new Location(30, 3), new Flower());
        world.add(new Location(30, 15), new KingCrab());
        world.add(new Location(29, 15), new Bug());
        world.add(new Location(1, 30), new QuickCrab());
        world.add(new Location(0, 30), new Bug());

        world.show();
    }
}
