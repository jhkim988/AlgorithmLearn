import java.io.*;
import java.util.*;

public class BOJ2533 {
  static int N;
  static int root = 1;
  static ArrayList<Queue<Integer>> graph;
  static boolean[] marked;
  static ArrayList<Integer> minEarlyAdopterArr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      graph.get(start).add(end);
      graph.get(end).add(start);
    }
    
    marked = new boolean[N + 1];
    minEarlyAdopterArr = new ArrayList<>(); // minEarlyAdopterArr(i): minimum Early Adopter of subtree(root = i)
    for (int i = 0; i <= N; i++) {
      minEarlyAdopterArr.add(N + 1);
    }
    minEarlyAdopter(root, -1);
    bw.write(Math.abs(minEarlyAdopterArr.get(root)) + "\n");
    bw.flush();

    for (int i = 1; i <= N; i++) {
      System.out.print(minEarlyAdopterArr.get(i) + " ");
    }
  }
  static int minEarlyAdopter(int crnt, int prev) {
    // contain root: positive, not contain root: negative
    if (marked[crnt]) {
      return minEarlyAdopterArr.get(crnt);
    }
    if (graph.get(crnt).size() == 1 && crnt != root) {
      minEarlyAdopterArr.set(crnt, 1);
      return 1;
    }
    
    marked[crnt] = true;
    for (int next : graph.get(crnt)) {
      if (crnt == prev) continue;
      minEarlyAdopter(next, crnt);
    }

    int minWithoutRoot = 0;
    boolean flag = false; // root contain?
    for (int next : graph.get(crnt)) {
      if (next == prev) continue;
      int summand = minEarlyAdopterArr.get(next);
      if (summand < 0) {
        flag = true;
        minWithoutRoot -= summand;
      } else {
        minWithoutRoot += summand;
      }
    }
    if (flag) {
      minWithoutRoot++;
    }
    // System.out.println("crnt: " + crnt + ", " + minWithRoot + ", " + minWithoutRoot);
    int result = flag ? minWithoutRoot : -minWithoutRoot;
    minEarlyAdopterArr.set(crnt, result);
    return result;
  }
}
