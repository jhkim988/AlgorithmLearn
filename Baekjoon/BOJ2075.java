import java.io.*;
import java.util.*;

public class BOJ2075 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] sort = new int[n*n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        sort[i*n+j] = Integer.parseInt(st.nextToken());
      }
    } 
    Arrays.sort(sort);
    // if there is duplicate element...
    // int t = 1;
    // int tIdx = binSearch(sort[n*n-1], sort);
    // while (t < n) {
    //   tIdx = binSearch(sort[tIdx], sort) - 1;
    //   t++;
    // }
    // bw.write(Integer.toString(sort[tIdx]));
    bw.write(Integer.toString(sort[n*n-n]));
    bw.newLine();
    bw.flush();
  }
  static int binSearch(int key, int[] arr) {
    int lo = -1, hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
