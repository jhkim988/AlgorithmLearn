import edu.princeton.cs.algs4.StdRandom;

// Q2. Randomized priority queue.
// Describe how to add the methods sample() and delRandom() to our binary heap implementation.
// The two methods return a key that is chosen uniformly at random among the remaining keys
// with the latter method also removing that key. The sample() method should take constant time;
// the delRandom() method should take logarithmic time. Do not worry about resizing the underlying array.

public class Interview07_02_RandomizedPriorityQueue<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	
	Interview07_02_RandomizedPriorityQueue(int capacity) {
		pq = (Key[]) new Comparable[capacity + 1];
	}

	void insert(Key key) {
		pq[++N] = key;
		swim(N);
	}
	Key delMax() {
		Key max = pq[1];
		exch(1, N);
		pq[N--] = null;
		sink(1);
		return max;
	}
	Key sample() {
		return pq[StdRandom.uniform(1, N + 1)]; // [1, N]
	}
	
	Key delRandom() {
		int rand = StdRandom.uniform(1, N + 1);
		Key randKey = pq[rand];
		exch(rand, N);
		pq[N--] = null;
		sink(rand);
		return randKey;
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
		return pq[idx1].compareTo(pq[idx1]) < 0;
	}
	private void exch(int idx1, int idx2) {
		Key tmp = pq[idx1];
		pq[idx1] = pq[idx2];
		pq[idx2] = tmp;
	}
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k /= 2;
		}
	}
	private void sink(int k) {
		while(2*k <= N) {
			int j = 2*k;
			if(2*k < N && less(j, j+1)) j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
}
