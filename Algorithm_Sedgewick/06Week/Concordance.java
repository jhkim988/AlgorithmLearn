import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class Concordance {
	// find context...
	public static void main(String[] args) {
		In in = new In(args[0]);
		String[] words = StdIn.readAll().split("\\s+");
		ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
		for(int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!st.contains(s)) st.put(s, new SET<Integer>());
			SET<Integer> pages = st.get(s);
			pages.add(i);
		}
		
		while(!StdIn.isEmpty()) {
			String query = StdIn.readString();
			SET<Integer> set = st.get(query);
			for(int k : set) {
				// print words[k-4] to words[k+4];
			}
		}
	}
}
