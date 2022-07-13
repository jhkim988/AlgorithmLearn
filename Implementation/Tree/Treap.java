import java.io.*;

public class Treap {
  static final int INF = Integer.MAX_VALUE/2;
  private static class Node {
    int weight, key;
    Node leftChild, rightChild;
    Node(int key) {
      this.key = key;
      weight = (int) (INF*Math.random());
    }
  }
  // weight: MaxHeap
  // key: BinarySearchTree
  Node root;
  void add(int key) {
    add(root, new Node(key));
  }
  Node find(int key) {
    return null;
  }
  Node delete(int key) {
    return null;
  }
  void add(Node ptr, Node node) {
    if (ptr == null) {
      ptr = node;
      return;
    }
    if (ptr.weight > node.weight) {
      if (ptr.key > node.key) {
        add(ptr.leftChild, node);
      } else if (ptr.key < node.key) {
        add(ptr.rightChild, node);
      } else {
        // duplicate key:
      }
    } else {
      if (ptr.key > node.key) {

      } else if (ptr.key < node.key) {

      } else {

      }
    }
  }
}
