// 1.3.29
// 원형 연결 리스트를 사용하는 큐를 작성하라.
// 기존 큐와 같지만 null 링크가 없고, last.next가 첫 번째 노드를 가리킨다는 것만 다르다.
// 인스턴스 변수는 last 노드만 사용하여 구연해보라.
class CircularArrayList<Item> {
	private Node<Item> head;
	private Node<Item> tail;
	private int size;

	void addFirst(Item item) {
		if (size == 0) {
			head = tail = new Node<Item>(item, head);
		} else {
			Node<Item> newHead = new Node<Item>(item, head);
			tail.next = newHead;
			head = newHead;
		}
		size++;
	}

}

public class Exercise01_03_29 {

}
