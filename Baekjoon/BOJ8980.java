import java.io.*;
import java.util.*;

public class BOJ8980 {
  private static class Pair {
    int lo, hi, weight;
    Pair(int lo, int hi, int weight) {
      this.lo = lo;
      this.hi = hi;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(br.readLine());
    ArrayList<Pair> arr = new ArrayList<>();
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      arr.add(new Pair(lo, hi, weight));
    }
    Collections.sort(arr, new Comparator<Pair>(){
      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.hi != p2.hi ? p1.hi-p2.hi : p1.lo-p2.lo;
      }
    });
    int maxDeliver = 0;
    int[] val = new int[n+1];
    for (Pair p : arr) {
      int max = 0;
      for (int i = p.lo; i <= p.hi; i++) {
        if (max < val[i]) max = val[i];
      }
      if (c <= max) continue;
      int add = Integer.min(c-max, p.weight);
      for (int i = p.lo; i < p.hi; i++) {
        val[i] += add;
      }
      maxDeliver += add;
    }
    bw.write(Integer.toString(maxDeliver));
    bw.flush();
  }
}
