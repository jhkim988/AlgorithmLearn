
public class Heap<Key extends Comparable<Key>> {
	// array를 이용한 Heap 구현
	// 편의상 a[0]는 비워둔다.
	// a[1]부터 root, ...
	private Key[] heap;
	private int N;
	
	private void swim(int k) { // index k element
		while(k > 1 && less(k/2, k)) { // parent <= child
			exch(k/2, k); // swap
			k /= 2;
		}
	}
	
	private void sink(int k) {
		// parent node k become smaller than one(or both) of its childs'
		while(2*k <= N) {
			int j = 2*k; // child
			if(j < N && less(j, j+1)) j++; // choose lager child
			if(!less(k, j)) break; // if parent >= small, break
			exch(k, j);
			k = j;
		}
	}
	
	public void insert(Key key) { // add key in leaf and swim
		heap[++N] = key;
		swim(N); // At most 1 + lg N compares
	}
	
	private boolean less(int idx1, int idx2) {
		return heap[idx1].compareTo(heap[idx2]) < 0;
	}
	private void exch(int idx1, int idx2) {
		Key tmp = heap[idx1];
		heap[idx1] = heap[idx2];
		heap[idx2] = tmp;
	}
}
