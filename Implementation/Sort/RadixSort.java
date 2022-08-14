import java.util.*;

public class RadixSort {
  int[] arr;
  RadixSort(int[] arr) {
    this.arr = arr;
  }
  void sort(int d) {
    ArrayList<Queue<Integer>> bucket = new ArrayList<>();
    for (int i = 0; i < d; i++) {
      bucket.add(new LinkedList<>());
    }

    int digit = 1;
    while (true) {
      int maxDigit = 0;
      for (int el: arr) {
        if (maxDigit < el) maxDigit = el;
        bucket.get(el/digit%d).add(el);
      }
      int ptr = 0;
      for (Queue<Integer> que: bucket) {
        while (!que.isEmpty()) {
          arr[ptr++] = que.poll();
        }
      }
      digit *= d;
      if (maxDigit < digit) break;
    }
  }

  public static void main(String[] args) {
    int[] test = {40, 50, 60, 10, 30, 80};
    RadixSort rs = new RadixSort(test);
    rs.sort(10);

    for (int x: test) {
      System.out.println(x);
    }
  }
}
