import java.util.*;

public class B_InteractiveSorting {
  static Scanner sc;
  static  int[][] cache;
  private static class AVLTree {
    private static class Node {
      char key;
      int height;
      Node left, right;
      Node(char key) {
        this.key = key;
        this.height = 1;
      }
    }
    Node root = null;
    int height(Node node) {
      if (node == null) return 0;
      return node.height;
    }
    Node insert(char key) {
      return root = insert(root, key);
    }
    Node insert(Node node, char key) {
      if (node == null) return new Node(key);
      if (compare(key, node.key) < 0) node.left = insert(node.left, key);
      else if (compare(key, node.key) > 0) node.right = insert(node.right, key);
      else return node;
  
      node.height = Integer.max(height(node.left), height(node.right)) + 1;
      int diff = height(node.left) - height(node.right);
  
      // Left Left
      if (diff > 1 && compare(key, node.left.key) < 0) return rotateRight(node);
      // Left Right
      if (diff > 1 && compare(key, node.left.key) > 0) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
      } 
      // Right Left
      if (diff < -1 && compare(key, node.right.key) < 0) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
      }
      // Right Right
      if (diff < -1 && compare(key, node.right.key) > 0) return rotateLeft(node);
      return node;
    }
    Node rotateRight(Node node) {
      Node left = node.left;
      Node tmp = left.right;
      left.right = node;
      node.left = tmp;
      node.height = Integer.max(height(node.left), height(node.right)) + 1;
      left.height = Integer.max(height(left.left), height(left.right)) + 1;
      return left;
    }
    Node rotateLeft(Node node) {
      Node right = node.right;
      Node tmp = right.left;
      right.left = node;
      node.right = tmp;
      node.height = Integer.max(height(node.left), height(node.right)) + 1;
      right.height = Integer.max(height(right.left), height(right.right)) + 1;
      return right;
    }
    Queue<Character> inorder() {
      Queue<Character> que = new LinkedList<>();
      dfs(root, que);
      return que;
    }
    void dfs(Node node, Queue<Character> que) {
      if (node == null) return;
      dfs(node.left, que);
      que.add(node.key);
      dfs(node.right, que);
    }
  }
  public static void main(String[] args) {
    sc = new Scanner(System.in);	
    int n = sc.nextInt();
    int q = sc.nextInt();
    sc.nextLine();
    cache = new int[n][n];
    if (n == 5) {
      char a = 'A', b = 'B', c = 'C', d = 'D', e = 'E';
      if (compare(a, b) < 0) a = swap(b, b=a);
      if (compare(c, d) < 0) c = swap(d, d=c);
      if (compare(a, c) < 0) a = swap(c, c=a);
      if (compare(c, e) < 0) {
        if (compare(d, e) < 0) {

        } else {

        }
      } else {
        if (compare(a, e) < 0) {

        } else {

        }
      }
    } else {
      AVLTree avl = new AVLTree();
      for (int i = 0; i < n; i++) {
        avl.insert((char) ('A'+i));
      }
      System.out.print("! ");
      for (char ch : avl.inorder()) {
        System.out.print(ch);
      }
    }
  }
  static int compare(char c1, char c2) {
    System.out.println("call: " + c1 + ", " + c2);
    if (cache[c1-'A'][c2-'A'] != 0) return cache[c1-'A'][c2-'A'];
    System.out.println("? " + c1 + " " + c2);
    int ret = sc.nextLine().charAt(0) == '<' ? -1 : 1;
    cache[c1-'A'][c2-'A'] = ret;
    cache[c2-'A'][c1-'A'] = -ret;
    return ret;
  }
  static char swap(char a, char b) {
    return a;
  }
}