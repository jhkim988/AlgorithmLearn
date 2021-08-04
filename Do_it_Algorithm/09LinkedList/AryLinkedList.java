import java.util.Comparator;

class AryLinkedList<E> {
	// 포인터로 연결리스트 -> 노드 삽입 삭제를 데이터 이동 없이 수행,
	// 그러나 삽입 삭제를 수행할 때마다 노드용 객체를 위한 메모리 영역을 만들고 해제하는 과정이 필요했다.
	// 데이터 수가 크게 바뀌지 않고, 데이터 수의 최댓값을 미리 알 수 있다면 배열을 사용해 효율적으로 연결 리스트를 운용할 수 있다.

	// 배열 인덱스 0 1 2 3 4 5 6 7
	// 배열 요소값 D A E C B F - -
	// 커서 2 4 5 0 3 -1

	// 위의 방법을 이용하면 노드의 삽입, 삭제 시 요소를 옮길 필요가 없다.

	// 레코드 - 배열 인덱스 n인 요소에 들어 있는 노드를 n 번째 레코드라 부른다.
	// 삭제를 여러 번 반복하면 배열 안은 빈 레코드 투성이가 된다.
	// 이를 관리하기 위하여 free list를 새로 만든다.

	// dnext: 프리 리스트의 다음 노드를 가리키는 커서
	// deleted: 프리 리스트의 머리 노드를 가리키는 커서
	// max: 배열의 가장 꼬리 쪽에 들어있는 노드의 레코드 번호

	// Q6 tail 추가
	class Node<E> {
		private E data;
		private int next; // 리스트의 뒤쪽 포인터
		private int dnext; // free 리스트의 뒤쪽 포인터

		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;
	private int size; // 리스트의 용량(가장 큰 데이터 수)
	private int max; // 사용중인 꼬리 record
	private int head;
	private int crnt;
	private int tail;
	private int deleted; // free 리스트의 머리 노드
	private static final int NULL = -1; // 다음 노드 없음 / 리스트가 가득 참

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

	// 머리에 노드를 삽입
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
				n[rec].set(obj, head);
				tail = rec;
			}
		}
	}

	// 머리노드를 삭제
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

	// 꼬리노드를 삭제
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

			// remove 메서드를 이용하면 내부에서 루프를 한 번 더 돌아야한다..
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