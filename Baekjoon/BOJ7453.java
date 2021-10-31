import java.io.*;
import java.util.*;

public class BOJ7453 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    int[][] data = new int[4][len];

    for (int i = 0; i < len; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());      
      for (int j = 0; j < 4; j++) {
        data[j][i] = Integer.parseInt(st.nextToken());
      }
    }

    int[] left = new int[len * len];
    int[] right = new int[len * len];

    init(left, data[0], data[1]);
    init(right, data[2], data[3]);
    // size of left, right: n^2
    Arrays.sort(left); // n^2 log(n)
    Arrays.sort(right); // n^2 log(n)

    long count = 0L;

    // use binary search:
    for (int num : left) { // n^2
      int lo = lowerbound(right, -num);
      int hi = upperbound(right, -num);
      if (right[lo] != -num ) continue;
      if (right[hi] == -num) hi++; // case: size 1
      count += hi - lo;
    }

    bw.write(count + "\n");
    bw.flush();
  }
  
  static void init(int[] storage, int[] arr0, int[] arr1) {
    for (int i = 0; i < arr0.length; i++) {
      for (int j = 0; j < arr1.length; j++) {
        storage[i * arr0.length + j] = arr0[i] + arr1[j];
      }
    }
  }

  static int upperbound(int[] arr, int key) {
    int lo = 0;
    int hi = arr.length - 1;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] <= key) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return hi;
  }

  static int lowerbound(int[] arr, int key) {
    int lo = 0;
    int hi = arr.length - 1;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] < key) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
