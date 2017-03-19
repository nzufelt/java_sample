import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
Prompt: By starting at the top of the triangle below and moving to adjacent
numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in triangle.txt, a 15K text
file containing a triangle with one-hundred rows.
*/
public class SixtySeven {
    /* Build the triangle by reading in the file line by line and
    constructing the triangle row by row.  Most importantly, note that
    we immediately push the current largest path from parent to child in
    construction.  This is very important, as traversing a tree of this
    depth would take far too long once the pointers to intermediate nodes
    are lost.  Return the final row of the triangle so that traversing for
    the true maxPath is fast.
    */
    private static Node[] buildTriangle() {
        // Read in file as List<String>
        String filename = "triangle.txt";
        List<String> lines = null; // avoids compiler error
        try {
            lines = Files.readAllLines(Paths.get(filename),
                                       StandardCharsets.UTF_8);
        } catch (IOException exception) {
            System.err.println("Caught IOException: " + exception.getMessage());
        }

        // Build the triangle
        lines.remove(0); // to make finding parents work, manually add root
        Node[] parents = new Node[1];
        parents[0] = new Node(59);
        parents[0].setLargestPath(0);
        Node[] children;
        for (String line : lines) {
            // break each line into an array of ints
            String[] newStrValues = line.split("\\s+");
            int[] newValues = new int[newStrValues.length];
            for (int i = 0; i < newStrValues.length; i++) {
                newValues[i] = Integer.parseInt(newStrValues[i]);
            }

            // Now create the array of children
            children = new Node[parents.length + 1];
            /* create the two outsidemost children and push the largestPath
            to them */
            children[0] = parents[0].left = new Node(newValues[0]);
            children[0].setLargestPath(parents[0].getLargestPath());
            children[children.length - 1] = parents[parents.length - 1].right
                    = new Node(newValues[newValues.length - 1]);
            children[children.length - 1].setLargestPath(
                    parents[parents.length - 1].getLargestPath());
            /* create the inside children, zipping the triangle up as we go
            (that is, connecting interior Nodes to not have a tree) and push
            the largestPath to them */
            for (int i = 1; i < parents.length; i++) {
                children[i] = parents[i].left = parents[i - 1].right
                        = new Node(newValues[i]);
                children[i].setLargestPath(parents[i - 1].getLargestPath());
                children[i].setLargestPath(parents[i].getLargestPath());
            }
            parents = children;
        }
        return parents;
    }

    /** This class implements Nicholas Zufelt's solution to Project Euler problem
    67.  It takes no command-line arguments, and prints the solution to
    standard out. */
    public static void main(String[] args) {
        // Build the triangle
        Node[] lastRow = buildTriangle();

        int maxPath = 0;
        for (int i = 0; i < lastRow.length; i++) {
            maxPath = Math.max(maxPath,lastRow[i].getLargestPath());
        }

        // Get the largest path
        System.out.println("The largest path has a value of " + maxPath);
    }
}
