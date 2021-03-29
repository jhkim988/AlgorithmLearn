
public class RedBlackTree<Key extends Comparable<Key>, Value> {
	// left-leaning Red-Black Tree
	// In 2-3 tree, 3-node split 2 2-nodes with linked "Red link"
	// small thing among 2-nodes -> left of red link
	// large thing among 2-nodes -> right of red link
	
	// 1. No nodes has two red links connected to it.
	// 2. Every path from root to null link has # of black links.
	// (2-3 tree is balanced, same height. Therefore # of black links are same.)
	// 3. Red Link lean left.

	// 1-1 correspondence between 2-3 and LLRB(Left Leaning Red Black Tree)
	// Ignore color... Same BST, It is balanced, Faster than BST.
	
	// rotation
	// wrong direction leaning -> rotation
	// Insert -> use right leaning
	// Maintain Black Balanced Symmetric order
	
	// flip color(색 뒤집기)
	// two red link(In 2-3 tree, 4-node)
	// Node h == BLACK, Node h.left == RED, Node h.right == RED
	// In 2-3 tree, 4-node, keys = (h, h.left, h.right)
	// median value(h) move up parent
	// h.left.color = BLACK, h.right.color = BLACK, h.color = RED
	
	// Basic Strategy
	// Maintain 1-1 correspondence with 2-3 tree
	
	// proposition
	// height of tree is <= 2 lg N in the worst case
	// 1. Every path from root to null link has same number of black links.
	// 2. Never two red links in-a-row.
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		Key key;
		Value val;
		Node left;
		Node right;
		boolean color; // color of parent link
		
		Node(Key key, Value val, boolean color) {
			this.key = key;
			this.val = val;
			this.color = color;
		}
	}
	
	private Node root;
	
	private boolean isRed(Node x) {
		if (x == null) return false; // null links are black.
		return x.color == RED;
	}
	private Node rotateLeft(Node h) {
		assert(isRed(h.right));
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	private Node rotateRight(Node h) {
		assert(isRed(h.left));
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	private void flipColors(Node h) {
		assert(!isRed(h));
		assert(isRed(h.left));
		assert(isRed(h.right));
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	public Value get(Key key) {
		Node x = root;
		while(x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	private Node put(Node h, Key key, Value val) {
		if (h == null) return new Node(key, val, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0) h.left = put(h.left, key, val);
		else if (cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;
		
		if (isRed(h.right) && !isRed(h.left)) h = rotateRight(h); // wrong directioni leaning
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h); // balance 4-node
		if (isRed(h.left) && isRed(h.right)) flipColors(h); // split 4-node
		
		return h;
	}
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
}
