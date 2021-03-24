
class IntervalST<Key extends Comparable<Key>, Value> {
	private class Node {
		Key interval;
		Node left;
		Node right;
		Key max; // maximum key of subtree as this node is root
	}

	Node root;

	IntervalST() {

	}

	void put(Key lo, Key hi, Value val) { // put interval-value pair into ST
		// [lo, hi]

	}

//	Value get(Key lo, Key hi) {
//		// value paired with given interval
//	}

	void delete(Key lo, Key hi) {
		// delete the given interval

	}

//	Iterable<Value> intersects(Key lo, Key hi) {
//		// all intervals that intersects the given interval
//
//	}

	// Interval Search Trees
	// Use left endpoint as BST key.
	// Store Maximum Key of Subtree at each Node
	// Use Red-Black BST
//	Key intervalSearch(Key lo, Key hi) { // search any one that intersects query interval (lo, hi)
//		Node x = root;
//		while(x!=null) {
//			if (x.interval.intersects(lo, hi)) return x.interval;
//			else if(x.left == null) x = x.right;
//			else if (x.left.max.compareTo(lo) < 0) x = x.right;
//			else x = x.left;
//		}
	// proof
	// 1. If search goes right, then no intersection in left.
	// left == null -> no intersection in left
	// left.max < lo -> no intersection in left
	// 2. If search goes left, then there is either an intersection in left subtree
	// or no intersections(left and right) in either.
	// Suppose no intersection in left. Since went left, lo <= max
	// Let c be left point of max interval. i.e. (c, max)
	// For any interval (a, b) s.t. in right subtree, hi < c <= a.
	// hi < c : We assume no intersection in left. If c <= hi(and lo <= max),
	// intersects.
	// c <= a : right of tree.
}

public class IntervalSearch {

}
