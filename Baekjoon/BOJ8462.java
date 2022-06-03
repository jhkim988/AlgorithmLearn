import java.io.*;
import java.util.*;

public class BOJ8462 {
  static int[] count;
  static long val;
  private static class Query {
    int lo, hi, id;
    Query(int lo, int hi, int id) {
      this.lo = lo;
      this.hi = hi;
      this.id = id;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    int[] arr = new int[n+1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    ArrayList<Query> query = new ArrayList<>();
    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      query.add(new Query(lo, hi, i));
    }

    int k = (int) Math.sqrt(n);
    Collections.sort(query, new Comparator<Query>(){
      @Override
      public int compare(Query q1, Query q2) {
        if (q1.lo/k != q2.lo/k) return q1.lo/k-q2.lo/k;
        return q1.hi-q2.hi;
      }
    });
  
    count = new int[1_000_001];
    long[] res = new long[t];
    int lo = 0, hi = 0;
    val = 0;
    for (Query q : query) {
      while (lo > q.lo) add(arr[--lo]);
      while (hi < q.hi) add(arr[++hi]);
      while (lo < q.lo) sub(arr[lo++]);
      while (hi > q.hi) sub(arr[hi--]);
      res[q.id] = val;
    }
    for (long r : res) {
      bw.write(Long.toString(r));
      bw.newLine();
    }
    bw.flush();
  }
  static void add(int x) {
    val -= count[x]*count[x]*x;
    count[x]++;
    val += count[x]*count[x]*x;
  }
  static void sub(int x) {
    val -= count[x]*count[x]*x;
    count[x]--;
    val += count[x]*count[x]*x;
  }
}
