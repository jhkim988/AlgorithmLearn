import java.io.*;
import java.util.*;

public class BOJ2635 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Queue<Integer> que = new LinkedList<>();
    int maxLen = 2;
    for (int i = 1; i <= n; i++) {
      Queue<Integer> q = new LinkedList<>();
      int prev = n, crnt = i;
      q.add(prev); q.add(crnt);
      while (prev-crnt >= 0) {
        int tmp = crnt;
        crnt = prev-crnt;
        prev = tmp;
        q.add(crnt);
      } 
      if (maxLen < q.size()) {
        maxLen = q.size();
        que = q;
      }
    }
    bw.write(Integer.toString(maxLen));
    bw.newLine();
    while (!que.isEmpty()) {
      bw.write(Integer.toString(que.poll()));
      bw.write(' ');
    }
    bw.flush();
  }
}
