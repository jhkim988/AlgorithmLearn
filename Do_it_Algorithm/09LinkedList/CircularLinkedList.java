import java.util.Comparator;

public class CircularLinkedList<E> {
	class Node<E> {
		private E data;
		private Node<E> next;

		Node(E data) {
			this.data = data;
			this.next = this;
		}

		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> crnt;
	private Node<E> tail;

	public CircularLinkedList() {
		tail = head = crnt = null;
	}

	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head;
		if (ptr != null) {
			do {
				if (c.compare(obj, ptr.data) == 0) { // �˻� ����
					crnt = ptr;
					return ptr.data;
				}
				ptr = ptr.next; // ���� ��带 ����
			} while (ptr != head);
		}
		return null;
	}

	// �Ӹ��� ��带 �����ϴ� addFirst �޼���
	public void addFirst(E obj) {
		if (head == null)
			head = tail = crnt = new Node<E>(obj);
		else {
			Node<E> ptr = head;
			head = crnt = new Node<E>(obj, ptr);
			tail.next = head;
		}
	}

	// ������ ��带 �����ϴ� addLast �޼���
	public void addLast(E obj) {
		// ����Ʈ�� ��������� addFirst�� ����.
		if (head == null)
			addFirst(obj);
		else {
//			Node<E> ptr = head;
//			while (ptr.next != null)
//				ptr = ptr.next;
//			ptr.next = crnt = new Node<E>(obj, null);
			Node<E> ptr = tail;
			ptr.next = crnt = tail = new Node<E>(obj, head);
		}
	}

	// �Ӹ� ��带 �����ϴ� removeFirst �޼���
	public void removeFirst() {
		if (head != null)
			if (head == tail) {
				head = crnt = tail = null;
			} else {
				tail.next = head = crnt = head.next;
			}

	}

	// ���� ��带 �����ϴ� removeLast �޼���
	public void removeLast() {
		if (head != null) {
			if (head.next == null)
				removeFirst();
			else {
				// tail�� ���� ��带 ã�� ����� �����Ƿ� head���� ���������� �� �ۿ� ����..
				Node<E> ptr = head;
				Node<E> pre = head;

				while (ptr.next != null) {
					pre = ptr;
					ptr = ptr.next;
				}
				pre.next = head;
				tail = crnt = pre;
			}
		}
	}

	// ������ ��带 �����ϴ� remove �޼���
	public void remove(Node<E> p) {
		if (head != null) {
			if (p == head)
				removeFirst();
			else if (p == tail)
				removeLast();
			else {
				Node<E> ptr = head;

				while (ptr.next != p) {
					ptr = ptr.next;
					if (ptr == null)
						return; // p�� ����Ʈ�� �����ϴ�.
				}
				ptr.next = p.next;
				crnt = ptr;
			}
		}
	}

	// ������ ��带 ����
	public void removeCurrentNode() {
		remove(crnt);
	}

	// ��� ��带 ����
	public void clear() {
		while (head != null)
			removeFirst();
		crnt = null;
	}

	// ������ ��带 �ϳ� �� ������ �̵�
	public boolean next() {
		if (crnt == null || crnt.next == null)
			return false;
		crnt = crnt.next;
		return true;
	}

	// ���� ��带 ���
	public void printCurrentNode() {
		if (crnt == null)
			System.out.println("������ ��尡 �����ϴ�.");
		else
			System.out.println(crnt.data);
	}

	// ��� ��带 ���
	public void dump() {
		if (head != null) {
			Node<E> ptr = head;
			do {
				System.out.println(crnt.data);
				crnt = ptr.next;
			} while (ptr != head);
		}
	}

	// Q1 comparator c �޼��带 ����� ���� ���� ��带 ã�� ��� �����ϴ� ���� �޼��带 �ۼ��ϼ���
	void purge(Comparator<? super E> c) {
		if (head == null)
			return;

		Node<E> ptr = head;

		do {
			int count = 0;
			Node<E> ptr2 = ptr;
			Node<E> pre = ptr;

			while (pre.next != head) {
				ptr2 = pre.next;
				if (c.compare(ptr.data, ptr2.data) == 0) {
					pre.next = ptr2.next;
					count++;
				} else {
					pre = ptr2;
				}
			}
			if (count == 0) {
				ptr = ptr.next;
			} else {
				Node<E> temp = ptr;
				remove(ptr);
				ptr = temp.next;
			}
		} while (ptr != head);
	}

	// Q2 �Ӹ����� n�� ���� ��忡 ���� ������ ��ȯ�ϴ� ������ �޼��带 �ۼ��ϼ���
	// n�� �����ų� ��� �������� ũ�ų� ������ null�� ��ȯ�մϴ�.
	E retrieve(int n) {
		if (head != null) {
			Node<E> ptr = head;
			do {
				if (n-- == 0) {
					crnt = ptr;
					return ptr.data;
				}
				ptr = ptr.next;
			} while (n >= 0 && ptr != head);
		}
		return null;
	}
}