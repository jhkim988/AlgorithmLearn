import java.util.Comparator;

public class DblLinkedList<E> {
	class Node<E> {
		private E data;
		private Node<E> prev;
		private Node<E> next;

		Node() {
			data = null;
			prev = next = this;
		}

		Node(E obj, Node<E> prev, Node<E> next) {
			data = obj;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head; // 더미노드
	private Node<E> crnt;

	public DblLinkedList() {
		head = crnt = new Node<E>();
	}

	public boolean isEmpty() {
		// head의 next가 더미노드인 head를 가리킨다면 빈 리스트
		return head.next == head;
	}

	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head.next;
		while (ptr != head) {
			if (c.compare(ptr.data, obj) == 0) {
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next;
		}
		return null;
	}

	// Node<E> p;
	// p.prev == head; // p가 가리키는 노드가 머리 노드인지 확인합니다.(더미 노드 제외)
	// p.prev.prev = head; // p가 가리키는 노드가 머리에서부터 두 번째 노드인지 확인합니다.(더미 노드 제외)
	// p.next = head; // p가 가리키는 노드가 꼬리노드인지 확인합니다.
	// p.next.next = head; // p가 가리키는 노드가 꼬리에서부터 두 번째 노드인지 확인합니다.

	public void printCurrentNode() {
		if (isEmpty())
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}

	public void dump() {
		Node<E> ptr = head.next;
		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}

	public void dumpReverse() {
		Node<E> ptr = head.prev;
		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.prev;
		}
	}

	public boolean next() {
		if (isEmpty() || crnt.next == head)
			return false;
		crnt = crnt.next;
		return true;
	}

	public boolean prev() {
		if (isEmpty() || crnt.prev == head)
			return false;
		crnt = crnt.prev;
		return true;
	}

	// 선택 노드 바로 뒤에 노드를 삽입
	public void add(E obj) {
		// head가 항상 더미노드로 있기 때문에, 리스트가 비어있을 때나 머리/꼬리에 삽입하는 걸 따로 다룰 필요가 없다.
		Node<E> node = new Node<E>(obj, crnt, crnt.next);
		crnt.next = node.next.prev = node;
		crnt = node;
	}

	public void addFirst(E obj) {
		crnt = head;
		add(obj);
	};

	public void addLast(E obj) {
		crnt = head.prev;
		add(obj);
	}

	public void removeCurrentNode() { // 더미 노드를 지우면 안되기 때문에 isEmpty()로 확인한다.
		if (isEmpty())
			return;
		crnt.prev.next = crnt.next;
		crnt.next.prev = crnt.prev;
		crnt = crnt.prev;
	}

	public void remove(Node<E> p) {
		Node<E> ptr = head.next;

		while (ptr != head) {
			if (ptr == p) {
				crnt = p;
				removeCurrentNode();
				break;
			}
			ptr = ptr.next;
		}
	}

	public void removeFirst() {
		crnt = head.next;
		removeCurrentNode();
	}

	public void removeLast() {
		crnt = head.prev;
		removeCurrentNode();
	}

	public void clear() {
		while (!isEmpty())
			removeFirst();
	}

	// Q9 purge method
	public void purge(Comparator<? super E> c) {
		Node<E> ptr1 = head.next;
		while (ptr1 != head) {
			Node<E> ptr2 = ptr1;
			int count = 0;
			while (ptr2 != head) {
				if (c.compare(ptr2.data, ptr1.data) == 0) {
					remove(ptr2);
					count++;
				}
				ptr2 = ptr2.next;
			}
			if (count > 0) {
				Node<E> temp = ptr1;
				remove(ptr1);
				ptr1 = temp.next;
			} else
				ptr1 = ptr1.next;
		}
	}

	// Q10 retreive method
	public E retreive(int n) {
		Node<E> ptr = head.next;
		while (n >= 0 && ptr != head) {
			if (n-- == 0) {
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next;
		}
		return null;
	}
}