
public class ResizingArrayStackOfStrings {
	// how to grow and shrink array?
	// 1. array size +-1
	// push - increasing size of array s[] by 1
	// pop - decreasing size of array s[] by 1
	// Inserting first N items -> 1 + 2 + ... + N ~ N^2/2
	
	// 2. array size *2
	// double resizing
	// Inserting first N items -> N + (2 + 4 + 8 + ... + N) ~ 3N
	// (N push) + (resizing, 2(2^(lgN) - 1))
	// amortized analysis
	
	// shrink
	// halve size of array s[] when array is one-half full -> thrashing!
	// when array is full, consider push-pop-push-pop...
	// -> doubling, halving, doubling, halving, ...
	// one-quarter -> halve sizing
	// array is always 25% ~ 100%
	// every time you resize, you've already paid for it in an amortized sense by inserting push/pop.
	
	// ~8N ~ ~32N
	
	private String[] s;
	private int N = 0;
	
	private void resize(int capacity) {
		String copy[] = new String[capacity];
		for(int i = 0; i < s.length; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	public ResizingArrayStackOfStrings() {
		String[] s = new String[1];
	}
	public void push(String item) {
		if(N == s.length) resize(2 * s.length);
		s[N++] = item;
	}
	public String pop() {
		String item = s[--N];
		s[N] = null;
		if (N > 0 && N == s.length / 4) resize(s.length/2);
		return item;
	}
	
}
