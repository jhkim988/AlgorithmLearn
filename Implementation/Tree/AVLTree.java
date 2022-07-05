public class AVLTree {
  private static class Node {
    int key, height;
    Node left, right;
    Node(int key) {
      this.key = key;
      this.height = 1;
    }
  }
  Node root = null;
  Node insert(Node node, int key) {
    if (node == null) return new Node(key);
    if (key < node.key) node.left = insert(node.left, key);
    else if (key > node.key) node.right = insert(node.right, key);
    else return node;

    node.height = Integer.max(node.left.height, node.right.height) + 1;
    int diff = node.left.height - node.right.height;

    // Left Left
    if (diff > 1 && key < node.left.key) return rotateRight(node);
    // Left Right
    if (diff > 1 && key > node.left.key) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    } 
    // Right Left
    if (diff < -1 && key < node.right.key) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    // Right Right
    if (diff < -1 && key > node.right.key) {
      return rotateLeft(node);
    }
    return node;
  }
  Node rotateRight(Node node) {
    
  }
  public static void main(String[] args) {

  }  
}
