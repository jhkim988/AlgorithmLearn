import java.util.Comparator;

class AryCircularLinkedList<E> {
	class Node<E> {
		private E data;
		private int next;
		private int dnext;

		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;
	private int size;
	private int max;
	private int head;
	private int crnt;
	private int tail;
	private int deleted;
	private static final int NULL = -1;

	public AryCircularLinkedList(int capacity) {
		tail = head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for (int i = 0; i < capacity; ++i)
				n[i] = new Node<E>();
			size = capacity;
		} catch (OutOfMemoryError e) {
			size = 0;
		}
	}

	// ������ �����ϴ� record�� �ε����� ����
	private int getInsertIndex() {
		if (deleted == NULL) { // free ����Ʈ�� ����ִٸ�
			if (max < size) // �� ���ڵ带 ����Ѵ�.
				return max++;
			else // �뷮�� ���ļ� �� ���ڵ� ����� ����
				return NULL;
		} else { // free ����Ʈ�� ������� �ʴٸ�
			int rec = deleted; // free ����Ʈ�� �Ӹ���带 ������ ���ڵ�� ����Ѵ�.
			deleted = n[rec].dnext; // free ����Ʈ �Ӹ������ ���� ��带 �Ӹ��� ����
			return rec;
		}
	}

	// record idx�� free ����Ʈ�� ���
	private void deleteIndex(int idx) {
		if (deleted == NULL) { // free ���ڵ尡 ���������
			deleted = idx; // idx�� free ���ڵ��� �Ӹ� ����
			n[idx].dnext = NULL;
		} else {
			int rec = deleted;
			deleted = idx;
			n[idx].dnext = rec;
		}
	}

	// ��带 �˻�
	public E search(E obj, Comparator<? super E> c) {
		if (head != NULL) {
			int ptr = head;
			do {
				if (c.compare(obj, n[ptr].data) == 0) {
					crnt = ptr;
					return n[ptr].data;
				}
				ptr = n[ptr].next;
			} while (ptr != head);
		}
		return null;
	}

	// �Ӹ��� ��带 ����
	public void addFirst(E obj) {
		boolean empty = (tail == NULL);

		int ptr = head;
		int rec = getInsertIndex();
		if (rec != NULL) {
			head = crnt = rec;
			if (empty)
				tail = rec;
			n[rec].set(obj, ptr);
		}
	}

	// ������ ��带 ����
	public void addLast(E obj) {
		if (head == NULL) {
			addFirst(obj);
		} else {
//			int ptr = head;
//			while (n[ptr].next != NULL)
//				ptr = n[ptr].next;
//			int rec = getInsertIndex();
//			if (rec != NULL) {
//				n[ptr].next = crnt = tail = rec;
//				n[rec].set(obj, rec);
//			}
			int rec = getInsertIndex();
			if (rec != NULL) {
				n[tail].next = crnt = rec;
				n[rec].set(obj, NULL);
				tail = rec;
			}
		}
	}

	// �Ӹ���带 ����
	public void removeFirst() {
		if (head != NULL) {
			int ptr = n[head].next;
			deleteIndex(head);
			head = crnt = ptr;
			if (head == NULL)
				tail = NULL;
		}
	}

	// ������带 ����
	public void removeLast() {
		if (head != NULL) {
			if (n[head].next == NULL) {
				removeFirst();
			} else {
				int ptr = head;
				int pre = head;
				while (n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = NULL;
				deleteIndex(ptr);
				tail = crnt = pre;
			}
		}
	}

	// record p�� ����
	public void remove(int p) {
		if (head != NULL) {
			if (p == head) {
				removeFirst();
			} else if (p == tail) {
				removeLast();
			} else {
				int ptr = head;
				while (n[ptr].next != p) {
					ptr = n[ptr].next;
					if (ptr == NULL)
						return;
				}
//				n[ptr].next = NULL; // why?
				deleteIndex(p);
				n[ptr].next = n[p].next;
				crnt = ptr;
			}
		}
	}

	// ���� ��带 ����
	public void removeCurrentNode() {
		remove(crnt);
	}

	// ��� ��带 ����
	public void clear() {
		while (head != NULL)
			removeFirst();
		crnt = NULL;
	}

	// ���� ��带 �ϳ� �������� �̵�
	public boolean next() {
		if (crnt == NULL || n[crnt].next == NULL)
			return false;
		crnt = n[crnt].next;
		return true;
	}

	// ���� ��带 ���
	public void printCurrentNode() {
		if (crnt == NULL)
			System.out.println("������ ��尡 �����ϴ�.");
		else
			System.out.println(n[crnt].data);
	}

	// ��� ��带 ���
	public void dump() {
		int ptr = head;
		while (ptr != NULL) {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		}
	}

	// Q4 purge method
	public void purge(Comparator<? super E> c) {
		int ptr1 = head;

		while (ptr1 != NULL) {
			int pre = ptr1;
			int ptr2 = ptr1;
			int count = 0;

			// remove �޼��带 �̿��ϸ� ���ο��� ������ �� �� �� ���ƾ��Ѵ�..
			while (n[ptr2].next != NULL) {
				ptr2 = n[pre].next;
				if (c.compare(n[ptr1].data, n[ptr2].data) == 0) {
					n[pre].next = n[ptr2].next;
					count++;
				} else {
					pre = ptr2;
				}
			}
			if (count > 0) {
				int temp = n[ptr1].next;
				remove(ptr1);
				ptr1 = temp;
			} else
				ptr1 = n[ptr1].next;
		}
	}

	// Q5 retreive
	public E retrieve(int num) {
		int ptr = head;
		while (num-- >= 0 && n[ptr].next != NULL) {
			if (num == 0) {
				crnt = ptr;
				return n[ptr].data;
			}
			ptr = n[ptr].next;
		}
		return null;
	}
}