import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

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
        
        world.add(new Location(1, 1), new ZBug(4));
        world.add(new Location(1, 15), new CircleBug(2));
        world.add(new Location(15, 1), new DancingBug(new int[]{1, 2, 1, 3}));
        world.add(new Location(15, 15), new SpiralBug(1));

        world.show();
    }
}
