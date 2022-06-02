import java.io.*;
import java.util.*;

public class BOJ10124 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int n, m;
  static int[] arr;
  static final int NUMLOOP = 25;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    arr = new int[n+1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    useRandomize();
  }
  static void useRandomize() throws IOException {
    ArrayList<ArrayList<Integer>> ncount = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      ncount.add(new ArrayList<>());
    }
    for (int i = 1; i <= n; i++) {
      ncount.get(arr[i]).add(i);
    }
    while (m-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(response(ncount, lo, hi)));
      bw.newLine();
    }
    bw.flush();
  }
  static int response(ArrayList<ArrayList<Integer>> ncount, int lo, int hi) {
    for (int i = 0; i < NUMLOOP; i++) {
      int r = arr[(int) (Math.random()*(hi-lo+1) + lo)];
      int val = upperbound(ncount.get(r), hi) - lowerbound(ncount.get(r), lo);
      if (hi-lo+1 < 2*val) return r;
    }
    return 0;
  }
  static int upperbound(ArrayList<Integer> arr, int key) {
    int lo = -1, hi = arr.size();
    while (lo+1 < hi) {
      int mid = (lo+hi)>>1;
      if (arr.get(mid) <= key) lo = mid;
      else hi = mid;      
    }
    return hi;
  }
  static int lowerbound(ArrayList<Integer> arr, int key) {
    int lo = -1, hi = arr.size();
    while (lo+1 < hi) {
      int mid = (lo+hi)>>1;
      if (arr.get(mid) < key) lo = mid;
      else hi = mid;      
    }
    return hi;
  }
}
