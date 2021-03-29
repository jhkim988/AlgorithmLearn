
public class LinkedQueueOfStrings {
	private class Node {
		String item;
		Node next;
	}
	
	Node first = null;
	Node last = null;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public String dequeue() {
		String item = first.item;
		first = first.next;
		return item;
	}
	
	public void enqueue(String item) {
		Node oldlast = last;
		Node last = new Node();
		last.item = item;
		last.next = null;
		oldlast.next = last; 
	}
}
