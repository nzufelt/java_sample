/**
 * Recursively print out an animal's hierarchy, which is a String
 *
 * Author: Nicholas Zufelt
 * Date: February 10, 2016
 * Course: AP Computer Science A
 */

public class AnimalKingdom {
    // Print out `Dog, a subset of Canid,` etc., recursively
    public static void recursivePrint(String animal) {
        int firstSpace = animal.indexOf(' ');
        int secondSpace = animal.indexOf(' ', firstSpace + 1);

        if (secondSpace < 0)
            return;

        String word1 = animal.substring(0, firstSpace);
        String word2 = animal.substring(firstSpace + 1, secondSpace);

        System.out.println(word1 + ", a subset of " + word2 + ",");

        recursivePrint(animal.substring(firstSpace + 1));
    }
    /* Note: we could have made a fancier base case that gets up to "Animal",
     * and then says something like "Animal, and that's the top!".  I encourage
     * you to try adding that to the code!
     */

    public static void main(String[] args) {
        String greyhound = "Greyhound Hound Dog Canid Caniformia Canivore Mammal Chordate Animal ";

        String honeybee = "Honeybee Apids Insect Arthropod Animal ";

        recursivePrint(greyhound);
        System.out.println("\n");
        recursivePrint(honeybee);
    }
}
