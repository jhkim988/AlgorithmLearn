import java.util.Comparator;

public class LinkedList<E> {
	// Q3 �Ʒ� �׸�ó�� �Ӹ� ��忡 ���� ���� head�� ���ٿ� ���� ��忡 ���� ���� tail�� �����ϸ� ���� ��带 ���� �˻��� �� �ֽ��ϴ�.
	// LinkedList<E>�� �����ϴ� �޼���� ���� �޼��带 ��� ���弼��
	class Node<E> {
		private E data;
		private Node<E> next; // ���� ������, ��������� ���� ������ ���� NULL
		// �ڱ� ������(self-referential)

		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head; // �Ӹ����
	private Node<E> crnt; // ���ó��
	private Node<E> tail;

	// ��� 0�� head = NULL
	// ��� 1�� head -> Node(data A, head.next)
	// ��� 2�� head -> Node(data B, head.next)
	// head.next -> Node(data B, head.next.next)

	public LinkedList() {
		tail = head = crnt = null; // ��尡 ���� ����ִ� ���� ����Ʈ
		// head�� �Ӹ� ��忡 ���� �������� �Ӹ� ��� �� ��ü�� �ƴ��� ����
	}

	// ����Ʈ�� ����ִ��� �Ǵ�: head == null
	// ���� ����Ʈ�� ��尡 1������ �Ǵ�: head.next == null
	// ���� ����Ʈ�� ��尡 2������ �Ǵ�: head.next.next == null;
	// ���� ������� �Ǵ� : Node<E>�� ���� p�� ������� p.next == null

	// search method
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head;
		while (ptr != null) {
			if (c.compare(obj, ptr.data) == 0) { // �˻� ����
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // ���� ��带 ����
		}
		return null;
	}

	// �Ӹ��� ��带 �����ϴ� addFirst �޼���
	public void addFirst(E obj) {
		boolean empty = (tail == null);
		Node<E> ptr = head;
		head = crnt = new Node<E>(obj, ptr);
	
		if (empty)
			tail = crnt;
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
			tail.next = crnt = new Node<E>(obj, null);
			tail = crnt;
		}
	}

	// �Ӹ� ��带 �����ϴ� removeFirst �޼���
	public void removeFirst() {
		if (head != null)
			head = crnt = head.next;
		if (head == null)
			tail = null;
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
				pre.next = null;
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
		Node<E> ptr = head;
		while (ptr != null) {
			System.out.println(crnt.data);
			crnt = ptr.next;
		}
	}
	
	// Q1 comparator c �޼��带 ����� ���� ���� ��带 ã�� ��� �����ϴ� ���� �޼��带 �ۼ��ϼ���
	void purge(Comparator <? super E> c) {
		Node<E> ptr = head;
		while(ptr != null) {
			int count = 0;
			Node<E> ptr2 = ptr;
			Node<E> pre = ptr;
			
			while(pre.next != null) {
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
		}
	}
	
	// Q2 �Ӹ����� n�� ���� ��忡 ���� ������ ��ȯ�ϴ� ������ �޼��带 �ۼ��ϼ���
	// n�� �����ų� ��� �������� ũ�ų� ������ null�� ��ȯ�մϴ�.
	E retrieve(int n) {		
		Node<E> ptr = head;
		while(n >= 0 && ptr != null) {
			if (n-- == 0) {
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next;
		}
		return null;
	}
}