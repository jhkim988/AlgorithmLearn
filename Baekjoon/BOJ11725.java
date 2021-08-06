import java.io.*;
import java.util.*;

public class BOJ11725 {
  private static class Pair {
    int x;
    int y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int num = Integer.parseInt(br.readLine());
    int[] tree = new int[num + 1];

    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= num; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < num; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      graph.get(x).add(y);
      graph.get(y).add(x);
    } 

    bfs(graph, tree);

    for (int i = 2; i <= num; i++) {
      bw.write(tree[i] + "\n");
    }
    bw.flush();
  }
  static void bfs(ArrayList<Queue<Integer>> graph, int[] tree) {
    Queue<Integer> que = new LinkedList<>();
    que.add(1);

    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int next : graph.get(crnt)) {
        if (tree[next] == 0) {
          tree[next] = crnt;
          que.add(next);
        }
      }
    }
  }
  static void sol1() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int num = Integer.parseInt(br.readLine());
    int[] tree = new int[num + 1];
    Stack<Pair> input = new Stack<>();
    HashSet<Integer> connect = new HashSet<>(); 
    connect.add(1);    
    for (int i = 1; i < num; i++) {
      st = new StringTokenizer(br.readLine());
      input.push(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
      while (!input.empty()) {
        Pair top = input.peek();
        if (connect.contains(top.x) && !connect.contains(top.y)) {
          tree[top.y] = top.x;
          connect.add(top.y);
          input.pop();
        } else if (connect.contains(top.y) && !connect.contains(top.x)) {
          tree[top.x] = top.y;
          connect.add(top.x);
          input.pop();
        } else if (connect.contains(top.x) && connect.contains(top.y)) {
          input.pop();
        }
      }
    }

    for (int i = 2; i <= num; i++) {
      bw.write(tree[i] + "\n");
    }
    bw.flush();
  }
}
