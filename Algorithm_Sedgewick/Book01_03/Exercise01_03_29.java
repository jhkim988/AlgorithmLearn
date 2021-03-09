// 1.3.29
// ���� ���� ����Ʈ�� ����ϴ� ť�� �ۼ��϶�.
// ���� ť�� ������ null ��ũ�� ����, last.next�� ù ��° ��带 ����Ų�ٴ� �͸� �ٸ���.
// �ν��Ͻ� ������ last ��常 ����Ͽ� �����غ���.
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
