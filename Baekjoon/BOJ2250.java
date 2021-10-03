import java.io.*;
import java.util.*;

public class BOJ2250 {
  // 1. DFS: Find size of subtree
  // 2. BFS: Find position of root
  // 3. BFS: Find max width and its level.
  private static class Node {
    int node;
    Node leftChild;
    Node rightChild;
    int size = 1; // size of subtree w.r.t subroot = node
    int position = 0;
    int lev = 1;
    Node(int node, Node leftChild, Node rightChild) {
      this.node = node;
      this.leftChild = leftChild;
      this.rightChild = rightChild;
    }
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(node).append(' ');
      if (leftChild == null) {
        sb.append(-1).append(' ');
      } else {
        sb.append(leftChild.node).append(' ');
      }
      if (rightChild == null) {
        sb.append(-1);
      } else {
        sb.append(rightChild.node);
      }
      return sb.toString();
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // Tree Initialized:
    int numNode = Integer.parseInt(br.readLine());
    Node[] tree = new Node[numNode + 1];
    int[] parent = new int[numNode + 1];
    for (int i = 1; i <= numNode; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int nodeId = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      if (left != -1) {
        parent[left] = nodeId;
      }
      if (right != -1) {
        parent[right] = nodeId;
      }

      if (left != -1 && tree[left] == null) {
        tree[left] = new Node(left, null, null);
      }
      if (right != -1 && tree[right] == null) {
        tree[right] = new Node(right, null, null);
      }

      Node leftNode = left == -1 ? null : tree[left];
      Node rightNode = right == -1 ? null : tree[right];
      if (tree[nodeId] == null) {
        tree[nodeId] = new Node(nodeId, leftNode, rightNode);
      } else {
        tree[nodeId].leftChild = leftNode;
        tree[nodeId].rightChild = rightNode;
      }
    }

    // Find Root:
    int root = 1;
    while (parent[root] != 0) {
      root = parent[root];
    }

    // check: Tree initialize
    // for (int i = 1; i <= numNode; i++) {
    //   System.out.println(tree[i]);
    //   System.out.println(parent[i]);
    // }

    // DFS: Find size of all subtrees.
    boolean[] marked = new boolean[numNode + 1];
    Stack<Node> stk = new Stack<>();
    stk.push(tree[root]);
    while (!stk.isEmpty()) {
      Node crnt = stk.peek();
      marked[crnt.node] = true;
      if (crnt.leftChild != null && !marked[crnt.leftChild.node]) {
        stk.push(crnt.leftChild);
      }
      if (crnt.rightChild != null && !marked[crnt.rightChild.node]) {
        stk.push(crnt.rightChild);
      }
      if ((crnt.leftChild == null || marked[crnt.leftChild.node]) && (crnt.rightChild == null || marked[crnt.rightChild.node])) {
        stk.pop();
        if (parent[crnt.node] != 0) {
          tree[parent[crnt.node]].size += crnt.size;
        }
      }
    }

    // for (int i = 1; i <= numNode; i++) {
    //   System.out.println(i + ": " + tree[i].size);
    // }
  
    // Find position of all node:
    tree[root].position = tree[root].leftChild == null ? 1 : tree[tree[root].leftChild.node].size + 1;
    Queue<Node> que = new LinkedList<>();
    que.add(tree[root]);
    while (!que.isEmpty()) {
      Node crnt = que.poll(); // root
      if (crnt.leftChild != null) {
        if (crnt.leftChild.rightChild == null) {
          crnt.leftChild.position = crnt.position - 1;
        } else {
          crnt.leftChild.position = crnt.position - crnt.leftChild.rightChild.size - 1;
        }
        que.add(crnt.leftChild);
      }
      if (crnt.rightChild != null) {
        if (crnt.rightChild.leftChild == null) {
          crnt.rightChild.position = crnt.position + 1;
        } else {
          crnt.rightChild.position = crnt.position + crnt.rightChild.leftChild.size + 1;
        }
        que.add(crnt.rightChild);
      }
    }

    // for (int i = 1; i <= numNode; i++) {
    //   System.out.println(i + ": " + tree[i].position);
    // }
  
    // BFS, Find max width:
    int maxLev = 1;
    int max = 1;
    que.add(tree[root]);
    while (!que.isEmpty()) {
      Node crnt = que.peek();
      int minIdx = crnt.position;
      int maxIdx = crnt.position;
      while (!que.isEmpty() && que.peek().lev == crnt.lev) {
        crnt = que.poll();
        int tmp = crnt.position;
        if (tmp < minIdx) {
          minIdx = tmp;
        }
        if (maxIdx < tmp) {
          maxIdx = tmp;
        }
        if (crnt.leftChild != null) {
          crnt.leftChild.lev = crnt.lev + 1;
          que.add(crnt.leftChild);
        }
        if (crnt.rightChild != null) {
          crnt.rightChild.lev = crnt.lev + 1;
          que.add(crnt.rightChild);
        }
      }
      int width = maxIdx - minIdx + 1; 
      if (max < width) {
        max = width;
        maxLev = crnt.lev;
      }     
    }

    bw.write(maxLev + " " + max + "\n");
    bw.flush();
  }
}
