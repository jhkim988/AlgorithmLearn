
public class RangeSearch1D<Key extends Comparable<Key>, Value> {
	// Unordered array : Fast insert, Slow range search(O(N))
	// Ordered array : Slow insert, Binary Search for k1 and k2 to do range search.
	// Binary Search Tree :
//	public int size(Key lo, Key hi) {
//		// In Binary Search Tree, log N
//		if(contains(hi)) return rank(hi) - rank(lo) + 1;
//		else return rank(hi) - rank(lo);
//	}
	// Search - Go down recursively with compare left and right(log N)
}
