import java.io.*;
import java.util.*;

public class BOJ14395 {
  private static class Pair {
    char operator;
    int node;
    Pair (char operator, int node) {
      this.operator = operator;
      this.node = node;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());

    if (start == target) {
      bw.write("0\n");
      bw.flush();
      return;
    }

    Queue<Integer> que = new LinkedList<>();
    HashMap<Integer, Pair> edgeTo = new HashMap<>();
    que.add(start);
    while (!que.isEmpty()) {
      int crnt = que.poll();
      if (crnt == target) {
        break;
      }

      if ((crnt == 0 || crnt <= Integer.MAX_VALUE / crnt) && !edgeTo.containsKey(crnt * crnt)) {
        int result = crnt * crnt;
        que.add(result);
        edgeTo.put(result, new Pair('*', crnt));
      }
      if (crnt <= Integer.MAX_VALUE - crnt && !edgeTo.containsKey(crnt + crnt)) {
        int result = crnt + crnt;
        que.add(result);
        edgeTo.put(result, new Pair('+', crnt));
      }
      if (!edgeTo.containsKey(0)) {
        que.add(0);
        edgeTo.put(0, new Pair('-', crnt));
      }
      if (crnt != 0 && !edgeTo.containsKey(1)) {
        que.add(1);
        edgeTo.put(1, new Pair('/', crnt));
      }
    }
  
    if (!edgeTo.containsKey(target)) {
      bw.write("-1\n");
      bw.flush();
      return;
    }

    Stack<Character> path = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int ptr = target;
    while (ptr != start) {
      Pair node = edgeTo.get(ptr);
      path.push(node.operator);
      ptr = node.node;
    }

    while (!path.isEmpty()) {
      sb.append(path.pop());
    }
    sb.append('\n');

    bw.write(sb.toString());
    bw.flush();
  }
}
