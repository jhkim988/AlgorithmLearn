import java.io.*;
import java.util.*;

public class BOJ1948 {
  private static class Edge {
    int end, time;
    Edge(int end, int time) {
      this.end = end;
      this.time = time;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int v = Integer.parseInt(br.readLine());
    int e = Integer.parseInt(br.readLine());
    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    ArrayList<Queue<Edge>> reverse = new ArrayList<>();
    for (int i = 0; i <= v; i++) {
      graph.add(new LinkedList<>());
      reverse.add(new LinkedList<>());
    }
    for (int i = 0; i < e; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Edge(end, time));
      reverse.get(end).add(new Edge(start, time));
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    
    int[] cost = new int[v+1];
    Queue<Edge> que = new LinkedList<>();
    que.add(new Edge(start, 0));
    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      if (cost[crnt.end] < crnt.time) continue;
      for (Edge edge : graph.get(crnt.end)) {
        if (cost[edge.end] < cost[crnt.end] + edge.time) {
          cost[edge.end] = cost[crnt.end] + edge.time;
          que.add(new Edge(edge.end, cost[edge.end]));
        }
      }
    }
    int longestTime = cost[end];
    int numEdge = 0;
    boolean[] visit = new boolean[v+1];
    visit[end] = true;
    que.add(new Edge(end, 0));
    while (!que.isEmpty()) {
      Edge crnt = que.poll();
      for (Edge edge : reverse.get(crnt.end)) {
        if (crnt.time + edge.time + cost[edge.end] != longestTime) continue;
        if (!visit[edge.end]) que.add(new Edge(edge.end, edge.time + crnt.time));
        visit[edge.end] = true;
        numEdge++;
      }
    }
    bw.write(Integer.toString(longestTime));
    bw.newLine();
    bw.write(Integer.toString(numEdge));
    bw.flush();
  }
}
