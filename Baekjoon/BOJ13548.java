import java.io.*;
import java.util.*;

public class BOJ13548 {
  static int max = 0;
  static int[] arr, count, ncount;
  private static class Query {
    int id, lo, hi;
    Query(int id, int lo, int hi) {
      this.id = id;
      this.lo = lo;
      this.hi = hi;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    arr = new int[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int q = Integer.parseInt(br.readLine());
    ArrayList<Query> query = new ArrayList<>();
    for (int i = 0; i < q; i++) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      query.add(new Query(i, lo, hi));
    }

    int k = (int) Math.sqrt(n);
    Collections.sort(query, new Comparator<Query>() {
      @Override
      public int compare(Query q1, Query q2) {
        if (q1.lo/k != q2.lo/k) return q1.lo/k-q2.lo/k;
        return q1.hi-q2.hi;
      }
    });

    count = new int[100_100];
    ncount = new int[100_100];
    int[] response = new int[q];
    int lo = 0, hi = 0;
    for (Query crnt : query) {
      // 순서 주의!
      while (lo > crnt.lo) add(arr[--lo]);
      while (hi < crnt.hi) add(arr[++hi]);
      while (lo < crnt.lo) sub(arr[lo++]);
      while (hi > crnt.hi) sub(arr[hi--]);
      response[crnt.id] = max;
    }

    for (int res : response) {
      bw.write(Integer.toString(res));
      bw.newLine();
    }
    bw.flush();
  }
  static void add(int val) {
    if (count[val] > 0) ncount[count[val]]--;
    count[val]++;
    ncount[count[val]]++;
    max = Integer.max(max, count[val]);
  }
  static void sub(int val) {
    if (count[val] == 0) return;
    ncount[count[val]]--;
    if (count[val] == max && ncount[count[val]] == 0) max--;
    count[val]--;
    ncount[count[val]]++;
  }
}
