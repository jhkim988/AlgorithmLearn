import java.io.*;
import java.util.*;

public class BOJ1517 {
  static int numCall = 0;
  static int numTest = 0;
  static int[] data;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    recur(0, N);
    System.out.println(numCall);
    System.out.println(bubble());
  }
  static void recur(int lo, int hi) { // [lo, hi)
    if (hi - lo <= 1) return;
    int[] tmp = new int[hi - lo];
    System.out.println("lo: " + lo + ", hi: " + hi);
    int mid = hi - (hi - lo) / 2;
    recur(lo, mid);
    recur(mid, hi);
    int loPtr = mid - 1;
    int hiPtr = hi - 1;
    int tmpPtr = tmp.length - 1;
    while (loPtr >= 0 && hiPtr >= mid) {
      System.out.println("loPtr: " + loPtr + ", hiPtr: " + hiPtr);
      while (loPtr >= 0 && hiPtr >= mid && data[loPtr] <= data[hiPtr]) {
        tmp[tmpPtr--] = data[hiPtr];
        hiPtr--;
      }
      numCall += hiPtr - loPtr;
      while (loPtr >= 0 && hiPtr >= mid && data[hiPtr] <= data[loPtr]) {
        tmp[tmpPtr--] = data[loPtr];
        loPtr--;
      }
      numCall += hiPtr - loPtr;
    }
    System.out.println("Out: loPtr: " + loPtr + ", hiPtr: " + hiPtr + ", tmpPtr: " + tmpPtr);
    while (loPtr >= 0) {
      tmp[tmpPtr--] = data[loPtr--];
    }
    while (hiPtr >= mid) {
      tmp[tmpPtr--] = data[hiPtr--];
    }
    for (int i = lo; i < hi; i++) {
      data[i] = tmp[i];
    }
  }
  static int bubble() {
    int numCallSwap = 0;
    int[] copy = data.clone();
    for (int i = 0; i < copy.length; i++) {
      for (int j = 0; j < copy.length - i - 1; j++) {
        if (copy[j] > copy[j + 1]) {
          swap(copy, j, j + 1);
          numCallSwap++;
        }
      }
    }
    for (int i = 0; i < copy.length; i++) {
      System.out.print(copy[i] + " ");
    }
    System.out.println();
    return numCallSwap;
  }
  static void swap(int[] arr, int idx1, int idx2) {
    int tmp = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = tmp;
  }
}
