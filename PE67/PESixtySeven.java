/** By starting at the top of the triangle below and moving to adjacent
numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in triangle.txt, a 15K text
file containing a triangle with one-hundred rows.
*/

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.IOException;

public class PESixtySeven {
  /* Build the triangle by reading in the file line by line and
  constructing the triangle row by row.  Most importantly, note that
  we immediately push the current largest path from parent to child in
  construction.  This is very important, as traversing a tree of this
  depth would take far too long once the pointers to intermediate nodes
  are lost.  Return the final row of the triangle so that traversing for
  the true max_path is fast.
  */
  private static Node[] build_triangle() {
    // Read in file as List<String>
    String filename = "triangle.txt";
    List<String> lines = null; // avoids compiler error
    try {
      lines = Files.readAllLines(Paths.get(filename),
                                 StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println("Caught IOException: " + e.getMessage());
    }

    // Build the triangle
    lines.remove(0); // to make finding parents work, manually add root
    Node[] parents = new Node[1];
    parents[0] = new Node(59);
    parents[0].set_largest_path(0);
    Node[] children;
    for (String line : lines) {
      // break each line into an array of ints
      String[] new_str_values = line.split("\\s+");
      int[] new_values = new int[new_str_values.length];
      for(int i = 0; i < new_str_values.length; i++) {
        new_values[i] = Integer.parseInt(new_str_values[i]);
      }

      // Now create the array of children
      children = new Node[parents.length+1];
      /* create the two outsidemost children and push the largest_path
      to them
      */
      children[0] = parents[0].lChild = new Node(new_values[0]);
      children[0].set_largest_path(parents[0].get_largest_path());
      children[children.length-1] = parents[parents.length-1].rChild
          = new Node(new_values[new_values.length-1]);
      children[children.length-1].set_largest_path(
          parents[parents.length-1].get_largest_path());
      /* create the inside children, zipping the triangle up as we go
      (that is, connecting interior Nodes to not have a tree) and push
      the largest_path to them
      */
      for (int i=1; i < parents.length; i++) {
        children[i] = parents[i].lChild = parents[i-1].rChild
            = new Node(new_values[i]);
        children[i].set_largest_path(parents[i-1].get_largest_path());
        children[i].set_largest_path(parents[i].get_largest_path());
      }
      parents = children;
    }
    return parents;
  }

  public static void main(String[] args) {
    // Build the triangle
    Node[] last_row = build_triangle();

    int max_path = 0;
    for (int i=0; i < last_row.length; i++){
      max_path = Math.max(max_path,last_row[i].get_largest_path());
    }

    // Get the largest path
    System.out.println("The largest path has a value of " + max_path);
  }
}
