import java.io.*;
import java.util.*;

public class BOJ1205 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int score = Integer.parseInt(st.nextToken());
    int p = Integer.parseInt(st.nextToken());
    
    if (n == 0) {
      bw.write("1");
      bw.flush();
      return;
    }

    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int rank = upper(arr, score);
    int lower = lower(arr, score);
    if (lower >= p) {
      bw.write("-1");
    } else {
      bw.write(Integer.toString(rank+1));
    }
    bw.flush();
  }
  static int upper(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while(lo+1 < hi) {
      int mid = (lo+hi)>>1;
      if (arr[mid] > key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int lower(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while(lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] >= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
