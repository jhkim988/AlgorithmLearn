import java.io.*;
import java.util.*;

public class BOJ2213 {
  static int[] weight;
  static ArrayList<Queue<Integer>> graph;
  static ArrayList<TreeSet<Integer>> maxIndependentSetArr;
  static ArrayList<Integer> sum;
  static boolean[] marked;
  static int root;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    weight = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    maxIndependentSetArr = new ArrayList<>();
    sum = new ArrayList<>();
    marked = new boolean[N + 1];
    for (int i = 0; i <= N; i++) {
      maxIndependentSetArr.add(new TreeSet<>());
      sum.add(0);
    }
    root = 1;
    int max = maxIndependentSet(root, -1);
    bw.write(max + "\n");
    for (int node : maxIndependentSetArr.get(1)) {
      bw.write(node + " ");
    }
    bw.write("\n");
    bw.flush();
  }
  static int maxIndependentSet(int crnt, int prev) {
    if (marked[crnt]) {
      return sum.get(crnt);
    }
    marked[crnt] = true;
    if (graph.get(crnt).size() == 1 && crnt != root) {
      maxIndependentSetArr.get(crnt).add(crnt);
      sum.set(crnt, weight[crnt]);
      return weight[crnt];
    }
    for (int next : graph.get(crnt)) {
      if (next == prev) continue;
      maxIndependentSet(next, crnt);
    }
    int nextSum = 0;
    int nextNextSum = weight[crnt];
    for (int next : graph.get(crnt)) {
      if(next == prev) continue;
      nextSum += sum.get(next);
      for (int nextNext : graph.get(next)) {
        if (nextNext == crnt) continue;
        nextNextSum += sum.get(nextNext);
      }
    }
    if (nextSum < nextNextSum) {
      for (int next : graph.get(crnt)) {
        if (next == prev) continue;
        for (int nextNext : graph.get(next)) {
          if (nextNext == crnt) continue;
          for (int setEl : maxIndependentSetArr.get(nextNext)) {
            maxIndependentSetArr.get(crnt).add(setEl);
          }          
        }
      }
      maxIndependentSetArr.get(crnt).add(crnt);
      sum.set(crnt, nextNextSum);
      return nextNextSum;
    } else {
      for (int next : graph.get(crnt)) {
        if (next == prev) continue;
        for (int setEl : maxIndependentSetArr.get(next)) {
          maxIndependentSetArr.get(crnt).add(setEl);
        }  
      }
      sum.set(crnt, nextSum);
      return nextSum;
    }
  }
}
