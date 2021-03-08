import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int size;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	public Item peak() {
		return first.item;
	}
	public Item pop() {
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		Node crnt = first;
		
		@Override
		public boolean hasNext() {
			return crnt != null;
		}

		@Override
		public Item next() {
			Item item = crnt.item;
			crnt = crnt.next;
			return item;
		}
		
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack;
		stack = new Stack<Integer>();
		while(!StdIn.isEmpty())
			stack.push(StdIn.readInt());
		for(int i : stack)
			StdOut.println(i);
	}
	
	// In 02Week...
	// Dijkstra - Double Stack Algorithm
	// Fixed Capacity Stack of String
		// Not Generic - GenericClass<T> -> T must be reference Type. primitive type -> Wrapper Type(Auto-boxing)
		// Generic Array - Use Type casting - Item[] a = (Item[]) new Object[cap];
		// client must know size of stack in advance.
		// java cannot change size of array... -> Fixed capacity stack has always full size.(wasted memory) -> Fixed capacity stack must provide isFull() method.
	// Resizing Stack
		// why halve size of array if size became quarter?(why not if size became half?) - if so, too many over head when push() pop() alternatively.
		// Loitering - When some element of stack pop, It remain in stack-array, Therefore GC does not operate(Loitering). So, We have to free the memory(a[N] = null;) before pop.
	// iterator - foreach()
		// Iterable collection must implement method to return Iterator object.
		// Iterator class must implement two methods : hasNext(), next()
		// remove method - It is advisible not to create a situation where the data structure changes while traversing data.
	// List traverse
		//	for (Node x = first; x != null; x = x.next) {
		//		
		//	}
}
