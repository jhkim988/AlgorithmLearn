import java.io.*;
import java.util.*;

public class BOJ11497 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      // int answer = parametricSearch(arr);
      int answer = greedy(arr);
      bw.write(Integer.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
  static int greedy(int[] arr) {
    int gap = 0;
    for (int i = 0; i+2 < arr.length; i++) {
      if (gap < arr[i+2]-arr[i]) gap = arr[i+2]-arr[i];
    }
    return gap;
  }
  static int parametricSearch(int[] arr) {
    int lo = -1, hi = arr[arr.length-1]-arr[0];
    while (lo+1 < hi) {
      int mid = (lo + hi) >> 1;
      if (possible(arr, mid)) {
        hi = mid;
      } else {
        lo = mid;
      }
    }
    return hi;
  }
  static boolean possible(int[] arr, int gap) {
    boolean[] visit = new boolean[arr.length];
    int ptr = 0;
    while (ptr < arr.length && gap < arr[arr.length-1] - arr[ptr]) {
      int lo = ptr, hi = arr.length;
      while (lo+1 < hi) {
        int mid = (lo + hi) >> 1;
        if (arr[mid] <= arr[ptr] + gap) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      if (lo == ptr) return false;
      ptr = lo;
      visit[ptr] = true;
    }
    int prev = arr.length-1;
    visit[0] = visit[arr.length-1] = false;
    for (int i = arr.length-2; i >= 0; i--) {
      if (visit[i]) continue;
      if (gap < arr[prev]-arr[i]) return false;
      prev = i;
    }
    return true;
  }
}
