import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] s;
	private int N = 0;
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private int crnt = N;
		public boolean hasNext() {
			return crnt > 0;
		}
		public Item next() {
			return s[--crnt];
		}
	}
	
	private void resize(int capacity) {
		Item copy[] = (Item[]) new Object[capacity];
		for(int i = 0; i < s.length; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	public ResizingArrayStack() {
		Item[] s = (Item[])new Object[1];
	}
	public void push(Item item) {
		if(N == s.length) resize(2 * s.length);
		s[N++] = item;
	}
	public Item pop() {
		Item item = s[--N];
		s[N] = null;
		if (N > 0 && N == s.length / 4) resize(s.length/2);
		return item;
	}
	
}
