import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStack<Item> implements Iterable<Item>{
	private Node first = null;
	private int size = 0;
	private class Node {
		Item item;
		Node next;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node crnt = first;
		public boolean hasNext() {
			return crnt != null;
		}
		public Item next() {
			Item item = crnt.item;
			crnt = crnt.next;
			return item;
		}
	}
	
	boolean isEmpty() {
		return first == null;
	}
	void push(Item item) {
		Node oldfirst = first; // copy
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	Item pop() {
		Item item = first.item; // copy first.item
		first = first.next; // advance pointer, ready to reclaim by GC.
		size--;
		return item;
	}
	int size() {
		return size;
	}
	Item peak() {
		return first.item;
	}
	void dump () {
		Node temp = first;
		while(temp != null) {
			System.out.print(temp.item + " ");
			temp = temp.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LinkedStack<String> stack = new LinkedStack<String>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("-"))
				StdOut.print(stack.pop());
			else
				stack.push(s);
		}
	}

}
