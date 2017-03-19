import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

/**
Prompt: The minimum number of cubes to cover every visible face on a cuboid
measuring 3 x 2 x 1 is twenty-two.

If we then add a second layer to this solid it would require forty-six cubes
to cover every visible face, the third layer would require seventy-eight
cubes, and the fourth layer would require one-hundred and eighteen cubes to
cover every visible face.

However, the first layer on a cuboid measuring 5 x 1 x 1 also requires
twenty-two cubes; similarly the first layer on cuboids measuring 5 x 3 x 1,
7 x 2 x 1, and 11 x 1 x 1 all contain forty-six cubes.

We shall define C(n) to represent the number of cuboids that contain n
cubes in one of its layers. So C(22) = 2, C(46) = 4, C(78) = 5, and
C(118) = 8.

It turns out that 154 is the least value of n for which C(n) = 10.

Find the least value of n for which C(n) = 1000.
*/
public class OneTwentySix {
    private static int[] cubeCount;
    private static int limit;
    private static final int TARGET = 1000;

    /**
    This class implements Nicholas Zufelt's solution to Project Euler problem
    126.  It takes one command-line argument, namely the limit to attempt to
    find the solution.  If the input is too low, it will give an error.
    Otherwise, it prints the solution to standard out.
    */
    public static void main(String[] args) {
        try {
            limit = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            System.err.println("Argument" + args[0] + " must be an integer.");
            System.exit(1);
        }
        long timeStart = System.currentTimeMillis();
        cubeCount = new int[limit + 1];
        /* Each outer loop is terminated by one way a cuboid could be
        too big in its first layer.  They are arranged to avoid
        overcounting. */
        for (int len = 1;
                countLayer(len, len, len, 1) <= limit;
                len++) {
            // Cuboid is a cube (length == width == height)
            for (int wid = len;
                    countLayer(len, wid, len, 1) <= limit;
                    wid++) {
                // Cuboid is a square rectangular prism (length == height)
                for (int hei = wid;
                        countLayer(len, wid, hei, 1) <= limit;
                        hei++) {
                    // Cuboid is a generic rectangular prism
                    for (int layer = 1;
                            countLayer(len, wid, hei, layer) <= limit;
                            layer++) {
                        try {
                            cubeCount[countLayer(len, wid, hei, layer)]++;
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            System.out.println("An error occured, probably the"
                                    + " limit was too large.");
                            System.out.println(exception);
                            System.exit(1);
                        }
                    }
                }
            }
        }
        long time = System.currentTimeMillis() - timeStart;
        int smallestInd = smallest();
        if (smallestInd == -1) {
            System.out.println("The limit was set too low. Try again with"
                    + " a larger limit.");
        } else {
            System.out.println(
                    String.format("The smallest n with C(n) = %d is %d. ",
                    TARGET,smallestInd));
            System.out.println("Solution took " + time + " milliseconds");
        }
    }

    private static int smallest() {
        for (int i = 0; i < limit; i++) {
            if (cubeCount[i] == TARGET) {
                return i;
            }
        }
        return -1;
    }

    /* Like most project euler problems, there is a significantly faster way
    to compute the number of of cubes in layer `n` of a cuboid with dimensions
    `l x w x h`.  In fact, it is not even recurrent (i.e. it doesn't require
    storage of previous layer size/information).  See implementation for
    more explanation.
    */
    private static int countLayer(int length, int width,
            int height, int layerInd) {
        /* This counts the cubes that eminate straight out from a cuboid's
        face.  We call these "face cubes" below */
        int layer = 2 * (length * width + width * height + length * height );

        /* This counts the cubes that border a face cube.  We call these
        "border cubes" below */
        layer += 4 * (layerInd - 1) * (length + width + height);

        /* This counts the cubes that border a previous layer's border cubes,
        but are not themselves face or border cubes.  One could call them
        "corner cubes", as they fill in the corners of each layer. A careful
        picture demonstrates that there are 8 sets of corner cubes, and each
        set is the (n-2)^nd triangle number.
        */
        if (layerInd > 2) {
            layer += 4 * (layerInd - 1) * (layerInd - 2);
        }
        return layer;
    }
}
