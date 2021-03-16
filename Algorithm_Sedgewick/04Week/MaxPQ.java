public class MaxPQ <Key extends Comparable<Key>>{
	// binary tree -> d-ary tree
	// insert log_d(N), del Max d log_d(N)
	// Fibonacci Heap - insert 1, del max log(N)
	// IMPOSSIBLE - insert 1, del max 1
	
	// underflow - throw exception if deleting from empty PQ.
	// overflow - add no-arg constructor and use resizing arr.(log N amortized)
	
	// minimum oriented priority queue.
	// replace less() with greater()
	// Implement greater()
	
	// remove arbitrary item
	// change the priority of an item
	private Key[] pq;
	private int N;
	
	MaxPQ() {
		// Create an empty priority queue.
	}
	MaxPQ(int capacity) { // use resizing array...
		pq = (Key[]) new Comparable[capacity + 1];
	}
	
	MaxPQ(Key[] a) {
		// Create a priority queue with given keys
	}
	
	// want to move child to above(root direction)
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	// want to move child to bottom(leaf direction)
	private void sink(int k) {
		while(2*k <= N) { // have child?
			int j = 2*k;
			if(j < N && less(j, j+1)) j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	// lg N
	void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	
	// lg N
	Key delMax() {
		Key max = pq[1]; // root elements
		exch(1, N--);
		sink(1);
		pq[N+1] = null;
		return max;
	}
	
	boolean isEmpty() {
		return N == 0;
	}
	
	Key max() {
		return pq[1];
	}
	
	int size() {
		return N;
	}
	
	private boolean less(int idx1, int idx2) {
		return pq[idx1].compareTo(pq[idx2]) < 0;
	}
	private void exch(int idx1, int idx2) {
		Key temp = pq[idx1];
		pq[idx2] = pq[idx2];
		pq[idx2] = temp;
	}
	
	public static void main(String[] args) {
		
	}
}