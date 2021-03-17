// Q1 Dynamic median.
// Design a data type that supports insert in logarithmic time,
// find-the-median in constant time, and remove-the-median in logarithmic time.
// If the number of keys in the data type is even, find/remove the lower median.

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
