import java.util.*;

public class B_InteractiveSorting {
  static Scanner sc;
  static Queue<Character> que;
  private static class Node {
    char ch;
    Node left, right;
    Node(char ch) {
    	this.ch = ch;
    }
  }
  public static void main(String[] args) {
    sc = new Scanner(System.in);	
    int n = sc.nextInt();
    int q = sc.nextInt();
    sc.nextLine();
    char[] list = new char[n];
    for (int i = 0; i < list.length; i++) {
      list[i] = (char) ('A'+i);
    }
    for (int i = list.length-1; i > 0; i--) {
      int idx = (int) (Math.random()*(i+1));
      char tmp = list[idx];
      list[idx] = list[i];
      list[i] = tmp;
    }
    Node root = new Node(list[0]);
    for (int i = 1; i < n; i++) {
      dfs(root, (char) (list[i]));
    }
    que = new LinkedList<>();
    inorder(root);
    System.out.print("! ");
    while (!que.isEmpty()) {
      System.out.print(que.poll());
    }
    System.out.println();
  }
  static void dfs(Node node, char ch) {
  	System.out.println("? " + node.ch + " " + ch);
    char res = sc.nextLine().charAt(0);
    Node next = null;
    if (res == '>') {
      if (node.left == null) {
      	node.left = new Node(ch);
        return;
      } 
      next = node.left;
    } else {
    	if (node.right == null) {
        node.right = new Node(ch);
        return;
      } 
      next = node.right;
    }
    dfs(next, ch);
  }
  static void inorder(Node node) {
    if (node.left != null) inorder(node.left);
    que.add(node.ch);
    if (node.right != null) inorder(node.right);
  }
}