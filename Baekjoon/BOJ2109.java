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
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int num = Integer.parseInt(br.readLine());
    Pair[] data = new Pair[num];
    for (int i = 0; i < num; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      data[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(data);
    int date = 0;
    int sum = 0;
    for (int i = 0; i < num; i++) {
      if (date >= data[i].due) continue;
      sum += data[i].val;
      date++;
    }

    bw.write(sum + "\n");
    bw.flush();
  }
}
