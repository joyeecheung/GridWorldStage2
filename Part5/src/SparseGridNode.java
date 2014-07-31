/**
 * Node for hand written linked list version of SparseBoundedGrid.
 * 
 * @author joyeecheung
 */
public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;

    /**
     * Construct a node with given occupant in the given column,
     * with given next pointer.
     * 
     * @param occupant
     *            occupant in this node.
     * @param col
     *            column index of this node.
     * @param next
     *            the next node of this node.
     */
    public SparseGridNode(Object occupant, int col,
            SparseGridNode next)
    {
        this.occupant = occupant;
        this.col = col;
        this.next = next;
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

    /**
     * Set the next node.
     * 
     * @param next
     *            reference to the next node of thie node.
     */
    public void setNext(SparseGridNode next)
    {
        this.next = next;
    }

    /**
     * Get the next node.
     * 
     * @return the reference to next node.
     */
    public SparseGridNode getNext()
    {
        return next;
    }
}
