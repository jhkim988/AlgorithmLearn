import java.util.*;

public class BinarySearchTree {
  private static class Node {
    int key;
    Node leftChild, rightChild;
    Node(int key) {
      this.key = key;
    }
  }
  Node root;
  void add(int key) {
    root = add(root, key);
  }
  private Node add(Node ptr, int key) {
    if (ptr == null) return ptr = new Node(key);
    
    if (ptr.key < key) {
      ptr.rightChild = add(ptr.rightChild, key);
    } else if (ptr.key > key) {
      ptr.leftChild = add(ptr.leftChild, key);
    } else {
      // duplicate key:
    }
    return ptr;
  }
  Node get(int key) {
    return get(root, key);
  }
  private Node get(Node ptr, int key) {
    if (ptr == null) return null;
    if (ptr.key < key) return get(ptr.rightChild, key);
    else if (ptr.key > key) return get(ptr.leftChild, key);
    else return ptr;
  }
  Node delete(int key) {
    return root = delete(root, key);
  }
  private Node delete(Node ptr, int key) {
    if (ptr == null) return ptr;
    if (ptr.key < key) return ptr.rightChild = delete(ptr.rightChild, key);
    else if (ptr.key > key) return ptr.leftChild = delete(ptr.leftChild, key);
    if (ptr.leftChild == null) return ptr.rightChild;
    if (ptr.rightChild == null) return ptr.leftChild;
    ptr.key = minValue(ptr.rightChild);
    ptr.rightChild = delete(ptr.rightChild, ptr.key);
    return ptr;
  }
  private int minValue(Node ptr) {
    return ptr.leftChild == null ? ptr.key : minValue(ptr.leftChild);
  }
  Queue<Integer> inorder() {
    Queue<Integer> que = new LinkedList<>();
    inorder(root, que);
    return que;
  }
  void inorder(Node ptr, Queue<Integer> que) { 
    if (ptr == null) return;
    inorder(ptr.leftChild, que);
    que.add(ptr.key);
    inorder(ptr.rightChild, que);
  }
  ArrayList<Queue<Node>> levelOrder() {
    ArrayList<Queue<Node>> ret = new ArrayList<>();
    if (root == null) return ret;
    ret.add(new LinkedList<>());
    ret.get(0).add(root);
    int level = 0;
    while (ret.size() == level+1) {
      Queue<Node> que = ret.get(level);
      Queue<Node> add = new LinkedList<>();
      for (Node node : que) {
        if (node.leftChild != null) add.add(node.leftChild);
        if (node.rightChild != null) add.add(node.rightChild);
      }
      if (add.size() != 0) ret.add(add);
      level++;
    }
    return ret;
  }
  public static void main(String[] args) {
    int n = 10;
    int[] test = new int[n];
    for (int i = 0; i < n; i++) test[i] = i;
    shuffle(test);
    BinarySearchTree bst = new BinarySearchTree();
    for (int i = 0; i < n; i++) {
      bst.add(test[i]);
      System.out.println("add: " + test[i]);
    }
    levelPrint(bst);

    bst.delete(bst.root.key);
    levelPrint(bst);
  } 
  static void shuffle(int[] arr) {
    for (int i = arr.length-1; i > 0; i--) {
      int idx = (int) (Math.random()*(i+1));
      arr[idx] = swap(arr[i], arr[i]=arr[idx]);
    }
  }
  static void levelPrint(BinarySearchTree bst) {
    ArrayList<Queue<Node>> levelOrder = bst.levelOrder();
    for (int i = 0; i < levelOrder.size(); i++) {
      System.out.print(i + ":");
      for (Node node : levelOrder.get(i)) {
        System.out.print(" " + node.key);
      }
      System.out.println();
    }
  }
  static <T> T swap(T a, T b) {
    return a;
  }
}
