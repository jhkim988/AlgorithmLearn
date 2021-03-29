import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings {
	// Implementation used LinkedList
	// push(): add node at head of linkedlist
	// pop(): remove and return node at head of linkedlist
	
	// No Loop, constant time in worst case O(1)
	// In Java, every object has 16byte overhead, specially inner class has 8byte additionally.
	// N node stack -> ~ 40N
	
	private Node first = null;
	private int size = 0; 
	private class Node {
		String item; // 8byte
		Node next; // 8byte
	}
	
//	StackOfStrings(){	}
	boolean isEmpty() {
		return first == null;
	}
	void push(String item) {
		// add new node at head of linkedlist
		Node oldfirst = first; // copy
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	String pop() {
		String item = first.item; // copy first.item
		first = first.next; // advance pointer, ready to reclaim by GC.
		size--;
		return item;
	}
	int size() {
		return size;
	}
	
	public static void main(String[] args) {
		LinkedStackOfStrings stack = new LinkedStackOfStrings();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("-"))
				StdOut.print(stack.pop());
			else
				stack.push(s);
		}
	}
}
