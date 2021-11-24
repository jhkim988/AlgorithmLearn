import java.io.*;
import java.util.*;

public class BOJ16936 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    long[] input = new long[N];
    for (int i = 0; i < N; i++) {
      input[i] = Long.parseLong(st.nextToken());
    }
    Arrays.sort(input);
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    graphInit(graph, N, input);
    Queue<Integer> topSort = topologicalSort(graph);
    String answer = print(topSort, input);
    bw.write(answer);
    bw.flush();
  }
  static void graphInit(ArrayList<Queue<Integer>> graph, int N, long[] input) {
    HashSet<Long> inputSet = new HashSet<>();
    for (int i = 0; i < N; i++) {
      inputSet.add(input[i]);
    }
    for (int i = 0; i < N; i++) {
      graph.add(new LinkedList<>());
    }    
    for (int i = 0; i < N; i++) {
      if (inputSet.contains(input[i] * 2)) {
        int idx = Arrays.binarySearch(input, input[i] * 2);
        graph.get(i).add(idx);
      }
      if (input[i] % 3 == 0 && inputSet.contains(input[i] / 3)) {
        int idx = Arrays.binarySearch(input, input[i] / 3);
        graph.get(i).add(idx);
      }
    }
  }
  static int[] numIncomming(ArrayList<Queue<Integer>> graph) {
    int size = graph.size();
    int[] result = new int[size];
    for (int i = 0; i < size; i++) {
      Queue<Integer> edge = graph.get(i);
      for (int idx : edge) {
        result[idx]++;
      }
    }
    return result;
  }
  static Queue<Integer> topologicalSort(ArrayList<Queue<Integer>> graph) {
    // Kahn's Algorithm:
    int size = graph.size();
    Queue<Integer> result = new LinkedList<>();
    boolean[] marked = new boolean[size];
    int[] numIncomming = numIncomming(graph);
    Queue<Integer> que = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      if (numIncomming[i] == 0) que.add(i);
    }
    while (!que.isEmpty()) {
      int node = que.poll();
      marked[node] = true;
      result.add(node);
      Queue<Integer> edge = graph.get(node);
      for (int idx : edge) {
        numIncomming[idx]--;
      }
      for (int i = 0; i < size; i++) {
        if (!marked[i] && numIncomming[i] == 0) que.add(i);
      }
    }
    return result;
  }
  static String print(Queue<Integer> que, long[] input) {
    StringBuilder sb = new StringBuilder();
    sb.append(input[que.poll()]);
    while (!que.isEmpty()) {
      sb.append(' ').append(input[que.poll()]);
    }
    sb.append('\n');
    return sb.toString();
  }
}