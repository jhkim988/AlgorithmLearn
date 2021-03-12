import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Q3 Shuffling a linked list.
// Given a singly-linked list containing n items, rearrange the items uniformly at random.
// Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

// sol) divide conquer.
// Let A be singly-linked list and have size N.
// Suppose front half of A and rear half of A are uniformly shuffled.
// If we merge the two halves with 50% probability, we can uniformly shuffle total Linked List A.
// the number of cases that A is uniformly shuffled = N!
// the number of cases that front/rear half of A is uniformly shuffled = (N/2)!
// the number of cases that two single-linked list with length L are merged = (2L)!/(L!L!)
// Therefore, our method to merge uniformly shuffled two single-linked list are valid.(maybe?)

public class Interview05_03_ShufflingLinkedList {
	private static void mergeList(ArrayList<Integer> deleted, ArrayList<Integer> created) {
		// use removeFirst and addLast -> memory saved.
//		StdOut.println("deleted : " + deleted);
//		StdOut.println("created : " + created);
		int dsize = deleted.size();
		int csize = created.size();
		int i = 0, j = 0;
		while (i < dsize && j < csize) {
			if (StdRandom.bernoulli()) {
				created.addLast(created.head.item);
				created.removeFirst();
				j++;
			} else {
				created.addLast(deleted.head.item);
				deleted.removeFirst();
				i++;
			}
		}
		while (i < dsize) {
			created.addLast(deleted.head.item);
			deleted.removeFirst();
			i++;
		}
		while (j < csize) {
			created.addLast(created.head.item);
			created.removeFirst();
			j++;
		}
	}

	private static ArrayList<Integer> divideArrayList(ArrayList<Integer> arrlst, int frontNum) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < frontNum; i++) {
			int rm = arrlst.removeFirst();
			result.addLast(rm);
		}
		return result;
	}

	public static void shuffleList(ArrayList<Integer> arrlst) {
		int N = arrlst.size();
		if (N < 2)
			return;
		
		// just mid = N / 2 -> Not uniform
		int mid;
		if (N % 2 == 0) {
			mid = N / 2;
		} else {
			if (StdRandom.bernoulli()) {
				mid = (N / 2) + 1;
			} else {
				mid = (N / 2);
			}
		}
		
		ArrayList<Integer> fronthalf = divideArrayList(arrlst, mid);
		shuffleList(fronthalf);
		shuffleList(arrlst); // rear half
		mergeList(fronthalf, arrlst);
	}

	public static void main(String[] args) {
		// generate and shuffle
//		ArrayList<Integer> arrlst = new ArrayList<Integer>();
//		for (int i = 0; i < 10; i++) {
//			arrlst.addFirst(i);
//		}
//		StdOut.println(arrlst);
//		shuffleList(arrlst);
//		StdOut.println(" ----- shuffle ----- ");
//		StdOut.println(arrlst);

		// really uniform ?
		int T = 100;
		int arrSize = 10;
		double[][] statArr = new double[T][arrSize];
		
		for (int t = 0; t < T; t++) {
			ArrayList<Integer> testlst = new ArrayList<Integer>();
			for (int i = 0; i < arrSize; i++) {
				testlst.addLast(i);
			}
			shuffleList(testlst);
			Node<Integer> tmp = testlst.head;
			int i = 0;
			while (tmp != null) {
				statArr[t][i++]+= tmp.item;
				tmp = tmp.next;
			}
//			StdOut.println(Arrays.toString(statArr));
		}

		double[] mean = new double[arrSize];
		double[] stddev = new double[arrSize];
		for(int i = 0; i < arrSize; i++) {
			mean[i] = StdStats.mean(statArr[i]);
			stddev[i] = StdStats.stddev(statArr[i]);
		}
		
		StdOut.println(Arrays.toString(mean));
		StdOut.println(Arrays.toString(stddev));
	}
}
