/**
Prompt:
Take the number 192 and multiply it by each of 1, 2, and 3:
    192 × 1 = 192
    192 × 2 = 384
    192 × 3 = 576

By concatenating each product we get the 1 to 9 pandigital, 192384576.
We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2,
3, 4, and 5, giving the pandigital, 918273645, which is the
concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be
formed as the concatenated product of an integer with (1,2, ... , n)
where n > 1?
*/

public class ThirtyEight {
    private static Integer[] pandigit = new Integer[9];
    private static int[] directions = {1,1,1,1,1,1,1,1,0};

    /* Implement the Steinhaus–Johnson–Trotter algorithm to set pandigit
    to the next permutation.  This algorithm iterates through all
    permutations of pandigit, without repition.  This helps by only having
    to count 9! options instead of 10^9 options.  This will throw an
    exception if there are no more permutations to iterate through.  Note
    that I could have used the Iterator interface, maybe a future version
    will implement that.
    */
    private static void nextPandigit() {
        /* Step one: Find the largest digit with non-zero direction.
        First, initialize variables and check for termination*/
        int mover = -1; // Needs to become a valid index
        for (int i = 0; i < 9; i++) {
            if (directions[i] != 0) {
                mover = i;
            }
        }
        if (mover < 0) {
            // There are no more permutations
            throw new RuntimeException("There are no more Pandigits.");
        }
        // Now, actually perform step one
        for (int i = 0; i < 9; i++) {
            if ((directions[i] != 0) && (pandigit[mover] < pandigit[i])) {
                mover = i;
            }
        }

        /* Step two: Swap that digit with the adjacent digit and the corresponding
        directions*/
        int dir = directions[mover];
        int tempDigit = pandigit[mover + dir];
        int tempDirection = directions[mover + dir];
        pandigit[mover + dir] = pandigit[mover];
        directions[mover + dir] = directions[mover];
        pandigit[mover] = tempDigit;
        directions[mover] = tempDirection;

        /* Step three: Update the directions according to the following rules.
        1) If the digit just swapped becomes adjacent to a larger number or
        the end, remove its direction.
        2) All digits larger than the newly swapped digit, get a sign so that
        they want to move beyond the newly swapped digit.*/
        if ((mover + dir == 0)
                || (mover + dir == 8)
                || (pandigit[mover + dir + dir] > pandigit[mover + dir])) {
            /* Note, this uses short-circuit evaluation to ensure no array
            bounds exceptions.*/
            directions[mover + dir] = 0;
        }
        for (int i = 0; i < mover + dir; i++) {
            if (pandigit[i] > pandigit[mover + dir]) {
                directions[i] = 1;
            }
        }
        for (int i = 8; i > mover + dir; i--) {
            if (pandigit[i] > pandigit[mover + dir]) {
                directions[i] = -1;
            }
        }
    }

    /* Attempt to build a 9-digit number by taking the first `i` digits
    in `pandigit`, concatenating them into one int, then concatenating
    multiples of it to itself until the result is at least 9 digits long.
    After completing this, return whether that number is equal to pandigit.
    */
    private static boolean buildNum(int index) {
        // build base
        String numberStr = "";
        for (int j = 0; j < index; j++) {
            numberStr = numberStr + pandigit[j].toString();
        }
        // make the number big enough
        int base = Integer.parseInt(numberStr);
        int multiple = 2;
        while (numberStr.length() < 9) {
            numberStr = numberStr + new Integer(base * multiple);
            multiple++;
        }
        if (numberStr.length() > 9) {
            // The number is too big
            return false;
        }
        return (Integer.parseInt(numberStr) == getInt());
    }


    /* Return the int form of pandigit. */
    private static int getInt() {
        int output = 0;
        for (int i = 0; i < 9; i++) {
            output = output * 10 + pandigit[i];
        }
        return output;
    }

    /**
    This class implements Nicholas Zufelt's solution to Project Euler problem
    38.  It takes no command-line arguments, and prints the solution to
    standard out.
    */
    public static void main(String[] args) {
        // Populate pandigit
        for (int i = 8; i > -1; i--) {
            pandigit[8 - i] = 1 + i;
        }
        // Run main loop
        int max = 123456789;
        int iterations = 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 - 1;
        int current;
        for (int i = 0; i < iterations; i++) {
            current = getInt();
            if ((isProduct()) && (max < current)) {
                System.out.println("Found new pandigital product: " + current);
                max = current;
            }
            nextPandigit();
        }
        System.out.println("The largest pandigital product is " + max);
    }

    /* Determine if pandigit is a product of some integer n with some sequence
    (1,2,...,n)*/
    private static boolean isProduct() {
        for (int i = 1; i <= pandigit.length / 2; i++) {
            if (buildNum(i)) {
                return true;
            }
        }
        return false;
    }
}
