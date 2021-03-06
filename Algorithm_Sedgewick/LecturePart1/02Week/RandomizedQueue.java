import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue;
	private int head;
	private int tail;
	private int size;
//	private int nullNum;

	// construct an empty randomized queue
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		head = 0;
		tail = 0;
		size = 0;
//		nullNum = 0;
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return head == tail;
	}

	// return the number of items on the randomized queue
	public int size() {
		return size;
	}

	private void resize(int capacity) {
		// copy to head ~ tail -> 0 ~ idx
		// initial value - null
//		StdOut.println("resize");
		Item[] newQueue = (Item[]) new Object[capacity];
		int pointer = 0;
		while (pointer < size) {
//			StdOut.println("INFINITE LOOP - resize");
			if (queue[head] != null)
				newQueue[pointer++] = queue[head];
			head++;
			if (head == queue.length)
				head = 0;
		}
		head = 0;
		tail = size;
		queue = newQueue;
//		nullNum = 0;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
//		if ((size() + nullNum) >= queue.length - 1)
//			resize(2 * queue.length);
		if (size() >= queue.length - 1)
			resize(2 * queue.length);
		queue[tail] = item;
		tail = (tail == queue.length - 1) ? 0 : tail + 1;
		size++;
	}

	private void swap(Item[] que, int idx1, int idx2) {
		Item temp = que[idx1];
		que[idx1] = que[idx2];
		que[idx2] = temp;
	}

	// remove and return a random item
	public Item dequeue() {
		// random access -> use array...
		// too many call StdRandom
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (size < queue.length / 4)
			resize(queue.length / 2);
//		int rand = StdRandom.uniform(size + nullNum);
//		while (queue[(head + rand) % (queue.length)] == null) {
////			StdOut.println("INFINITE LOOP - deque 1");
//			rand = StdRandom.uniform(size + nullNum);
//		}
		int rand = StdRandom.uniform(size);
		int idx = (head + rand) % (queue.length);
		Item deqItem = queue[idx];
		queue[(head + rand) % (queue.length)] = null;
		size--;
		swap(queue, head, idx);
		head = (head == queue.length - 1) ? 0 : head + 1;
//		nullNum++;

//		int prevTail = (tail == 0) ? queue.length - 1 : tail - 1;
//		while (queue[prevTail] == null && tail != head) {
//			tail = (tail == 0) ? queue.length - 1 : tail - 1;
//			prevTail = (tail == 0) ? queue.length - 1 : tail - 1;
//			nullNum--;
////			StdOut.println("INFINITE LOOP - deque 2 tail : " + tail + " \tprevTail : " + prevTail + " \tsize : " + size);
//		}
//		while (queue[head] == null && tail != head) {
////			StdOut.println("INFINITE LOOP - deque 3");
//			head = (head == queue.length - 1) ? 0 : head + 1;
//			nullNum--;
//		}

		return deqItem;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
//		int rand = StdRandom.uniform(size + nullNum);
//		while (queue[(head + rand) % queue.length] == null) {
////			StdOut.println("INFINITE LOOP - sample");
//			rand = StdRandom.uniform(size + nullNum);
//		}
		int rand = StdRandom.uniform(size);
		return queue[(head + rand) % queue.length];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new IteratorRandomizedQueue();
	}

	private class IteratorRandomizedQueue implements Iterator<Item> {
		int crnt;
		int[] shuffle; // 0 ~ size - 1

		IteratorRandomizedQueue() {
			crnt = head;
			shuffle = new int[size];

			// initialized
			for (int i = 0; i < shuffle.length; i++)
				shuffle[i] = i;
			knuthShuffle(shuffle);
		}

		// shuffle
		private void knuthShuffle(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				int r = i + StdRandom.uniform(arr.length - i);
				int temp = arr[i];
				arr[i] = arr[r];
				arr[r] = temp;
			}
		}

		@Override
		public boolean hasNext() {
			if (size == 0)
				return false;
			return (head < tail) ? (crnt >= head && crnt < tail) : (crnt >= head || crnt < tail);
		}

		@Override
		public Item next() {
			if (size == 0 || !hasNext())
				throw new NoSuchElementException();
			Item queItem;
			if (head < tail) {
				queItem = queue[shuffle[crnt - head] + head];
			} else { // tail < head
				if (crnt >= head) {
					queItem = queue[(shuffle[crnt - head] + head) % queue.length];
				} else {
					queItem = queue[(shuffle[queue.length - head + crnt] + head) % queue.length];
				}
			}
			
			if (crnt == queue.length - 1) {
				crnt = 0;
			} else {
				crnt++;
			}
			
			return queItem;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

//	public String toString() {
//		String sum = "";
//		for (int i = 0; i < queue.length; i++)
//			sum += queue[i] + " ";
//		return sum;
//	}

	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		StdOut.println("rq is " + (rq.isEmpty() ? "empty" : "not emty"));
		for (int i = 0; i <= 10; i = i + 5) {
			StdOut.println("size of rq : " + rq.size());
			rq.enqueue(1 + i);
			rq.enqueue(2 + i);
			rq.enqueue(3 + i);
			rq.enqueue(4 + i);
			rq.enqueue(5 + i);
//			StdOut.println("Dequeue : " + rq.dequeue());
//			StdOut.println("Dequeue : " + rq.dequeue());
//			StdOut.println("Dequeue : " + rq.dequeue());
//			StdOut.println("Sample : " + rq.sample());
//			StdOut.println("Sample : " + rq.sample());
//			StdOut.println("Sample : " + rq.sample());
		}
		rq.enqueue(316);
		rq.dequeue();
		StdOut.println(rq.isEmpty());
		StdOut.println(rq);
		StdOut.println(rq.head + " " + rq.tail);
		rq.enqueue(189);
		rq.dequeue();
		rq.sample();
		StdOut.println(rq.isEmpty());
		StdOut.println(rq.head + " " + rq.tail);
		StdOut.println("rq is " + (rq.isEmpty() ? "empty" : "not emty"));
		
		for(int i = 0; i < 10; i++) {
			rq.enqueue(i);
			rq.dequeue();
			StdOut.println(rq);
		}
		
		Iterator<Integer> iter = rq.iterator();
		while (iter.hasNext()) {
			StdOut.println(rq);
			Iterator<Integer> nestedIter = rq.iterator();
			while (nestedIter.hasNext())
				StdOut.println(nestedIter.next());
			StdOut.println(iter.next());
		}

	}

}
