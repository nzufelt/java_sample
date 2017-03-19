/**
 * Compare two Books by their title, rather than the default (number of
 * pages)
 *
 * Author: Nicholas Zufelt
 * Date: March 18, 2017
 * Course: AP Computer Science A
 */

import java.util.Comparator;

public class TitleComparator implements Comparator<Book> {
    private boolean reverse;

    public TitleComparator(boolean reverse) {this.reverse = reverse;}
    public TitleComparator() {this.reverse = false;}

    // Compare two books via their title
    public int compare(Book book1, Book book2) {
        String firstWord1 = titleWord(book1.getTitle());
        String firstWord2 = titleWord(book2.getTitle());

        return firstWord1.compareTo(firstWord2);
    }

    /* Return the "title first word" of a word:
    * titleWord("Star Wars") --> "Star"
    * titleWord("The Great Escape") --> "Great"
    * titleWord("A New Hope") --> "New"
    */
    private static String titleWord(String title) {
        String[] articles = {"a", "an", "the"};
        for (String article : articles) {
            if (findWord(0, title).toLowerCase().equals(article)) {
                return findWord(1, title);
            }
        }
        return findWord(0, title);
    }

    /* Return the nth word of a string (zero indexed), or "" if title has
    * fewer than n words.
    *
    * Precondition: `title` contains only words and spaces
    */
    private static String findWord(int n, String title) {
        // base case: return the empty word if necessary
        if ((title.equals("")) || (n < 0)) return "";

        int firstSpace = title.indexOf(' ');
        if (n == 0) return title.substring(0, firstSpace);

        // recursive step
        return findWord(n - 1, title.substring(firstSpace + 1));
    }

    public static void main(String[] args) {
        String title = "The quick brown fox jumped over the lazy dog";
        // Test code!  Run with `java -ea TitleComparator` to "enable assertions"
        // test to make sure findWord works
        assert findWord(3, title).equals("fox");
        assert findWord(5, title).equals("over");

        // test titleWord
        assert titleWord(title).equals("quick");
        assert titleWord("Star Wars").equals("Star");
        assert titleWord("The Great Escape").equals("Great");
        assert titleWord("A New Hope").equals("New");

        try {
            assert 0 == 1;
            System.out.println("Assertions aren't enabled!");
        } catch (AssertionError e) {
            System.out.println("All your assertions passed!");
        }
    }
}
