import edu.princeton.cs.algs4.StdOut;

// Q2. Check if a binary tree is a BST.
// Given a binary tree where each Node contains a key,
// determine whether it is a binary search tree.
// Use extra space proportional to the height of the tree.

// sol)
// Record min/max value.
// As we go down tree, Update the min/max values.
// If (Node x, min, max) state, then left child (x.left, min, x.val) and right child (x.right, x.val, max)
// check whether x.val is in range min ~ max.
public class Interview08_02_CheckBST {
	private static class Node<Key extends Comparable<Key>> {
		Key key;
		Node<Key> left;
		Node<Key> right;

		Node() {

		}

		Node(Key key) {
			this.key = key;
			left = null;
			right = null;
		}
	}

	private static class Btree<Key extends Comparable<Key>> {
		Node<Key> root;

		Btree() {
			root = new Node<Key>();
		}

		boolean isBST(Node<Key> x, Key min , Key max) { // using null pointer instead of min, max
			if (x == null) return true;
			StdOut.println(x.key);
			if(x.key.compareTo(min) <= 0 || x.key.compareTo(max) >= 0)
				return false;			
			return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
		}
		
		boolean isBST(Key min, Key max) {
			return isBST(root, min, max);
		}
		
		
		// wrong method
		boolean isBST(Node<Key> x) {
			// compare with only parent node. wrong.
			if(x == null) return false;
			if(x.left != null && x.left.key.compareTo(x.key) > 0)
				return false;
			if (x.right != null && x.right.key.compareTo(x.key) < 0)
				return false;
			return isBST(x.left) && isBST(x.right);
		}
	}



	public static void main(String[] args) {
		Btree<Integer> bt = new Btree<Integer>();
		bt.root.key = 3;
		bt.root.left = new Node<Integer>(1);
		bt.root.right = new Node<Integer>(5);
		
		bt.root.left.left = new Node<Integer>(0);
		bt.root.left.right = new Node<Integer>(2);
		bt.root.right.left = new Node<Integer>(4);

		StdOut.println(bt.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
}
