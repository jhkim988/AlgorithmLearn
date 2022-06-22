import java.io.*;
import java.util.*;

public class BOJ1781 {
  private static class Pair {
    int date, profit;
    Pair(int date, int profit) {
      this.date = date;
      this.profit = profit;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Pair> arr = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int date = Integer.parseInt(st.nextToken());
      int profit = Integer.parseInt(st.nextToken());
      arr.add(new Pair(date, profit));
    }
    Collections.sort(arr, new Comparator<Pair>(){
      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.date!=p2.date ? p1.date-p2.date : p2.profit-p1.profit;
      }
    });

    PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<>(){
      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.profit!=p2.profit ? p2.profit-p1.profit : p1.date-p2.date;
      }
    });
    
    int val = 0;
    int ptr = arr.size()-1;
    int date = arr.get(ptr).date;
    for (int d = date; d > 0; d--) {
      while (ptr >= 0 && arr.get(ptr).date == d) pq.add(arr.get(ptr--));
      if (!pq.isEmpty()) val += pq.poll().profit;
    }
    bw.write(Integer.toString(val));
    bw.flush();
  }
}
