import edu.princeton.cs.algs4.In;

// ���Լ���(FIFO, First in First out)
// �������� �÷��ǿ� �����ϸ鼭, ������ ���� ����� ������ �����ϰ� ���� ��
public class Queue<Item> {
	private Node first;
	private Node last;
	private int size;
	private class Node {
		Item item;
		Node next;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void enqueue(Item item) {
		Node oldLast = last;
		Node last = new Node();
		last.item = item;
		oldLast.next = last;
	}
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		return item;
	}
	public static void main(String[] args) {
		// Queue Ŭ���̾�Ʈ �� - readAllInts
		In in = new In(args[0]);
		Queue<Integer> q = new Queue<Integer>();
		while(!in.isEmpty()) {
			q.enqueue(in.readInt());
		}
		int N = q.size();
		int[] a = new int[N];
		for(int i = 0; i < N; i++) {
			a[i] = q.dequeue();
		}
		// return a;
	}
}
