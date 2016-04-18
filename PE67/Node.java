import java.lang.Math;

public class Node{
  private int value;
  private int largest_path;
  private int row; // for debugging
  public Node lChild;
  public Node rChild;

  public Node(int new_value){
    value = new_value;
    largest_path = 0;
  }

  /* Attempt to set the largest path to this node.  This will only set
  `largest_path` if `above_path` (i.e. one parent's `largest_path`) is
  larger than `largest_path`.
  */
  public void set_largest_path(int above_path) {
    largest_path = Math.max(largest_path, value + above_path);
  }

  public int get_largest_path(){
    return largest_path;
  }
}
