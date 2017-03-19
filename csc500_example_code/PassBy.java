/**
 * A simple program that demonstrates the property that Java is
 * "pass-by-copy-of-reference".  Read through the comments to understand more.
 *
 * Author: Nicholas Zufelt
 * Date: Jan 10, 2017
 * Course: AP Computer Science A
 */

public class PassBy {
    private int field;

    public PassBy (int param) {
        field = param;
    }

    /* This is the static version of the method, so it is
     * called a bit differently from the non-static version */
    public static void changeField(PassBy paramObject, int n) {
        paramObject.field = n;
    }

    /* This is the non-static version of the method, so it is
     * called a bit differently from the static version */
    public void changeField(int n) {
        field = n;
    }

    /* This method "proves" that Java passes a copy of the reference to the
     * object into its methods */
    public static void deleteReference(PassBy paramObject) {
        // This is the same object! So it's pass-by-reference...
        System.out.println(paramObject);

        // ...but we CAN change the field...
        paramObject.field = 82345345;

        // ...and this doesn't actually delete the object, so it's actually
        // pass-by-copy-of-reference
        paramObject = null;
    }

    public static void main(String[] args) {
        // Let's create an object to mess with:
        PassBy object1 = new PassBy(6);

        System.out.println(object1.field);

        // Let's change it using the static method:
        changeField(object1, 5);
        System.out.println(object1.field);

        // Let's change it using the non-static method:
        object1.changeField(2);
        System.out.println(object1.field);

        // Let's try now to delete the object's reference using a method:
        System.out.println(object1); // a certain object!
        deleteReference(object1);

        // If Java passed the actual object ("pass-by-value"), or even if
        // it passed the actual reference to the object, then this would
        // throw a NullPointerException.
        System.out.println(object1.field);

        // Finally, let's delete the object's reference here.  This time
        // we'll actually get a NullPointerException, so we "catch" the
        // exception gracefully, so that the program doesn't crash.
        object1 = null;
        try {
            System.out.println(object1.field);
        } catch (NullPointerException e){
            System.out.println("Oh no! A Null Pointer Exception!");
        }

        // To see that the program didn't crash with NullPointerException above:
        System.out.println("That's okay, we caught it.");
    }
}
