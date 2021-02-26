
public class FixedCapacityStackOfStrings {
	private String[] s;
	private int N = 0; // cursor
	
	// If the client pop from an empty stack or push from a full stack, throw the exception
	// Allow to insert null?
	// loitering - if we remove item in stack, there is still a pointer in array -> GC Not work
	// remove item -> null
	
	public FixedCapacityStackOfStrings(int capacity) { // requiring the client to provide the capacity
		s = new String[capacity];
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public void push(String item) {
		s[N++] = item;
	}
	public String pop() {
		String item = s[--N];
		s[N] = null;
		return item;
	}
}
