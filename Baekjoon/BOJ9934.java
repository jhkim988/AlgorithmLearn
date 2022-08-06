import java.io.*;
import java.util.*;

public class BOJ9934 {
  static StringTokenizer st;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] tree = new int[1<<n];
    
    st = new StringTokenizer(br.readLine());
    inorder(tree, 1);

    Queue<Integer> que = new LinkedList<>();
    que.add(1);
    while (!que.isEmpty()) {
      Queue<Integer> nextLevel = new LinkedList<>();
      while (!que.isEmpty()) {
        int v = que.poll();
        bw.write(Integer.toString(tree[v]));
        bw.write(' ');
        if (v<<1 < tree.length) {
          nextLevel.add(v<<1);
          nextLevel.add(v<<1|1);
        }
      }
      bw.newLine();
      que = nextLevel;
    }
    bw.flush();
  }
  static void inorder(int[] tree, int node) {
    if (node >= tree.length) return;
    inorder(tree, node<<1);
    tree[node] = Integer.parseInt(st.nextToken());
    inorder(tree, node<<1|1);
  }
}
