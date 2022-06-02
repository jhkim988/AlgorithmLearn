import java.io.*;
import java.util.*;

public class BOJ2912 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static class Query {
    int id, lo, hi;
    Query(int id, int lo, int hi) {
      this.id = id;
      this.lo = lo;
      this.hi = hi;
    }
  }
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // useMosAlgorithm(n, c, arr);
    useRandomize(n, c, arr);
  }
  static void useMosAlgorithm(int n, int c, int[] arr) throws IOException {
    ArrayList<Query> query = new ArrayList<>();
    int numQuery = Integer.parseInt(br.readLine());
    for (int id = 0; id < numQuery; id++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      query.add(new Query(id, lo, hi));
    }    
    int k = (int) Math.sqrt(n);
    Collections.sort(query, new Comparator<Query>() {
      @Override
      public int compare(Query q1, Query q2) {
        return q1.lo/k != q2.lo/k ? q1.lo/k-q2.lo/k : q1.hi-q2.hi;
      }
    });
    int[] count = new int[c+1];
    int[] res = new int[numQuery];
    int lo = 0, hi = 0;
    for (Query q : query) {
      while (hi < q.hi) count[arr[++hi]]++;
      while (lo > q.lo) count[arr[--lo]]++;
      while (lo < q.lo) count[arr[lo++]]--;
      while (hi > q.hi) count[arr[hi--]]--;
      for (int color = 1; color <= c; color++) {
        if (2*count[color] <= hi-lo+1) continue;
        res[q.id] = color;
      }
    }
    for (int r : res) {
      if (r == 0) {
        bw.write("no");
      } else {
        bw.write("yes ");
        bw.write(Integer.toString(r));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static void useRandomize(int n, int c, int[] arr) throws IOException {
    ArrayList<ArrayList<Integer>> ncolor = new ArrayList<>();
    for (int i = 0; i <= c; i++) {
      ncolor.add(new ArrayList<>());
    }
    for (int i = 1; i <= n; i++) {
      ncolor.get(arr[i]).add(i);
    }

    int numQuery = Integer.parseInt(br.readLine());
    final int NUMLOOP = 15;
    while (numQuery-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      int res = response(arr, ncolor, lo, hi, NUMLOOP);
      if (res == -1) {
        bw.write("no");
      } else {
        bw.write("yes ");
        bw.write(Integer.toString(res));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static int response(int[] arr, ArrayList<ArrayList<Integer>> ncolor, int lo, int hi, int NUMLOOP) {
    for (int i = 0; i < NUMLOOP; i++) {
      int r = arr[(int)(Math.random()*(hi-lo+1) + lo)];
      int val = upperbound(ncolor.get(r), hi) - lowerbound(ncolor.get(r), lo);
      if (hi-lo+1 < 2*val) return r;
    }
    return -1;
  }
  static int upperbound(ArrayList<Integer> arr, int key) {
    int lo = -1, hi = arr.size();
    while (lo+1 < hi) {
      int mid = (lo+hi)>>1;
      if (arr.get(mid) <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int lowerbound(ArrayList<Integer> arr, int key) {
    int lo = -1, hi = arr.size();
    while (lo+1 < hi) {
      int mid = (lo+hi)>>1;
      if (arr.get(mid) < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
