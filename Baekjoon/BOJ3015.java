import java.io.*;
import java.util.*;

public class BOJ3015 {
  private static class Pair {
    int height, count;
    Pair(int height, int count) {
      this.height = height;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    long count = 0;
    Stack<Pair> stk = new Stack<>();
    while(N-- > 0) {
      int input = Integer.parseInt(br.readLine());
      Pair p = new Pair(input, 1);
      while (!stk.isEmpty() && stk.peek().height <= input) {
        Pair pop = stk.pop();
        count += pop.count;
        if (pop.height == input) p.count += pop.count;
      }
      if (!stk.isEmpty()) count++;
      stk.push(p);
    }
    bw.write(count + "\n");
    bw.flush();
  }
}
