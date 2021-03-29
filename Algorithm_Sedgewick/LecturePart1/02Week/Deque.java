import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> head;
	private Node<Item> tail;
	private int size;

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;
	}

	// construct an empty deque
	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node<Item> newHead = new Node<Item>();
		newHead.item = item;
		newHead.next = head;
		if (size != 0)
			head.prev = newHead;
		head = newHead;
		size++;
		if (size == 1)
			tail = head;
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (size == 0) {
			addFirst(item);
			return;
		}
		Node<Item> newTail = new Node<Item>();
		newTail.item = item;
		tail.next = newTail;
		newTail.prev = tail;
		tail = newTail;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = head.item;
		head = head.next;
		if (size > 1) {
			head.prev = null;
		}
		if (size == 1) {
			tail = null;
		}
		size--;
		return item;
	}

	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		if (size == 1) {
			return removeFirst();
		}
		Item item = tail.item;
		tail = tail.prev;
		tail.next = null;
		size--;
		return item;
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node<Item> crnt = head;

		@Override
		public boolean hasNext() {
			return crnt != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = crnt.item;
			crnt = crnt.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// unit testing (required)
	public static void main(String[] args) {
		Deque<Integer> deq = new Deque<Integer>();
		StdOut.println("Deque is " + (deq.isEmpty() ? "empty" : "not empty"));
		deq.addFirst(1);
		deq.addFirst(2);
		StdOut.println(deq.removeLast());
		StdOut.println(deq.removeLast());
		deq.addLast(3);
		deq.addLast(4);
		deq.addFirst(5);
		StdOut.println(deq.removeFirst());
		StdOut.println("size of Deque : " + deq.size());
		StdOut.println("remove first node : " + deq.removeFirst());
		StdOut.println("remove last node : " + deq.removeLast());
		StdOut.println("Deque is " + (deq.isEmpty() ? "empty" : "not empty"));
		Iterator<Integer> iter = deq.iterator();
		while (iter.hasNext()) {
			StdOut.println("iterator print : " + iter.next());
		}
	}
}
