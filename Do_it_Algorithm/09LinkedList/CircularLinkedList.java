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
				if (c.compare(obj, ptr.data) == 0) { // 검색 성공
					crnt = ptr;
					return ptr.data;
				}
				ptr = ptr.next; // 다음 노드를 선택
			} while (ptr != head);
		}
		return null;
	}

	// 머리에 노드를 삽입하는 addFirst 메서드
	public void addFirst(E obj) {
		if (head == null)
			head = tail = crnt = new Node<E>(obj);
		else {
			Node<E> ptr = head;
			head = crnt = new Node<E>(obj, ptr);
			tail.next = head;
		}
	}

	// 꼬리에 노드를 삽입하는 addLast 메서드
	public void addLast(E obj) {
		// 리스트가 비어있으면 addFirst와 같다.
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

	// 머리 노드를 삭제하는 removeFirst 메서드
	public void removeFirst() {
		if (head != null)
			if (head == tail) {
				head = crnt = tail = null;
			} else {
				tail.next = head = crnt = head.next;
			}

	}

	// 꼬리 노드를 삭제하는 removeLast 메서드
	public void removeLast() {
		if (head != null) {
			if (head.next == null)
				removeFirst();
			else {
				// tail의 이전 노드를 찾을 방법이 없으므로 head부터 순차접근할 수 밖에 없다..
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

	// 선택한 노드를 삭제하는 remove 메서드
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
						return; // p가 리스트에 없습니다.
				}
				ptr.next = p.next;
				crnt = ptr;
			}
		}
	}

	// 선택한 노드를 삭제
	public void removeCurrentNode() {
		remove(crnt);
	}

	// 모든 노드를 삭제
	public void clear() {
		while (head != null)
			removeFirst();
		crnt = null;
	}

	// 선택한 노드를 하나 뒤 쪽으로 이동
	public boolean next() {
		if (crnt == null || crnt.next == null)
			return false;
		crnt = crnt.next;
		return true;
	}

	// 선택 노드를 출력
	public void printCurrentNode() {
		if (crnt == null)
			System.out.println("선택한 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}

	// 모든 노드를 출력
	public void dump() {
		if (head != null) {
			Node<E> ptr = head;
			do {
				System.out.println(crnt.data);
				crnt = ptr.next;
			} while (ptr != head);
		}
	}

	// Q1 comparator c 메서드를 사용해 서로 같은 노드를 찾아 모두 삭제하는 다음 메서드를 작성하세요
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

	// Q2 머리부터 n개 뒤의 노드에 대한 참조를 반환하는 다음의 메서드를 작성하세요
	// n이 음수거나 노드 개수보다 크거나 같으면 null을 반환합니다.
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