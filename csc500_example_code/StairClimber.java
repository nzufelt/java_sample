/**
 * Recursively find all possible ways of "climbing the stairs" given
 * two inputs: numStairs and skip
 *
 * Author: Nicholas Zufelt
 * Date: February 14, 2016
 * Course: AP Computer Science A
 */

import java.util.Arrays;

public class StairClimber {
    /* Determine all the possible ways to climb a staircase of `numStairs`
     * stairs, given that the options are to take 1 step, or to skip `skip`
     * many steps.  Return the result as a (ragged) 2D int array.  For example,
     * a call to stairClimber(3, 2) should return:
     * [[1,1,1], [2,1], [1,2]]
     * corresponding to the three ways of climbing 3 stairs either by taking
     * single steps or skipping by 2 a single time.  This is a recursive
     * method.
     */
    public static int[][] stairClimber(int numStairs, int skip) {
        // base cases
        if (numStairs == 1) {
            return new int[][]{{1}};
        } else if (numStairs == 0) {
            return new int[][]{{}};
        } else if (numStairs < 0) {
            return new int[0][];
        } else if (numStairs < skip) {
            int[] row = new int[numStairs];
            for (int i = 0; i < row.length; i++) {row[i] = 1;}
            return new int[][]{row};
        } // end of base cases

        // Build both the tails and the main 2D array:
        int[][] stepTail = stairClimber(numStairs - 1, skip);
        int[][] skipTail = stairClimber(numStairs - skip, skip);

        int[][] stairs = new int[stepTail.length + skipTail.length][];
        int index = 0;

        // Populate stairs:
        for (int[] row : stepTail) {
            stairs[index++] = extend(row, 1);
        }
        for (int[] row : skipTail) {
            stairs[index++] = extend(row, skip);
        }

        return stairs;
    }

    // Return the array [firstEntry, <entries of row>]
    private static int[] extend(int[] row, int firstEntry) {
        int[] extended = new int[row.length + 1];
        extended[0] = firstEntry;
        for (int i = 0; i < row.length; i++) {
            extended[i+1] = row[i];
        }
        return extended;
    }

    public static void main(String[] args) {
        System.out.println("Testing stairClimber(3,2)");
        System.out.println(Arrays.deepToString(stairClimber(3,2)));

        System.out.println("\nTesting stairClimber(5,2)");
        System.out.println(Arrays.deepToString(stairClimber(5,2)));

        System.out.println("\nTesting stairClimber(13,4)");
        System.out.println(Arrays.deepToString(stairClimber(13,4)));
    }
}
