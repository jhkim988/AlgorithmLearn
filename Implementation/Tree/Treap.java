public class Treap {
  private static class Node {
    int key;
    double weight;
    Node leftChild, rightChild;
    Node(int key) {
      this.key = key;
      weight = Math.random();
    }
  }
  // weight: MaxHeap
  // key: BinarySearchTree
  Node root;
  void add(int key) {
    root = add(root, new Node(key));
  }
  Node find(int key) {
    return null;
  }
  Node delete(int key) {
    return null;
  }
  Node add(Node ptr, Node node) {
    if (ptr == null) return node;
    if (ptr.weight > node.weight) {
      if (ptr.key > node.key) {
        ptr.leftChild = add(ptr.leftChild, node);
      } else if (ptr.key < node.key) {
        ptr.rightChild = add(ptr.rightChild, node);
      } else {
        // duplicate key:
      }
      return ptr;
    } else {
      if (ptr.key > node.key) {
        node.leftChild = ptr.leftChild;
        node.rightChild = ptr;
        ptr.leftChild = null;
      } else if (ptr.key < node.key) {
        node.rightChild = ptr.rightChild;
        node.leftChild = ptr;
        ptr.rightChild = null;
      } else {
        // duplicate key:
      }
      return node;
    }
  }
  public static void main(String[] args) {

  }
}
