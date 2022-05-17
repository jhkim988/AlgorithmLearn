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
    for (int i = 0; i <= v; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < e; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Edge(end, time));
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
  }
}
