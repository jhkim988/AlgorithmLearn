import java.util.*;

public class BinSearch {
  public static void main(String[] args) {
    final int len = 15;
    final int lim = 10;
    int[] seq = new int[len];
    for (int i = 0; i < len; i++) {
      seq[i] = (int) (Math.random() * lim);
    }

    Arrays.sort(seq);
    System.out.println(Arrays.toString(seq));
    System.out.println(binSearch(seq, 5));
  } 
  static int binSearch(int[] seq, int key) {
    int ptr1 = 0;
    int ptr2 = seq.length - 1;

    while (ptr1 < ptr2) {
      int mid = (ptr1 + ptr2) / 2;
      if (seq[mid] < key) {
        ptr1 = mid + 1;
      } else if (seq[mid] > key) {
        ptr2 = mid - 1;
      } else { // Find
        return mid;
      }
    }

    // Not Found.
    // Find Insertion Point
    ptr2 = seq.length - 1;
    while (ptr1 < ptr2) { // use upper bound
      int mid = (ptr1 + ptr2) / 2;
      if (seq[mid] < key) {
        ptr1 = mid + 1;
      } else {
        ptr2 = mid - 1;
      }
    }
    return -ptr1 - 1;
  } 
}
