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

    useMosAlgorithm(n, c, arr);
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
    int[] ncount = new int[n+1];
    int[] res = new int[numQuery];
    int lo = 0, hi = 0, max = 0;
    for (Query q : query) {
      while (hi < q.hi) {
        int val = add(arr[++hi], count, ncount, max);
      }
      while (lo > q.lo) add(arr[--lo], count, ncount, max);
      while (lo < q.lo) sub(arr[lo++], count, ncount);
      while (hi > q.hi) sub(arr[hi--], count, ncount);
    }

  }
  static int add(int color, int[] count, int[] ncount, int max) {
    if (count[color] != 0) ncount[count[color]]--;
    count[color]++;
    ncount[count[color]]++;
    return Integer.max(max, count[color]);
  }
  static void sub(int color, int[] count, int[] ncount) {

  }
}
