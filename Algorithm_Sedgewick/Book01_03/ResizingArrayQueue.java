import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
	private Item[] que;
	private int size;
	private int head;
	private int tail;

	ResizingArrayQueue() {
		que = (Item[]) new Object[2];
		size = 0;
		head = 0;
		tail = 0;
	}
	
	private void resize(int capacity) {
		Item[] newQue = (Item[]) new Object[capacity];
		if(head < tail) { // [head, tail)
			for(int i = head; i < tail; i++) {
				newQue[i - head] = que[i];
			}
			tail = tail - head;
		} else { // [0, tail), [head, que.length)
			for(int i = head; i < que.length; i++) {
				newQue[i - head] = que[i];
			} for(int i = 0; i < tail; i++) {
				newQue[que.length - head + i] = que[i];
			}
			tail = que.length - head + tail;
		}
		head = 0;
		que = newQue;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item) {
		size++;
		if(size == que.length)
			resize(que.length * 2);
		que[tail] = item;
		tail = (tail == que.length - 1) ? 0 : (tail + 1);
	}
	
	public Item dequeue() {
		size--;
		if(size <= que.length / 4)
			resize(que.length / 2);
		Item item = que[head];
		que[head] = null;
		head = (head == que.length - 1) ? 0 : (head + 1);
		return item;
	}

	public Item peak() {
		return que[head];
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item> {
		int crnt = head;
		@Override
		public boolean hasNext() {
			if (head < tail) { // [head, tail)
				return crnt >= head && crnt < tail;
			} else { // [0, tail), [head, que.length)
				return crnt >= head || crnt < tail;
			}
		}

		@Override
		public Item next() {
			Item item = que[crnt];
			crnt = (crnt == que.length - 1) ? 0 : (crnt + 1);
			return item;
		}
		
	}

}
