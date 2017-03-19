import java.lang.Math;

/** The implementation of a Node in a binary triangle (i.e. a binary tree
with node.left.right == node.right.left for all nodes), for Nicholas
Zufelt's solution to project euler problem 67.
*/
public class Node {
    private int value;
    private int largestPath;
    public Node left;
    public Node right;

    /** Built a new Node object.  Called as the child of a previous node, or
    defined as root.  Must be manually set to have the triangle property (see
    class Javadoc).

    @param newValue the value of the node, not the value of the largest path
    */
    public Node(int newValue) {
        value = newValue;
        largestPath = 0;
    }

    /** Attempt to set the largest path to this node.  This will only set
    `largestPath` if `abovePath` (i.e. one parent's `largestPath`) is
    larger than `largestPath`.

    @param abovePath the largest path to the parent calling this Node
    */
    public void setLargestPath(int abovePath) {
        largestPath = Math.max(largestPath, value + abovePath);
    }

    public int getLargestPath() {
        return largestPath;
    }
}
