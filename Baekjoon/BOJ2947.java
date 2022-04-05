import java.io.*;
import java.util.*;

public class BOJ2947 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[5];
    for (int i = 0; i < 5; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    outer: while (true) {
      for (int i = 0; i < 4; i++) {
        if (arr[i] > arr[i+1]) swap(arr, i, i+1);
      }
      for (int i = 0; i < 5; i++) {
        if (arr[i] != i+1) continue outer;
      }
      break;
    }
    bw.flush();
  }
  static void swap(int[] arr, int idx1, int idx2) throws IOException {
    int tmp = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = tmp;
    print(arr);
  }
  static void print(int[] arr) throws IOException {
    for (int el : arr) {
      bw.write(Integer.toString(el));
      bw.write(' ');
    }
    bw.newLine();
  }
}
