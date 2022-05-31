import java.io.*;
import java.util.*;

public class BOJ13547 {
  private static class Pair {
    int id, lo, hi;
    Pair(int id, int lo, int hi) {
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
    int[] arr = new int[n+1];
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int q = Integer.parseInt(br.readLine());
    int[] response = new int[q];
    ArrayList<Pair> query = new ArrayList<>();
    for (int i = 0; i < q; i++) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      query.add(new Pair(i, lo, hi));
    }
    int k = (int) Math.sqrt(n);
    Collections.sort(query, new Comparator<Pair>() {
      @Override
      public int compare(Pair p1, Pair p2) {
        if (p1.lo/k != p2.lo/k) return p1.lo/k - p2.lo/k;
        return p1.hi-p2.hi;        
      }
    });
    
    int[] check = new int[1_000_001];
    int num = 0;
    Pair prev = query.get(0);
    for (int i = prev.lo; i <= prev.hi; i++) {
      if (check[arr[i]] == 0) num++;
      check[arr[i]]++;
    }
    response[prev.id] = num;
    for (int i = 1; i < query.size(); i++) {
      Pair crnt = query.get(i);
      for (int j = prev.lo-1; j >= crnt.lo; j--) {
        if (check[arr[j]] == 0) num++;
        check[arr[j]]++;
      }
      for (int j = prev.lo; j < crnt.lo; j++) {
        check[arr[j]]--;
        if (check[arr[j]] == 0) num--;
      }
      for (int j = prev.hi; j > crnt.hi; j--) {
        check[arr[j]]--;
        if (check[arr[j]] == 0) num--;
      }
      for (int j = prev.hi+1; j <= crnt.hi; j++) {
        if (check[arr[j]] == 0) num++;
        check[arr[j]]++;
      }
      response[crnt.id] = num;
      prev = crnt;
    }

    for (int i = 0; i < q; i++) {
      bw.write(Integer.toString(response[i]));
      bw.newLine();
    }
    bw.flush();
  }
}
