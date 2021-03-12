import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// Q1 Nuts and bolts.
// A disorganized carpenter has a mixed pile of n nuts and n bolts.
// The goal is to find the corresponding pairs of nuts and bolts.
// Each nut fits exactly one bolt and each bolt fits exactly one nut.
// By fitting a nut and a bolt together, the carpenter can see which one is bigger
// (but the carpenter cannot compare two nuts or two bolts directly).
// Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).

public class Interview06_01_NutsAndBolts {
	private static class Bolt {
		private int id;

		Bolt(int id) {
			this.id = id;
		}
		
		int id() {
			return id;
		}
		
		int compares (Nut nut) {
			if(id() > nut.id()) {
				return 1;
			} else if (id() < nut.id()) {
				return -1;
			} else {
				return 0;
			}
		}
		
		public String toString() {
			return id+" ";
		}
	}
	private static class Nut {
		private int id;
		int id() {
			return id;
		}
		Nut(int id) {
			this.id = id;
		}
		
		int compares (Bolt bolt ) {
			if(id() > bolt.id()) {
				return 1;
			} else if (id() < bolt.id()) {
				return -1;
			} else {
				return 0;
			}
		}
		
		public String toString() {
			return id+" ";
		}
	}	
	public static Bolt[] randomBoltArrayGenerator(int size) {
		// Bolt id range : 0 ~ size - 1
		Bolt[] resultArr = new Bolt[size];
		for(int i = 0; i < size; i++) {
			resultArr[i] = new Bolt(i);
		}
		StdRandom.shuffle(resultArr);
		return resultArr;
	}
	
	public static Nut[] randomNutArrayGenerator(int size) {
		// Bolt id range : 0 ~ size - 1
		Nut[] resultArr = new Nut[size];
		for(int i = 0; i < size; i++) {
			resultArr[i] = new Nut(i);
		}
		StdRandom.shuffle(resultArr);
		return resultArr;
	}
	
	public static void findPair(Bolt[] bolts, Nut[] nuts, int lo, int hi) {
		// only use compare() method, NOT id()
		// nlogn algorithm
		
		// move bolts[]. fixed nuts[].
		// two points, lo and hi, imitate quick sort(selection) algorithm.
		// use the fact that all are distinct.
		
		// partitioning [ < nuts[lo] | = nuts[lo] | > nuts[lo] ]
		// return i such that nuts[lo].compares(bolts[i]) == 0;
		int i = lo, j = hi + 1;
		while(i <= j) {
			if (bolts[i].compares(nuts[lo]) < 0) i++;
			else if (bolts[i].compares(nuts[lo]) > 0) exch(bolts, i, j--);
			else exch(bolts, i++, lo++);
		}
		
	}
	
	public static void main(String[] args) {
		int size = 100;
		Bolt[] boltArr = randomBoltArrayGenerator(size);
		Nut[] nutArr = randomNutArrayGenerator(size);
		
		StdOut.println(Arrays.toString(boltArr));
		StdOut.println(Arrays.toString(nutArr));
	}
}
