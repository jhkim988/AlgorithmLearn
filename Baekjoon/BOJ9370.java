import java.io.*;
import java.util.*;

public class BOJ9370 {
  static final int INF = 50_000_001;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int numV;
  static int numE;
  static int numTarget;
  static int start;
  static int pass1;
  static int pass2;
  static ArrayList<Queue<Edge>> graph;
  static int[] target;

  private static class Edge implements Comparable<Edge> {
    int end;
    int weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
      return this.weight - other.weight;
    }
  }

  public static void main(String[] args) throws IOException {


    int numTest = Integer.parseInt(br.readLine());
    
    for (int i = 0; i < numTest; i++) {
      dataInput();
      ArrayList<Integer> result = new ArrayList<>();
      // start -> g, h
      int gpath = solve(start, pass1);
      int hpath = solve(start, pass2);
      
      // g -> h
      int ghpath = solve(pass1, pass2);      
      for (int t : target) {
        // start -> target
        int path = solve(start, t);

        // g -> target
        int pathg = solve(pass1, t);
        int pathh = solve(pass2, t);

        int passSGHT = gpath + ghpath + pathh;
        int passSHGT = hpath + ghpath + pathg;
        if (Math.min(passSGHT, passSHGT) <= INF) {
          if (path == Math.min(passSGHT, passSHGT)) {
            result.add(t);
          }
        }
      }
      Collections.sort(result);
      for (int j = 0; j < result.size(); j++) {
        bw.write(result.get(j) + " ");
      }
      bw.write("\n");
    }
    bw.flush();
  }
  static void dataInput() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    numV = Integer.parseInt(st.nextToken());
    numE = Integer.parseInt(st.nextToken());
    numTarget = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    start = Integer.parseInt(st.nextToken());
    pass1 = Integer.parseInt(st.nextToken());
    pass2 = Integer.parseInt(st.nextToken());

    // graph initialize
    graph = new ArrayList<>();
    for (int i = 0; i <= numV; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      // a -> b, weight: c

      graph.get(a).add(new Edge(b, c));
      graph.get(b).add(new Edge(a, c));
    }

    target = new int[numTarget];
    for (int i = 0; i < numTarget; i++) {
      target[i] = Integer.parseInt(br.readLine());
    }
  }
  static int solve(int start, int targetNode) {
    PriorityQueue<Edge> que = new PriorityQueue<>();
    int[] dist = new int[numV + 1];
    Arrays.fill(dist, INF);
    dist[start] = 0;
    Edge startNode = new Edge(start, 0);
    que.add(startNode);

    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      if (crnt.end == targetNode) {
        return dist[targetNode];
      }
      for (Edge next : graph.get(crnt.end)) {
        if (dist[next.end] > dist[crnt.end] + next.weight) {
          dist[next.end] = dist[crnt.end] + next.weight;
          que.add(new Edge(next.end, dist[next.end]));
        }
      }
    }
    return INF;
  }
}
