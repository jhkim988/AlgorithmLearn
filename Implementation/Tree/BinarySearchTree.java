// Naive Implementation:
public class BST {
  private static class Node {
    int key;
    Node parent, leftChild, rightChild;
  }
  Node root;
  void add(int key) {
    add(root, new Node(key));
  }
  void add(Node ptr, Node node) {
    if (ptr == null) {
      ptr = node;
      return;
    }
    if (ptr.key < node.key) {
      add(ptr.rightChild, node);
    } else if (ptr.key > node.key) {
      add(ptr.leftChild, node);
    } else {
      // duplicate key:
    }
  }
  Node get(int key) {
    return get(root, key);
  }
  Node get(Node ptr, int key) {
    if (ptr == null) return null;
    if (ptr.key < key) return get(ptr.rightChild, key);
    else if (ptr.key > key) return get(ptr.leftChild, key);
    else return ptr;
  }
  Node delete(int key) {
    return delete(ptr, key);
  }
  Node delete(Node ptr, int key) {
    if (ptr == null) return ptr;
    if (ptr.key < key) return delete(ptr.rightChild, key);
    else if (ptr.key > key) return delete(ptr.leftChild, key);
    if (ptr.leftChild == null) return ptr.rightChild;
    if (ptr.rightChild == null) return ptr.leftChild;
    ptr.key = minValue(ptr.right);
    ptr.right = delete(ptr.right, ptr.key);
    return ptr;
  }
  int minValue(Node ptr) {
    return ptr.leftChild == null ? ptr.key : minValue(ptr.leftChild).key;
  }
  public static void main(String[] args) {

  }  
}
