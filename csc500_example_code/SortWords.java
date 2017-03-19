/**
 * Turn an ArrayList<String> of words into an ArrayList<ArrayList<String>>
 * of "bins" of words based on starting letter.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 26, 2016
 * Course: AP Computer Science A
 */

import java.util.ArrayList;

public class SortWords {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<String>();

        myList.add("apple");
        myList.add("orange");
        myList.add("banana");
        myList.add("fruitcake");
        myList.add("cat");
        myList.add("salmonella");
        myList.add("counting");
        myList.add("femtosecond");

        /* I originally attempted to make an array of arraylists, but it turns
         * out Java doesn't let you do that! So we do need to do this,
         * after all. */
        ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < 26; i++) {
            buckets.add(new ArrayList<String>());
        }

        for (String word : myList) {
            char firstLetter = word.charAt(0);

            // Method 0: Dr Z's first guess
            int index0 = Character.getNumericValue(firstLetter) - Character.getNumericValue('a');

            // Method 1: Suggested by 4th Period
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            int index1 = alphabet.indexOf(firstLetter);

            // Both methods work fine:
            System.out.println(word + ": " + index0 + " " + index1);

            buckets.get(index0).add(word);
        }

        // Print out buckets to test the code:
        for (ArrayList<String> bucket : buckets) {
            if (bucket.size() > 0)
                System.out.println(bucket);
        }
    }
}
