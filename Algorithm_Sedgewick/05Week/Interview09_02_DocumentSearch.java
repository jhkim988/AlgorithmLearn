import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.RedBlackBST;

// Q2. Document search.
// Design an algorithm that takes a sequence of n document words
// and a sequence of m query words and find the shortest interval
// in which the m query words appear in the document in the order given.
// The length of an interval is the number of words in that interval.

// sol)
// 1. Traversing docs[] array, Find query word and insert in Red-Black Tree.
// 2. key of Red-Black Tree is words, value of Red-Black Tree is sorted indices array.
// 3. We get m sorted array qa1, qa2, ... qam.
// 4. Let pointers be idx1, idx2, ..., idxm for each(in order to traverse).
// 5. If we find tuple (idx1, ..., idxm)
// such that qa1[idx1] < qa2[idx2] < ... < qam[idxm], Insert in MinPQ<int[]>
// 6. In this MinPQ<int[]>, Determine priority as idxm - idx1 value.
// 7. By delMin(), We can find shortest interval.

public class Interview09_02_DocumentSearch {
	private static boolean exist(Comparable[] a, Comparable key) {
		// a : sorted
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = a[mid].compareTo(key);
			if (cmp < 0) lo = mid + 1;
			else if (cmp > 0) hi = mid - 1;
			else
				return true;
		}
		return false;
	}
	static String[] findShortestIntervalContaining(String[] docs, String[] query) {
		int n = docs.length;
		int m = query.length;
		
		String[] result;
		String[] qry = query.clone(); // copy
		Arrays.sort(qry); // O(m log m)
		RedBlackBST<String, ArrayList<Integer>> rb = new RedBlackBST<String, ArrayList<Integer>>();
		
		for(int i = 0; i < n && exist(qry, docs[i]); i++) { // # of query words in docs.
			// exist check - O(log m)
			ArrayList<Integer> al = rb.get(docs[i]); // O(log m)
			al.add(i);
			al.sort(null); // insertion, O(al.size)
			rb.put(docs[i], al);
		}
		
		Comparator<int[]> c = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int val1 = o1[o1.length - 1] - o1[0];
				int val2 = o2[o2.length - 1] - o2[0];
				if (val1 < val2) 		return -1;
				else if (val1 > val2) 	return 1;
				else					return 0;
			}
		};
		MinPQ<int[]> pq = new MinPQ<int[]>(c);
		int[] tmp = new int[m];
		boolean flag = false;
		
		// NOT YET...
		for(int i = 0; i < m; i++) {
			tmp[i] = rb.get(query[i]).get(0);
			
			if (rb.get(query[i]).isEmpty()) {
				flag = true;
			}
			rb.get(query[i]).remove(i);
		}
		
		for(int i = 1; i < m; i++) {
			if (tmp[i] > tmp[i-1]);
		}
		pq.insert(tmp.clone());
		
		int[] resultIndices = pq.delMin();
		result = new String[resultIndices.length];
		for(int i = 0; i < resultIndices.length; i++) {
			result[i] = docs[i];
		}
		return result;
	}
	public static void main(String[] args) {
		In indocs = new In(args[0]);
		In inquery = new In(args[1]);
		String[] docs = indocs.readAllStrings();
		String[] query = inquery.readAllStrings();
		
		String[] shortest = findShortestIntervalContaining(docs, query);
	}
}
