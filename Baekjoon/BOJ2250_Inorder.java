import java.io.*;
import java.util.*;

public class BOJ2250_Inorder {
  // DFS: Inorder traversal, Find position
  // BFS: Find max width and its level
  private static class Node {
    int id;
    Node left;
    Node right;
    int position = 1;
    int level = 1;
    Node (int id, Node left, Node right) {
      this.id = id;
      this.left = left;
      this.right = right;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numNode = Integer.parseInt(br.readLine());
    Node[] tree = new Node[numNode + 1];
    int[] parent = new int[numNode + 1];
    for (int i = 0; i <= numNode; i++) {
      tree[i] = new Node(i, null, null);
    } 
    for (int i = 0; i < numNode; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int root = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      tree[root].id = root;
      tree[root].left = left == -1 ? null : tree[left];
      tree[root].right = right == -1 ? null : tree[right];
      if (left != -1) {
        parent[left] = root;
      }
      if (right != -1) {
        parent[right] = root;
      }
    }

    int root = 1;
    while (parent[root] != 0) {
      root = parent[root];
    }

    // Inorder:
    int ptr = 1;
    Stack<Node> stk = new Stack<>();
    boolean[] marked = new boolean[numNode + 1]; 
    stk.push(tree[root]);
    while (!stk.isEmpty()) {
      Node crnt = stk.pop();
      if (crnt.left == null || marked[crnt.left.id]) {
        marked[crnt.id] = true;
        crnt.position = ptr++;
        if (crnt.right != null) {
          stk.push(crnt.right);
        }
      } else {
        stk.push(crnt);
        stk.push(crnt.left);
      }
    }

    // BFS:
    Queue<Node> que = new LinkedList<>();
    que.add(tree[root]);
    int maxWidth = 1;
    int maxLevel = 1;
    while (!que.isEmpty()) {
      Node crnt = que.peek();
      int maxIdx = crnt.position;
      int minIdx = crnt.position;
      while (!que.isEmpty() && crnt.level == que.peek().level) {
        crnt = que.poll();
        if (crnt.position < minIdx) {
          minIdx = crnt.position;
        }
        if (maxIdx < crnt.position) {
          maxIdx = crnt.position;
        }
        if (crnt.left != null) {
          crnt.left.level = crnt.level + 1;
          que.add(crnt.left);
        }
        if (crnt.right != null) {
          crnt.right.level = crnt.level + 1;
          que.add(crnt.right);
        }
      }

      int width = maxIdx - minIdx + 1;
      if (maxWidth < width) {
        maxWidth = width;
        maxLevel = crnt.level;
      }
    }

    bw.write(maxLevel + " " + maxWidth + "\n");
    bw.flush();
  }
}
