import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();

		// enqueue k - data.
		for (int i = 0; i < k && !StdIn.isEmpty(); i++)
			rq.enqueue(StdIn.readString());

		int j = 0;
		while (k != 0 && !StdIn.isEmpty()) {
			if (StdRandom.uniform(k + 1 + j) <= k) {
				rq.dequeue();
				rq.enqueue(StdIn.readString());
			} else {
				StdIn.readString();
			}
			j++;
		}
		for (int i = 0; i < k; i++)
			StdOut.println(rq.dequeue());
	}
}
