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

	private Node<E> head; // ���̳��
	private Node<E> crnt;

	public DblLinkedList() {
		head = crnt = new Node<E>();
	}

	public boolean isEmpty() {
		// head�� next�� ���̳���� head�� ����Ų�ٸ� �� ����Ʈ
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
	// p.prev == head; // p�� ����Ű�� ��尡 �Ӹ� ������� Ȯ���մϴ�.(���� ��� ����)
	// p.prev.prev = head; // p�� ����Ű�� ��尡 �Ӹ��������� �� ��° ������� Ȯ���մϴ�.(���� ��� ����)
	// p.next = head; // p�� ����Ű�� ��尡 ����������� Ȯ���մϴ�.
	// p.next.next = head; // p�� ����Ű�� ��尡 ������������ �� ��° ������� Ȯ���մϴ�.

	public void printCurrentNode() {
		if (isEmpty())
			System.out.println("���� ��尡 �����ϴ�.");
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

	// ���� ��� �ٷ� �ڿ� ��带 ����
	public void add(E obj) {
		// head�� �׻� ���̳��� �ֱ� ������, ����Ʈ�� ������� ���� �Ӹ�/������ �����ϴ� �� ���� �ٷ� �ʿ䰡 ����.
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

	public void removeCurrentNode() { // ���� ��带 ����� �ȵǱ� ������ isEmpty()�� Ȯ���Ѵ�.
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