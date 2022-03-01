import java.io.*;
import java.util.*;

public class BOJ1068 {
  static int[] input, tree;
  static boolean[] isLeaf;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] input = new int[n];
    boolean[] isLeaf = new boolean[n];
    Arrays.fill(isLeaf, true);
    int root = 0;
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
      if (input[i] == -1) {
        root = i;
        continue;
      }
      isLeaf[input[i]] = false;
    }
    int delete = Integer.parseInt(br.readLine());
    int[] tree = new int[n];
    for (int i = 0; i < n; i++) {
      if (!isLeaf[i]) continue;
      int node = i;
      while (node != -1) {
        tree[node]++;
        node = input[node];
      }
    }
    int answer = tree[root] - tree[delete];
    if (input[delete] >= 0 && tree[input[delete]] == tree[delete]) answer++;
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
