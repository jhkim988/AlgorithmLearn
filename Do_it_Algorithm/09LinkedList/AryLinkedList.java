import java.util.Comparator;

class AryLinkedList<E> {
	// �����ͷ� ���Ḯ��Ʈ -> ��� ���� ������ ������ �̵� ���� ����,
	// �׷��� ���� ������ ������ ������ ���� ��ü�� ���� �޸� ������ ����� �����ϴ� ������ �ʿ��ߴ�.
	// ������ ���� ũ�� �ٲ��� �ʰ�, ������ ���� �ִ��� �̸� �� �� �ִٸ� �迭�� ����� ȿ�������� ���� ����Ʈ�� ����� �� �ִ�.

	// �迭 �ε��� 0 1 2 3 4 5 6 7
	// �迭 ��Ұ� D A E C B F - -
	// Ŀ�� 2 4 5 0 3 -1

	// ���� ����� �̿��ϸ� ����� ����, ���� �� ��Ҹ� �ű� �ʿ䰡 ����.

	// ���ڵ� - �迭 �ε��� n�� ��ҿ� ��� �ִ� ��带 n ��° ���ڵ�� �θ���.
	// ������ ���� �� �ݺ��ϸ� �迭 ���� �� ���ڵ� �����̰� �ȴ�.
	// �̸� �����ϱ� ���Ͽ� free list�� ���� �����.

	// dnext: ���� ����Ʈ�� ���� ��带 ����Ű�� Ŀ��
	// deleted: ���� ����Ʈ�� �Ӹ� ��带 ����Ű�� Ŀ��
	// max: �迭�� ���� ���� �ʿ� ����ִ� ����� ���ڵ� ��ȣ

	// Q6 tail �߰�
	class Node<E> {
		private E data;
		private int next; // ����Ʈ�� ���� ������
		private int dnext; // free ����Ʈ�� ���� ������

		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;
	private int size; // ����Ʈ�� �뷮(���� ū ������ ��)
	private int max; // ������� ���� record
	private int head;
	private int crnt;
	private int tail;
	private int deleted; // free ����Ʈ�� �Ӹ� ���
	private static final int NULL = -1; // ���� ��� ���� / ����Ʈ�� ���� ��

	public AryLinkedList(int capacity) {
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
		int ptr = head;
		while (ptr != NULL) {
			if (c.compare(obj, n[ptr].data) == 0) {
				crnt = ptr;
				return n[ptr].data;
			}
			ptr = n[ptr].next;
		}
		return null;
	}

	// �Ӹ��� ��带 ����
	public void addFirst(E obj) {
		int rec = getInsertIndex();
		if (rec != NULL) {
			if (head == NULL) {
				head = crnt = tail = rec;
				n[head].set(obj, rec);
			} else {
				int ptr = head;
				head = crnt = rec;
				n[rec].next = ptr;
				n[tail].next = head;
			}
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
				n[rec].set(obj, head);
				tail = rec;
			}
		}
	}

	// �Ӹ���带 ����
	public void removeFirst() {
		if (head != NULL) {
			if (head == tail) {
				deleteIndex(head);
				head = crnt = tail = NULL;
			} else {
				int ptr = n[head].next;
				deleteIndex(head);
				head = crnt = ptr;
				n[tail].next = head;
			}
		}
	}

	// ������带 ����
	public void removeLast() {
		if (head != NULL) {
			if (head == tail) {
				removeFirst();
			} else {
				int ptr = head;
				int pre = head;
				while (n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = head;
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
		if (head == NULL)
			return;
		int ptr = head;
		do {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		} while (ptr != head);

	}

	// Q4 purge method
	public void purge(Comparator<? super E> c) {
		if (head == NULL)
			return;

		int ptr1 = head;

		do {
			int pre = ptr1;
			int ptr2 = ptr1;
			int count = 0;

			// remove �޼��带 �̿��ϸ� ���ο��� ������ �� �� �� ���ƾ��Ѵ�..
			while (n[ptr2].next != head) {
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
		} while (ptr1 != head);
	}

	// Q5 retreive
	public E retrieve(int num) {
		if (head != NULL) {
			int ptr = head;
			do {
				if (num == 0) {
					crnt = ptr;
					return n[ptr].data;
				}
				ptr = n[ptr].next;
			} while (num-- >= 0 && n[ptr].next != head);
		}
		return null;
	}
}