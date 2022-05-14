import java.io.*;
import java.util.*;

public class BOJ1059 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int l = Integer.parseInt(br.readLine());
    int[] set = new int[l];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < l; i++) {
      set[i] = Integer.parseInt(st.nextToken());
    }
    int n = Integer.parseInt(br.readLine());
    Arrays.sort(set);
    int answer = numGoodInterval(set, n);
    bw.write(Integer.toString(answer));
    bw.flush();
  } 
  static int numGoodInterval(int[] arr, int n) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] <= n) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    int left = lo; 
    if (left == -1) return n*(arr[0]-n)-1;
    if (arr[left] == n) return 0;
    lo = -1; hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] <= n) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    int right = hi;
    return (n-arr[left])*(arr[right]-n)-1;
  }
}
