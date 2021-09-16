package BinSearch;

import java.util.*;

public class UpperBound {
  public static void main(String[] args) {
    final int len = 10;
    final int lim = 8;

    int[] seq = new int[len];
    for (int i = 0; i < len; i++) {
      seq[i] = (int) (Math.random() * lim);
    }

    Arrays.sort(seq);
    System.out.println(Arrays.toString(seq));
    System.out.println(upperBound(seq, 5));
    System.out.println(lowerBound(seq, 5));
  }  
  static int upperBound(int[] seq, int key) {
    // find min { x : key < seq[x] } 
    int ptr1 = 0;
    int ptr2 = seq.length - 1;

    while (ptr1 < ptr2) {
      int mid = (ptr1 + ptr2) / 2;
      if (seq[mid] <= key) {
        ptr1 = mid + 1;
      } else {
        ptr2 = mid - 1;
      }
    }

    return ptr1;
  }
  static int lowerBound(int[] seq, int key) {
    // find max { x : seq[x] < key }
    int ptr1 = 0;
    int ptr2 = seq.length - 1;
    
    while (ptr1 < ptr2) {
      int mid = (ptr1 + ptr2) / 2;
      if (seq[mid] < key) {
        ptr1 = mid + 1;
      } else {
        ptr2 = mid - 1;
      }
    }
    return ptr2;
  }
}
