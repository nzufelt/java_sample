import java.util.*;

public class ArrayListWithSum<T> extends ArrayList<T>
                  implements ListWithSum<T>
{
  public ArrayListWithSum() {
    super();
  }

  public ArrayListWithSum (int capacity) {
    super(capacity);
  }

  public T sum() {
    T total;

    for (T i : this) {
      total.add(i);
    }

    return total;
  }

  public static void main(String[] args) {
    ArrayListWithSum<Integer> myList = new ArrayListWithSum<Integer>();

    myList.add(2);
    myList.add(2);
    myList.add(1);
    myList.add(2);
    myList.add(0);
    myList.add(-4);
    myList.add(231343);

    System.out.println(myList);
    System.out.println(myList.sum());
  }
}
