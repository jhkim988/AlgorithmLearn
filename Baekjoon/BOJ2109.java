import java.io.*;
import java.util.*;

public class BOJ2109 {
  private static class Pair implements Comparable<Pair> {
    int val;
    int due;
    Pair(int val, int due) {
      this.val = val;
      this.due = due;
    }
    @Override
    public int compareTo(Pair other) {
      return this.val == other.val ? this.due - other.due : other.val - this.val;
    }
    @Override
    public String toString() {
      return val + ", " + due;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int num = Integer.parseInt(br.readLine());
    Pair[] data = new Pair[num];
    int maxDate = 0;
    for (int i = 0; i < num; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      data[i] = new Pair(p, d);
      maxDate = Math.max(maxDate, d);
    }
    Arrays.sort(data, new Comparator<>() {
      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.due == p2.due ? p2.val - p1.val : p2.due - p1.due;
      }
    });
    int ptr = 0;
    int sum = 0;

    // System.out.println("maxDate: " + maxDate);
    // for (int i = 0; i < num; i++) {
    //   System.out.println("sort: " + data[i]);
    // }

    PriorityQueue<Pair> pq = new PriorityQueue<>();
    while (maxDate > 0) {
      while (ptr < num && data[ptr].due >= maxDate) {
        // System.out.println("enqueue: " + data[ptr]);
        pq.add(data[ptr++]);
      }
      if (!pq.isEmpty()) {
        int val = pq.poll().val;
        sum += val;
        // System.out.println("add : " + val + ", sum: " + sum);
      }
      maxDate--;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
