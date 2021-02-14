import java.util.Comparator;

public class LinkedList<E> {
	// Q3 아래 그림처럼 머리 노드에 대한 참조 head에 덧붙여 꼬리 노드에 대한 참조 tail을 도입하면 꼬리 노드를 쉽게 검색할 수 있습니다.
	// LinkedList<E>가 제공하는 메서드와 같은 메서드를 모두 만드세요
	class Node<E> {
		private E data;
		private Node<E> next; // 뒤쪽 포인터, 꼬리노드의 뒤쪽 포인터 값은 NULL
		// 자기 참조형(self-referential)

		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head; // 머리노드
	private Node<E> crnt; // 선택노드
	private Node<E> tail;

	// 노드 0개 head = NULL
	// 노드 1개 head -> Node(data A, head.next)
	// 노드 2개 head -> Node(data B, head.next)
	// head.next -> Node(data B, head.next.next)

	public LinkedList() {
		tail = head = crnt = null; // 노드가 없는 비어있는 연결 리스트
		// head가 머리 노드에 대한 참조이지 머리 노드 그 자체가 아님을 주의
	}

	// 리스트가 비어있는지 판단: head == null
	// 연결 리스트의 노드가 1개인지 판단: head.next == null
	// 연결 리스트의 노드가 2개인지 판단: head.next.next == null;
	// 꼬리 노드인지 판단 : Node<E>형 변수 p가 꼬리노드 p.next == null

	// search method
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head;
		while (ptr != null) {
			if (c.compare(obj, ptr.data) == 0) { // 검색 성공
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // 다음 노드를 선택
		}
		return null;
	}

	// 머리에 노드를 삽입하는 addFirst 메서드
	public void addFirst(E obj) {
		boolean empty = (tail == null);
		Node<E> ptr = head;
		head = crnt = new Node<E>(obj, ptr);
	
		if (empty)
			tail = crnt;
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
			tail.next = crnt = new Node<E>(obj, null);
			tail = crnt;
		}
	}

	// 머리 노드를 삭제하는 removeFirst 메서드
	public void removeFirst() {
		if (head != null)
			head = crnt = head.next;
		if (head == null)
			tail = null;
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
				pre.next = null;
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
		Node<E> ptr = head;
		while (ptr != null) {
			System.out.println(crnt.data);
			crnt = ptr.next;
		}
	}
	
	// Q1 comparator c 메서드를 사용해 서로 같은 노드를 찾아 모두 삭제하는 다음 메서드를 작성하세요
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
	
	// Q2 머리부터 n개 뒤의 노드에 대한 참조를 반환하는 다음의 메서드를 작성하세요
	// n이 음수거나 노드 개수보다 크거나 같으면 null을 반환합니다.
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