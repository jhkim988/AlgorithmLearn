import java.io.*;
import java.util.*;

public class BOJ16928 {
  private static class Pair {
    int node;
    int depth;
    Pair(int node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int numLadder = Integer.parseInt(st.nextToken());
    int numSnake = Integer.parseInt(st.nextToken());
    HashMap<Integer, Integer> shortcut = new HashMap<>();
    for (int i = 0; i < numLadder + numSnake; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      shortcut.put(start, end);
    } 

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= 100; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < 100; i++) {
      for (int j = 1; j <= 6; j++) {
        if (i + j <= 100) {
          if (shortcut.containsKey(i + j)) {
            graph.get(i).add(shortcut.get(i + j));
          } else {
            graph.get(i).add(i + j);
          }          
        }        
      }
    }
    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(1, 0));
    boolean[] marked = new boolean[101];
    marked[1] = true;

    while(!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.node == 100) {
        bw.write(crnt.depth + "\n");
        break;
      }
      for (int next : graph.get(crnt.node)) {
        if (!marked[next]) {
          que.add(new Pair(next, crnt.depth + 1));
          marked[next] = true;
        }
      }
    }
    bw.flush();
  }
}
