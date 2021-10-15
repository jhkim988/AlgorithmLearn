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
      return this.due == other.due ? other.val - this.val : this.due - other.due;
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
    Arrays.sort(data);
    for (int i = 0; i < num; i++) {
      System.out.println(data[i].val + ", " + data[i].due);
    }
    int ptr = num - 1;
    int sum = 0;
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    while (ptr >= 0) {
      while (ptr >= 0 && data[ptr].due == maxDate) {
        System.out.println("input: " + data[ptr].val + ", " + data[ptr].due);
        pq.add(data[ptr--]);
      }
      if (ptr < 0) break;
      sum += pq.poll().val;
      maxDate = data[ptr].due;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
