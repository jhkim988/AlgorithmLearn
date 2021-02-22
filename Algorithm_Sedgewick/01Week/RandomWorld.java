import edu.princeton.cs.algs4.*;

public class RandomWorld {
	public static void main(String [] args) {
		int count = 1;
		String read = "";
		while(!StdIn.isEmpty()) {
			if (StdRandom.bernoulli(count++)) {
				read = StdIn.readString();
			}
		}
		System.out.println(read);
	}
}
