import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Bag은 항목을 삭제할 수 없는 컬렉션이다.
// 항목을 수집하고, 수집된 항목들을 순회할 수 있는 도구를 제공한다.(항목 개수, 비어있는지 여부도 알려준다.)
// 항목들을 어떤 순서로 순회하는지 상관하지 않는다. 항목의 순서에 특별히 의미가 없다면 백을 이용한다.
// StdStatics 클래스가 전형적인 Bag 클라이언트의 예이다. 입력을 받아서 평균 표준편치 등을 구한다. 순서는 상관 없다.

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
		// Bag의 전형적인 클라이언트
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