import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Bag�� �׸��� ������ �� ���� �÷����̴�.
// �׸��� �����ϰ�, ������ �׸���� ��ȸ�� �� �ִ� ������ �����Ѵ�.(�׸� ����, ����ִ��� ���ε� �˷��ش�.)
// �׸���� � ������ ��ȸ�ϴ��� ������� �ʴ´�. �׸��� ������ Ư���� �ǹ̰� ���ٸ� ���� �̿��Ѵ�.
// StdStatics Ŭ������ �������� Bag Ŭ���̾�Ʈ�� ���̴�. �Է��� �޾Ƽ� ��� ǥ����ġ ���� ���Ѵ�. ������ ��� ����.

class Bag<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
	}
	private Node first;
	private int size;

	public void add(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	public boolean isEmpty() {
		return size == 0; // first == null
	}
	public int size() {
		return size;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		Node crnt = first;
		
		@Override
		public boolean hasNext() {
			return crnt != null;
		}

		@Override
		public Item next() {
			Item item = crnt.item;
			crnt = crnt.next;
			return item;
		}
		
		public void remove() {}
		
	}

	public static void main(String[] args) {
		// Bag�� �������� Ŭ���̾�Ʈ
		Bag<Double> numbers = new Bag<Double>();
		while (!StdIn.isEmpty()) {
			numbers.add(StdIn.readDouble());
		}
		int N = numbers.size();

		double sum = 0.0;
		for (double x : numbers) {
			sum += x;
		}
		double mean = sum / N;

		sum = 0.0;
		for (double x : numbers) {
			sum += (x - mean) * (x - mean);
		}
		double std = Math.sqrt(sum) / (N - 1);

		StdOut.printf("Mean : %.2f\n", mean);
		StdOut.printf("Std dev : %.2f\n", std);
	}

}