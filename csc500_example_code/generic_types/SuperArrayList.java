/**
 * Demonstrate the use of Generics by extending ArrayList.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 30, 2016
 * Course: AP Computer Science A
 */

import java.util.ArrayList;

public class SuperArrayList<T> extends ArrayList<T> {
    private T firstItem;

    // Note that constructors don't have a <T> after their name:
    public SuperArrayList (){
        super();
    }

    // Add the first item to firstItem, then perform the usual `ArrayList.add`
    public boolean add(T object) {
        if (this.size() == 0) {
            firstItem = object;
        }
        return super.add(object);
    }

    // helper method to make `main` more readable
    private void printItems() {
        System.out.println("" + this + "; " + firstItem + "\n");
    }

    public static void main(String[] args) {
        SuperArrayList<String> myList = new SuperArrayList<String>();
        System.out.println("Let's track the contents of myList and myList.firstItem");
        System.out.println("After creation of myList: ");
        myList.printItems();

        myList.add("first item!");
        System.out.println("After addition of first item: ");
        myList.printItems();

        myList.add("second item!");
        System.out.println("After addition of second item: ");
        myList.printItems();
    }
}
