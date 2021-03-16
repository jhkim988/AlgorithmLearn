
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	
	public UnorderedMaxPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity];
	}
	
	public boolean isEmpty() {
		return N ==0;
	}
	
	public void insert(Key x) { // O(1)
		pq[N++] = x;
	}
	
	public Key delMax() { // O(N), max() - O(N)
		int max = 0;
		for(int i = 1; i < N; i++) {
			if(less(max, i)) max = i;
		}
		exch(pq, max, N-1);
		return pq[--N];
	}
	// 불규칙하다.
	// Unordered - insert O(1), max O(N), delMax O(N)
	// ordered - insert O(N), max O(1), delMax O(1)
	// goal - logN
	
	
	private boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	private void exch(Object[] a, int idx1, int idx2) {
		Object temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}
}
