/**
 * The Box class is the generic implementation of the BoxBasic class.  With it,
 * one may now use the method of its object.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 30, 2016
 * Course: AP Computer Science A
 */

public class Box<T> {
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String toString(){
        return "Box containing: " + object;
    }

    public static void main(String[] args) {
        String s = "The contents of box 1";
        Box<String> box1 = new Box<String>();
        box1.setObject(s);

        Character c = 'Z';
        Box<Character> box2 = new Box<Character>();
        box2.setObject(c);

        System.out.println(box1);
        System.out.println(box2);

        // Now we can do these!
        System.out.println("\nsubstring works: " + box1.getObject().substring(5));
        System.out.println("compareTo works: " + box2.getObject().compareTo('N'));
    }

}
