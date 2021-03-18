import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ST<Key, Value> {
	// Key - Comparable -> compareTo(), any generic -> equals(), hashcode()
	// Value - any generic
	// immutable type
	// equality - reflexive, symmetric, trnasitive, non-null
	ST() { // create symbolic table
		
	}
	void put(Key key, Value val) {
		// overwirte old value with new value
		// value are not null(does not allow)
	}
	Value get(Key key) {
		// if key not present, return null
	}
	void delete(Key key) {
		// lazy
		put(key, null);
	}
	boolean contains(Key key) {
		return get(key) != null;
	}
	boolean isEmpty() {

	}
	int size() {
		
	}
	Iterable<Key> keys() {
		
	}
	
	public static void main(String[] args) {
//		ST<String, Integer> st = new ST<String, Integer>();
//		for(int i = 0; !StdIn.isEmpty(); i++) {
//			String key = StdIn.readString();
//			st.put(key, i);
//		}
//		
//		for(String s : st.keys())
//			StdOut.println(s + " " + st.get(s));
		
		int minlen = Integer.parseInt(args[0]);
		ST<String, Integer> st = new ST<String, Integer>();
		while(!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if(word.length() < minlen) continue;
			if(!st.contains(word)) st.put(word, 1);
			else st.put(word, st.get(word) + 1);
		}
		String max = "";
		st.put(max, 0);
		for(String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}
		StdOut.println(max + " " + st.get(max));
	}
}
