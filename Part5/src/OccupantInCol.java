/**
 * Node for LinkedList version of SparseBoundedGrid.
 * 
 * @author joyeecheung
 */
public class OccupantInCol
{
    private Object occupant;
    private int col;

    /**
     * Construct a node with given occupant in the given column.
     * 
     * @param occupant
     *            occupant in this node.
     * @param col
     *            column index of this node.
     */
    public OccupantInCol(Object occupant, int col)
    {
        this.occupant = occupant;
        this.col = col;
    }

    /**
     * Set the given occupant.
     * 
     * @param occupant
     *            new occupant in this node.
     */
    public void setOccupant(Object occupant)
    {
        this.occupant = occupant;
    }

    /**
     * @return occupant in this node.
     */
    public Object getOccupant()
    {
        return occupant;
    }

    /**
     * @return column index of this node.
     */
    public int getCol()
    {
        return col;
    }
}
