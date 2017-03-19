/**
 * Use memoization to speed up an `isPrime` method for the first `n` numbers.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 23, 2016
 * Course: AP Computer Science A
 */

import java.util.ArrayList;

public class MemoizedIsPrime {
    private static final int MAX_NUM = 1000000;
    private static ArrayList<Integer> primes = new ArrayList<Integer>();

    /* A memoized version: only checks those numbers that are
     * already known to be prime. */
    public static boolean isPrimeMem(int n) {
        for (Integer p : primes) {
            if (p*p <= n && n % p == 0)
                return false;
            else
                // This saves us a LOT of checking.  Try removing it and seeing
                // how long this method takes!
                break;
        }
        // This number `n` is indeed prime, so add it to `primes` before returning
        primes.add(n);
        return true;
    }

    /* The non-memoized version, to compare times */
    public static boolean isPrime(int n) {
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 2; i < MAX_NUM; i++) {
            isPrime(i);
        }
        long endTime = System.currentTimeMillis();
        long durationUnMem = (endTime - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 2; i < MAX_NUM; i++) {
            isPrimeMem(i);
        }
        endTime = System.currentTimeMillis();
        long durationMem = (endTime - startTime);

        System.out.println("Normal: " + durationUnMem + " milliseconds");
        System.out.println("Memoized: " + durationMem + " milliseconds");
    }
}
