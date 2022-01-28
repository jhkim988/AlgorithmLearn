import java.io.*;
import java.util.*;

public class BOJ1377 {
  static private class Pair {
    int index, value;
    Pair(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }
  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] input = new int[n];
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }
    Pair[] sorted = new Pair[n];
    for (int i = 0; i < n; i++) {
      sorted[i] = new Pair(i, input[i]);
    }
    Arrays.sort(sorted, (a, b) -> a.value == b.value ? a.index - b.index : a.value - b.value);

  }
  static void swap(int[] input, Pair[] sorted, int a, int b) {
    
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
  }
  static int binarySearch(Pair[] sorted, int value) {
    return 0;
  }
}