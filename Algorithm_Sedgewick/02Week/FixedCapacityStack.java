
public class FixedCapacityStack<Item> {
	// How to use primitive type?
	// - Wrapper object types
	// int -> Integer
	// auto boxing
	
	private Item[] s;
	private int N = 0; 
	
	
	public FixedCapacityStack(int capacity) {
//		s = new Item[capacity]; // Java does not allow create Generic Array
		s = (Item[]) new Object[capacity]; // type casting, warning message
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public void push(Item item) {
		s[N++] = item;
	}
	public Item pop() {
		Item item = s[--N];
		s[N] = null;
		return item;
	}
}
