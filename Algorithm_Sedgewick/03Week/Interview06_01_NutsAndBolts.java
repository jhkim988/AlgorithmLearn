import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Q1 Nuts and bolts.
// A disorganized carpenter has a mixed pile of n nuts and n bolts.
// The goal is to find the corresponding pairs of nuts and bolts.
// Each nut fits exactly one bolt and each bolt fits exactly one nut.
// By fitting a nut and a bolt together, the carpenter can see which one is bigger
// (but the carpenter cannot compare two nuts or two bolts directly).
// Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).

// sol)
// For some fixed nut, Process 3-way-partition Bolts. (N compares)
// We can find bolt that fit fixed nut.
// For bolt we found, Process 3-way-partition Nuts. (N compares)
// Then, Bolts and Nuts are same partitioning state(same indice).
// Recursively Repeat smaller and larger part.
// Then we can find all pair of bolts and nuts with 2NlgN compares.

public class Interview06_01_NutsAndBolts {
	private static class Bolt {
		private static int callCompares = 0;
		private int id;

		Bolt(int id) {
			this.id = id;
		}

		int id() {
			return id;
		}

		int compares(Nut nut) {
			callCompares++;
			if (id() > nut.id()) {
				return 1;
			} else if (id() < nut.id()) {
				return -1;
			} else {
				return 0;
			}
		}

		public String toString() {
			return id + " ";
		}
	}

	private static class Nut {
		private static int callCompares = 0;
		private int id;

		int id() {
			return id;
		}

		Nut(int id) {
			this.id = id;
		}

		int compares(Bolt bolt) {
			callCompares++;
			if (id() > bolt.id()) {
				return 1;
			} else if (id() < bolt.id()) {
				return -1;
			} else {
				return 0;
			}
		}

		public String toString() {
			return id + " ";
		}
	}

	public static Bolt[] randomBoltArrayGenerator(int size) {
		// Bolt id range : 0 ~ size - 1
		Bolt[] resultArr = new Bolt[size];
		for (int i = 0; i < size; i++) {
			resultArr[i] = new Bolt(i);
		}
		StdRandom.shuffle(resultArr);
		return resultArr;
	}

	public static Nut[] randomNutArrayGenerator(int size) {
		// Bolt id range : 0 ~ size - 1
		Nut[] resultArr = new Nut[size];
		for (int i = 0; i < size; i++) {
			resultArr[i] = new Nut(i);
		}
		StdRandom.shuffle(resultArr);
		return resultArr;
	}

	private static void exch(Object[] arr, int idx1, int idx2) {
		Object tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}

	private static void findPair(Bolt[] bolts, Nut[] nuts, int lo, int hi) {
		// 3-way partition
		// nuts[lo] -> bolts partitioning
		// nuts[lo] fits bolts[lt] (also lt == gt)
		// bolts[lt] -> nuts partitioning
		if (hi <= lo)
			return;
		int lt = lo, gt = hi;
		int i = lo;

		// nuts[lo] partitioning
		while (i <= gt) {
			int cmp = bolts[i].compares(nuts[lo]);
			if (cmp < 0)
				exch(bolts, lt++, i++);
			else if (cmp > 0)
				exch(bolts, i, gt--);
			else
				i++;
		}

		if (lt != gt) {
			StdOut.println("lt != gt");
		}

		int tmp = lt; // bolts[lt] - nuts[lo]
		lt = lo;
		gt = hi;
		i = lo;

		// bolts[lt] partitioning
		while (i <= gt) {
			int cmp = nuts[i].compares(bolts[tmp]);
			if (cmp < 0)
				exch(nuts, lt++, i++);
			else if (cmp > 0)
				exch(nuts, i, gt--);
			else
				i++;
		}
		findPair(bolts, nuts, lo, lt - 1);
		findPair(bolts, nuts, gt + 1, hi);
	}

	public static void findPair(Bolt[] bolts, Nut[] nuts) {
		int size = bolts.length;
		assert (size == nuts.length);
		findPair(bolts, nuts, 0, size - 1);
	}

	public static boolean verifySolution(Bolt[] bolts, Nut[] nuts) {
		int size = bolts.length;
		assert (size == nuts.length);
		for (int i = 0; i < size; i++)
			if (bolts[i].compares(nuts[i]) != 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		int size = 1000;
		int trial = 1000;
		double[] result = new double[trial];
		Bolt.callCompares = 0;
		Nut.callCompares = 0;
		for (int i = 0; i < trial; i++) {
			Bolt[] boltArr = randomBoltArrayGenerator(size);
			Nut[] nutArr = randomNutArrayGenerator(size);
			findPair(boltArr, nutArr);
			result[i] = (double) (Bolt.callCompares + Nut.callCompares);
			Bolt.callCompares = 0;
			Nut.callCompares = 0;
		}

		double mean = StdStats.mean(result);
		double esti = size * 2 * Math.log(size) / Math.log(2); // 2NlgN
		StdOut.println("mean : " + mean + " \testi : " + esti);
		
		// verify Solution
		boolean flag = true;
		for (int i = 0; i < trial; i++) {
			Bolt[] boltArr = randomBoltArrayGenerator(size);
			Nut[] nutArr = randomNutArrayGenerator(size);
			findPair(boltArr, nutArr);
			if (!verifySolution(boltArr, nutArr))
				flag = false;
		}
		StdOut.println("flag : " + flag);
	}
}
