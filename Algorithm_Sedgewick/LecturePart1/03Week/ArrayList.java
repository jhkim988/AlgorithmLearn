class Node<Item> {
	Item item;
	Node<Item> next;

	Node(Item item) {
		this.item = item;
	}

	Node(Item item, Node<Item> next) {
		this.item = item;
		this.next = next;
	}
}

public class ArrayList<Item> {

	Node<Item> head;
	Node<Item> crnt;
	Node<Item> tail;
	int size = 0;

	ArrayList() {
		head = crnt = tail = null;
		size = 0;
	}
	
	boolean isEmpty() {
		return size == 0;
	}

	int size() {
		return size;
	}

	void addFirst(Item item) {
		Node<Item> oldHead = head;
		head = new Node<Item>(item, oldHead);
		size++;

		if (size == 1) {
			tail = head;
		}
	}

	void addLast(Item item) {
		if (size == 0) {
			addFirst(item);
			return;
		}
//		Node<Item> oldTail = tail;
//		tail = new Node<Item>(item);
//		oldTail.next = tail;
		tail.next = new Node<Item>(item);
		tail = tail.next;
		size++;
	}

	Item removeFirst() {
		Item item = head.item;
		head = head.next;
		size--;

		if (size == 0) {
			tail = null;
		}
		
		return item;
	}

//	Item removeLast() {
//		if (size == 1) {
//			return removeFirst();
//		}
//		Item item = tail.item;
//		tail.next = null;
//		size--;
//		return item;
//	}

	Item delete(int k) {
		if (k < 0) // k is non-negative
			throw new IllegalArgumentException();
		if (size <= k) { // there is no k'th element
			return null;
		}

		Node<Item> tmp = head;
		// k = 0, 1, ..., size - 1
		while (k-- != 1) {
			tmp = tmp.next;
		} // stop (k - 1)'th index

		Item tmpItem = tmp.next.item;
		tmp.next = tmp.next.next;

		size--;
		return tmpItem;
	}

	Item removeAfter(Node<Item> node) {
		if (node == null || node.next == null)
			return null;
		Item item = node.item;
		node.next = node.next.next;

		size--;
		return item;
	}

	void insertAfter(Node<Item> node1, Node<Item> node2) {
		if (node1 == null || node2 == null)
			return;
		node2.next = node1.next;
		node1.next = node2;
		size++;
	}
	
	public String toString() {
		Node<Item> tmp = head;
		String sum = "";
		while(tmp !=null) {
			sum += tmp.item + " ";
			tmp = tmp.next;
		}
		return sum;
	}
}