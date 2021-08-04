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

	// 다음에 삽입하는 record의 인덱스를 구함
	private int getInsertIndex() {
		if (deleted == NULL) { // free 리스트가 비어있다면
			if (max < size) // 새 레코드를 사용한다.
				return max++;
			else // 용량이 넘쳐서 새 레코드 사용을 못함
				return NULL;
		} else { // free 리스트가 비어있지 않다면
			int rec = deleted; // free 리스트의 머리노드를 꺼내서 레코드로 사용한다.
			deleted = n[rec].dnext; // free 리스트 머리노드의 다음 노드를 머리로 설정
			return rec;
		}
	}

	// record idx를 free 리스트에 등록
	private void deleteIndex(int idx) {
		if (deleted == NULL) { // free 레코드가 비어있으면
			deleted = idx; // idx를 free 레코드의 머리 노드로
			n[idx].dnext = NULL;
		} else {
			int rec = deleted;
			deleted = idx;
			n[idx].dnext = rec;
		}
	}

	// 노드를 검색
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

	// 머리에 노드를 삽입
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

	// 꼬리에 노드를 삽입
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

	// 머리노드를 삭제
	public void removeFirst() {
		if (head != NULL) {
			int ptr = n[head].next;
			deleteIndex(head);
			head = crnt = ptr;
			if (head == NULL)
				tail = NULL;
		}
	}

	// 꼬리노드를 삭제
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

	// record p를 삭제
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

	// 선택 노드를 삭제
	public void removeCurrentNode() {
		remove(crnt);
	}

	// 모든 노드를 삭제
	public void clear() {
		while (head != NULL)
			removeFirst();
		crnt = NULL;
	}

	// 선택 노드를 하나 뒤쪽으로 이동
	public boolean next() {
		if (crnt == NULL || n[crnt].next == NULL)
			return false;
		crnt = n[crnt].next;
		return true;
	}

	// 선택 노드를 출력
	public void printCurrentNode() {
		if (crnt == NULL)
			System.out.println("선택한 노드가 없습니다.");
		else
			System.out.println(n[crnt].data);
	}

	// 모든 노드를 출력
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

			// remove 메서드를 이용하면 내부에서 루프를 한 번 더 돌아야한다..
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