/**
 * A small class with two fields, used to discuss compareTo and comparitors.
 *
 * Author: Nicholas Zufelt
 * Date: March 18, 2017
 * Course: AP Computer Science A
 */

import java.util.Arrays;
import java.util.Comparator;

public class Book implements Comparable<Book> {
    private int pages;
    private String title;
    private String author;

    public Book(int p, String t, String a) {pages = p; title = t; author = a;}

    public String getTitle() {return title;}

    public String toString() {
        return String.format("\"%s\" by %s (%d pages)", title, author, pages);
    }

    // This comparison is by page number
    public int compareTo(Book other) {
        return this.pages - other.pages;
    }

    // Return a more easy-to-read version of the Arrays.toString printout
    public static String formatArrayPrintout(String arrStr) {
        return arrStr.replace("[", "").replace("]", "").replace(", \"", ",\n\"");
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(368, "The Jungle Book", "Rudyard Kipling"),
            new Book(221, "2001: A Space Odyssey", "Arthur C. Clarke"),
            new Book(544, "Great Expectations", "Charles Dickens"),
            new Book(928, "Don Quixote", "Miguel de Cervantes")
        };
        // Used to check out compareTo
        for (Book book: books) {
            System.out.println(book);
            System.out.println(book.compareTo(books[0]));
        }

        System.out.println("\nOriginal array:\n" + formatArrayPrintout(Arrays.toString(books)));

        // Arrays.sort is in-place, so we have to put it first:
        Arrays.sort(books); // This changed books!!!

        System.out.println("\nSorted by page count:\n" + formatArrayPrintout(Arrays.toString(books)));

        // Arrays.sort can also take a comparator:
        Comparator<Book> byTitles = new TitleComparator();
        Arrays.sort(books, byTitles); // This changed books!!!

        System.out.println("\nSorted by title:\n" + formatArrayPrintout(Arrays.toString(books)));
    }
}
