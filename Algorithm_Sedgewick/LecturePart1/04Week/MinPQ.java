import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;

	@SuppressWarnings("unchecked")
	public MinPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity];
	}

	// want to move child to above(root direction)
	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k / 2, k);
			k /= 2;
		}
	}

	// want to move child to bottom(leaf direction)
	private void sink(int k) {
		while (2 * k <= N) { // have child?
			int j = 2 * k;
			if (j < N && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
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
	Key delMin() {
		Key max = pq[1]; // root elements
		exch(1, N--);
		sink(1);
		pq[N + 1] = null;
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

	private boolean greater(int idx1, int idx2) {
		return pq[idx1].compareTo(pq[idx2]) > 0;
	}

	private void exch(int idx1, int idx2) {
		Key temp = pq[idx1];
		pq[idx2] = pq[idx2];
		pq[idx2] = temp;
	}

	public static void main(String[] args) {
//		int M = 10;
//		MinPQ<Transaction> pq = new MinPQ<Transaction>(10000);
//		while(StdIn.hasNextLine()) {
//			String line = StdIn.readLine();
//			Transaction item = new Transaction(line);
//			pq.insert(item);
//			if(pq.size() > M) // Maintain M elements.
//				pq.delMin(); // delete minimum value
//			// Remain elements are largest M elements.
//			
//			// M elements...
//			//			 		time 	space
//			// Sorting  		NlogN	N
//			// elementaryPQ		MN		M
//			// binary heap 		NlogM 	M
//			// best in theory	N		M
//		}

		int N = 5;
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 122, 3, 1, 5, 6, 7 };
		MinPQ<Integer> pqInt = new MinPQ<Integer>(10000);
		for (int el : arr) {
			pqInt.insert(el);
			if (pqInt.size() > N) { // Maintain M elements.
				StdOut.println(pqInt.delMin()); // delete minimum value
			}
		}
	}
}
