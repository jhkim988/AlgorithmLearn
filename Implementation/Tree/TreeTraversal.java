import java.io.IOException;

public class TreeTraversal {
  // Preorder: root - left child - right child
  // Inorder: left child - root - right child
  // Postorder: left child - right child - root
  private static class Tree_ArrayRepresentation<T> {
    T[] tree;
    T root;
    Tree_ArrayRepresentation(T[] tree) {
      this.tree = tree;
      this.root = tree[1];
    }
    void preorder(T[] tree, int node) {
      if (node >= tree.length || tree[node] == null) return;
      System.out.println(tree[node]); // do something
      preorder(tree, node*2);
      preorder(tree, node*2+1);
    }
    void inorder(T[] tree, int node) {
      if (node >= tree.length || tree[node] == null) return;
      inorder(tree, node*2);
      System.out.println(tree[node]); // do something
      inorder(tree, node*2+1);
    }
    void postorder(T[] tree, int node) {
      if (node >= tree.length || tree[node] == null) return;
      postorder(tree, node*2);
      postorder(tree, node*2+1);
      System.out.println(tree[node]); // do something
    }
  }
  private static class Tree_NodeRepresentation<T> {
    Node<T> root;
    void preorder(Node<T> crnt) {
      if (crnt == null) return;
      System.out.println(crnt.value);
      preorder(crnt.left);
      preorder(crnt.right);
    }
    void inorder(Node<T> crnt) {
      if (crnt == null) return;
      inorder(crnt.left);
      System.out.println(crnt.value);
      inorder(crnt.right);
    }
    void postorder(Node<T> crnt) {
      if (crnt == null) return;
      postorder(crnt.left);
      postorder(crnt.right);
      System.out.println(crnt.value);
    }
  }
  private static class Node<T> {
    T value;
    Node<T> left, right;
  }
  public static void main(String[] args) throws IOException {

  }
}