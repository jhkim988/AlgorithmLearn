import java.io.*;
import java.util.*;

public class BOJ9019 {
  private static class Pair {
    int node;
    StringBuilder hist;
    Pair(int node, StringBuilder hist) {
      this.node = node;
      this.hist = hist;
    }
    Pair(int node, StringBuilder hist, int code) {
      this.node = node;
      this.hist = hist;
      if (code == 0) {
        this.hist.append("D");
      } else if (code == 1) {
        this.hist.append("S");
      } else if (code == 2) {
        this.hist.append("L");
      } else if (code == 3) {
        this.hist.append("R");
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int target = Integer.parseInt(st.nextToken());

      boolean[] marked = new boolean[10000];
      Queue<Pair> que = new LinkedList<>();
      que.add(new Pair(start, new StringBuilder()));
      marked[start] = true;

      while(!que.isEmpty()) {
        Pair crnt = que.poll();
        if (crnt.node == target) {
          bw.write(crnt.hist + "\n");
          bw.flush();
          break;
        }
        int[] search = dslr(crnt.node);
        for (int i = 0; i < 4; i++) {
          if (!marked[search[i]]) {
            marked[search[i]] = true;
            que.add(new Pair(search[i], new StringBuilder(crnt.hist), i));
          }
        }
      }
    } 
  }
  static int[] dslr(int n) {
    int[] result = new int[4];
    result[0] = (2*n % 10000);
    result[1] = (n == 0) ? 9999 : n - 1;
    int first = n;
    int second = n % 10;
    for (int i = 0; i < 3; i++) {
      first /= 10;
    }
    result[2] = n * 10 - first * 10000 + first;
    result[3] = n / 10 + second * 1000;
    return result;
  }
}
