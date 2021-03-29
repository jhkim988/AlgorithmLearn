// Q1 Dynamic median.
// Design a data type that supports insert in logarithmic time,
// find-the-median in constant time, and remove-the-median in logarithmic time.
// If the number of keys in the data type is even, find/remove the lower median.

// sol)
// Use Two Priority Queue. [ max pq | median | min pq ]
// Case(1) the number of elements is odd, max pq.size =  N + 1, min pq.size = N
// Case(2) the number of elements is even, max pq.size = N, min pq.size = N
// Case(1) - a, Insert element less(or equal) than median,
// Insert to max pq and minpq.insert(maxpq.delMax());
// Case(1) - b, Insert element larger than median,
// Insert to min pq.
// Case(2) - a, Insert element less(or equal) than median,
// Insert to max pq.
// Case(2) - b, Insert element larger than median,
// Insert to min pq, and maxpq.insert(minpq.delMin());

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Interview07_01_DynamicMedian<Key extends Comparable<Key>> {
	private MaxPQ<Key> maxpq;
	private MinPQ<Key> minpq;
	
	Interview07_01_DynamicMedian() {
		maxpq = new MaxPQ<Key>();
		minpq = new MinPQ<Key>();
	}
	
	void insert(Key key) { // constant number of maxpq/minpq.insert() method -> log n
		if(maxpq.isEmpty()) {
			maxpq.insert(key);
			return;
		}
		
		// median -> root of maxPQ
		if(maxpq.max().compareTo(key) <= 0) { // median <= key
			if(maxpq.size() > minpq.size()) {
				minpq.insert(key);
			} else {
				minpq.insert(key);
				maxpq.insert(minpq.delMin());
			}
		} else {
			if(maxpq.size() > minpq.size()) { // key < median
				maxpq.insert(key);
				minpq.insert(maxpq.delMax());
			} else {
				minpq.insert(key);
			}
		}
	}
	
	Key median() { // constant
		return maxpq.max();
	}
	
	Key delMed() { // constant number of insert method -> log n
		Key result = maxpq.delMax();
		
		if(maxpq.size() < minpq.size()) {
			maxpq.insert(minpq.delMin());
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Interview07_01_DynamicMedian<Integer> dynMed = new Interview07_01_DynamicMedian<>();
		int N = 6;
		for(int i = 0; i < N; i++) {
			int input = StdRandom.uniform(N);
			StdOut.println("Input : " + input);
			dynMed.insert(input);
		}
		
		StdOut.println(dynMed.median());
		StdOut.println(dynMed.delMed());
		StdOut.println(dynMed.median());
	}
}
