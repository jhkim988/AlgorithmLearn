import java.io.*;
import java.util.*;

public class BOJ14897 {
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
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int numQuery = Integer.parseInt(br.readLine());
    ArrayList<Query> query = new ArrayList<>();
    for (int i = 0; i < numQuery; i++) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      query.add(new Query(lo, hi, i));
    }
    int k = (int) Math.sqrt(n);
    Collections.sort(query, new Comparator<Query>() {
      @Override
      public int compare(Query q1, Query q2) {
        return q1.lo/k != q2.lo/k ? q1.lo/k-q2.lo/k : q1.hi-q2.hi;
      }
    });
    
  }
}
