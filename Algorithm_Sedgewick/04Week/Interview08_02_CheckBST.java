import edu.princeton.cs.algs4.StdOut;

// Q2. Check if a binary tree is a BST.
// Given a binary tree where each Node contains a key,
// determine whether it is a binary search tree.
// Use extra space proportional to the height of the tree.

public class Interview08_02_CheckBST {
	private static class Node<Key extends Comparable<Key>> {
		Key key;
		Node<Key> left;
		Node<Key> right;
		Node(){
			
		}
		Node(Key key) {
			this.key = key;
		}
	}
	
	private static class Btree<Key extends Comparable<Key>> {
		Node root;
		Btree() {
			root = new Node();
		}
		boolean isBST(Node x) {
			if (x == null) return true;
			if (!isBST(x.left) || !isBST(x.right)) return false;
			if (x.left != null && x.left.key.compareTo(x.key) > 0) return false;
			if (x.right != null && x.right.key.compareTo(x.key) < 0) return false;
			return true;
		}
	}
	
	private static boolean isBST(Btree bt) {
		return bt.isBST(bt.root);
	}
	
	public static void main(String [] args) {
		Btree<Integer> bt = new Btree<Integer>();
		bt.root.key = 3;
		bt.root.left = new Node<Integer>(1);
		bt.root.right = new Node<Integer>(5);
		bt.root.left.left = new Node<Integer>(0);
		bt.root.left.right = new Node<Integer>(2);
		bt.root.right.left = new Node<Integer>(4);
		
		StdOut.println(isBST(bt));
	}
}
