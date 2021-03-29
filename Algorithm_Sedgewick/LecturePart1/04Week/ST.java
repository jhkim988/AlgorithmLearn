import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ST<Key extends Comparable<Key>, Value> {
	// Key - Comparable -> compareTo(), any generic -> equals(), hashcode()
	// Value - any generic
	// immutable type
	// equality - reflexive, symmetric, trnasitive, non-null
	
	// Linked List
	// Maintain linked list, key-value pairs
	// Search - scan through all keys until find a mat	ch O(N)
	// Insert - scanf through all keys until find a match, if no match add to front.
	
	// Binary Search -  To insert, need to shift all generater keys over.
	// Scan - log N
	// Insert - N, N/2
//	private Key[] keys; // sorted
//	private Value[] vals; // keys[i] - vals[i]
//	private int N; // size
//	private int rank(Key key) {
//		int lo = 0;
//		int hi = N - 1;
//		while(lo <= hi) {
//			int mid = lo + (hi - lo) / 2;
//			int cmp = keys[mid].compareTo(key);
//			if (cmp < 0) lo = mid + 1;
//			else if (cmp > 0) hi = mid - 1;
//			else return mid;
//		}
//		return lo;
//	}
	
	// Binary Search Tree
	// Linked List - node have left, right link
	// search - ~ 1.39lgN
	// insert - ~ 1.39lgN
	// worst case - N, (single linked)
	private class Node {
		public Key key;
		public Value val;
		public Node left, right;
		public int count; // the number of node in subtree
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}
	private Node root;
	
	ST() { // create symbolic table
		
	}
	void put(Key key, Value val) {
		// overwirte old value with new value
		// value are not null(does not allow)
		root = put(root, key, val); // if root == null, new Node
	}
	Node put(Node x, Key key, Value val) {
		// similar to quick sort
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	Value get(Key key) {
		// if key not present, return null
		
		// Binary Search
//		if(isEmpty()) return null;
//		int i = rank(key);
//		if(i < N && keys[i].compareTo(key) == 0) return vals[i];
//		else return null;

		// Binary Search Tree
		Node x = root;
		while(x != null) {
			int cmp = key.compareTo(x.key);
			if(cmp > 0) x = x.left;
			if(cmp < 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	
	void delete(Key key) {
		// 1. Use Tombstone lazy
		// put(key, null);
		
		// Hibbard deletion - Not Symmetric
		// Repeated deletion -> break balance of tree.. ~ sqrt(N) and sqrt(N) height
		// Red Black tree - deletion logN
		// 1. 0 child 
		// 2. 1 child
		// 3. 2 children - Delete node x
		// Find minimum of right subtree(node t)
		// delete minimum in t's right subtree
		root = delete(root, key);
	}
	private Node delete(Node x, Key key) {
		// Hibbard deletion
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			// cover case 0 child, 1 child
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			
			// cover case 2 child
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}
	Node min(Node x) {
		while(x.left != null) {
			x = x.left;
		}
		return x;
	}
	// Deleting the minimum
	void deleteMin() {
		root = deleteMin(root);
	}
	Node deleteMin(Node x) {
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	void deleteMax() {
		root = deleteMax(root);
	}
	Node deleteMax(Node x) {
		if(x.right == null) return x.left;
		x.right = deleteMin(x.right);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	boolean contains(Key key) {
		return get(key) != null;
	}
	boolean isEmpty() {
		return size(root) == 0;
	}
	int size() {
		return size(root);
	}
	int size(Node x) {
		if (x == null) return 0;
		return x.count;
	}
	Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		inorder(root, q);
		return q;
	}
	private void inorder(Node x, Queue<Key> q) {
		if(x == null) return;
		inorder(x.left, q);
		q.enqueue(x.key);
		inorder(x.right, q);
	}
	
	Value min() {
		Node x = root;
		while(x.left != null) {
			x = x.left;
		}
		return x.val;
	}
	
	Value max() {
		Node x = root;
		while(x.right != null) {
			x = x.right;
		}
		return x.val;
	}
	
	Key floor(Key k) {
		// case 1) k equals the key at root
		// case 2) k is less than the key at root -> left subtree
		// case 3) k is greater than the key at root -> right subtree
		// any key <= k, otherwise it is the key in the root
		Node x = floor(root, k);
		if (x == null) return null;
		return x.key;
	}
	
	Node floor(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp < 0) return floor(x.left, key);
		
		Node t = floor(x.right, key);
		if(t!=null) return x;
		else return x;
	}
	
	Key ceilling(Key k) {
		Node x = ceiling(root, k);
		if (x == null) return null;
		return x.key;
	}
	
	Node ceiling(Node x, Key k) {
		if(x == null) return null;
		int cmp = k.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp > 0) return ceiling(x.right, k);
		
		Node t = ceiling(x.left, k);
		if(t != null) return t;
		else return x;
	}
	
	public int rank(Key key) {
		return rank(key, root);
	}
	
	private int rank(Key key, Node x) {
		if (x== null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) return rank(key, x.left);
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
		else return size(x.left);
	}
	
	public static void main(String[] args) {
//		ST<String, Integer> st = new ST<String, Integer>();
//		for(int i = 0; !StdIn.isEmpty(); i++) {
//			String key = StdIn.readString();
//			st.put(key, i);
//		}
//		
//		for(String s : st.keys())
//			StdOut.println(s + " " + st.get(s));
		
		int minlen = Integer.parseInt(args[0]);
		ST<String, Integer> st = new ST<String, Integer>();
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(word.length() < minlen) continue;
			if(!st.contains(word)) st.put(word, 1);
			else st.put(word, st.get(word) + 1);
		}
		String max = "";
		st.put(max, 0);
		for(String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println(max + " " + st.get(max));
	}
}
