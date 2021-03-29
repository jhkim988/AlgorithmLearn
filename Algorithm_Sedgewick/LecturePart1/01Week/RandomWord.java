import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
	public static void main(String[] args) {

		double num = 1;
		String result = "";

		while (!StdIn.isEmpty()) {
			if (StdRandom.bernoulli(1 / (num++))) {
				result = StdIn.readString();
			} else {
				StdIn.readString();
			}
		}
		StdOut.println(result);
	}
}
