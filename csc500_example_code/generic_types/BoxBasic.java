/**
 * An attempt to create a box that holds an Object of any type.  It fails,
 * demonstrating that we need to use generics.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 30, 2016
 * Course: AP Computer Science A
 */

public class BoxBasic {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String toString(){
        return "Box containing: " + object;
    }

    public static void main(String[] args) {
        String s = "The contents of box 1";
        BoxBasic box1 = new BoxBasic();
        box1.setObject(s);

        Character c = 'Z';
        BoxBasic box2 = new BoxBasic();
        box2.setObject(c);

        System.out.println(box1);
        System.out.println(box2);

        // But we can't do the following.  Uncomment to see!
        // System.out.println(box1.getObject().substring(5));
        // System.out.println(box2.getObject().compareTo('N'));
    }
}
