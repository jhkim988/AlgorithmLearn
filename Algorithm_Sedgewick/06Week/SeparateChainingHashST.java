
public class SeparateChainingHashST<Key, Value> {
	// Collision: Two distinct keys hashing to same index
	// Birthday problem - can't avoid collisions unless you have a ridiculous (quadratic) amount of memeory.
	// Coupon collector + load balancing - collisions will be evenly distritubed.
	// Challenge: Deal with collision efficiently.
	// hash("it") = 3, hash("times") = 3
	
	// Use an array of M < N linked list
	// Hash: map key to integer i betwwen 0 and M-1.
	// Insert: put at front of ith chain(if not already there).
	// Search: need to search only ith chain.
	
	// key - S E A R C H E X A M P L E
	// hash- 2 0 0 4 4 4 0 2 0 4 3 3 0
	// value 0 1 2 3 4 5 6 7 8 9 101112
	// st[] = [ A8-E12, null, X7-S0, L11-P10, M9-H5-C4-R3], array of linked list
	
	// N data, M hash table -> Average length of linked list = N/M
	// get - compute hash, find key by traversing linked list
	// put - compute hash, if there already exist in linked list, change value, and if not, create node.
	
	// Under uniform hashing assumption,
	// prob. that the number of keys in a list is within a constant factor of N/M is extremely close to 1.
	// Number of probes(equals() and hashCode()) for search/insert is proportional to N/M.
	// M too large - too many empty chains
	// M too small - chains too long
	// Typical choice: M ~ N/5 - constant-time ops.
	// Red-Black tree - search/insert/delete take log times.
	
	private int M = 97;
	private Node[] st = new Node[M];
	
	private static class Node {
		private Object key;
		private Object val;
		private Node next;
	}
	
	private int hash(Key key) {return (key.hashCode() & 0x7fffffff) % M;}
	public Value get(Key key) {
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next)
			if (key.equals(x.key)) return (Value) x.val;
		return null;
	}
}