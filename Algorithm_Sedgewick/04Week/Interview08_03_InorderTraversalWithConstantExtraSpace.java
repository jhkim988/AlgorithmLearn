import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Q3. Inorder traversal with constant extra space.
// Design an algorithm to perform an inorder traversal of a binary search tree
// using only a constant amount of extra space.

// sol)
// Recursively write code.
// For some node x, inorder(x.left); do something at x; inorder(x.right);
public class Interview08_03_InorderTraversalWithConstantExtraSpace {
	private static class Node<Key extends Comparable<Key>, Value> {
		Key key;
		Value val;
		Node<Key, Value> left;
		Node<Key, Value> right;
		Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}
	private static class BST<Key extends Comparable<Key>, Value> {
		Node<Key, Value> root;
		
		BST() {
			root = null;
		}
		
		void put(Key key, Value val) {
			root = put(root, key, val);
		}
		private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {
			if (x == null) return new Node<Key, Value>(key, val);
			int cmp = key.compareTo(x.key);
			if (cmp > 0) x.right = put(x.right, key, val);
			else if (cmp < 0) x.left = put(x.left, key, val);
			else x.val = val;
			return x;
		}
		
	}
	
	static void inorder(BST bst) {
		inorder(bst.root);
	}
	static void inorder(Node x) {
		if(x.left != null) inorder(x.left);
		StdOut.println(x.val + "\tDo Something");
		if(x.right != null)inorder(x.right);
	}

	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<>();
		int N = 20;
		for(int i = 0; i < N; i ++) {
			int rand = StdRandom.uniform(N);
			bst.put(rand, "put : " + rand);
		}
		inorder(bst);
	}
}
