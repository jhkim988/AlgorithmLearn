class Gqueue<E> {
	private int max;
	private int num;
	private int front;
	private int rear;
	private E[] que;

	// 예외처리
	static class EmptyGQueueException extends RuntimeException {
		public EmptyGQueueException() {

		}
	}

	static class OverflowGQueueException extends RuntimeException {
		public OverflowGQueueException() {

		}
	}

	Gqueue(int capacity) {
		max = capacity;
		rear = front = num = 0;
		que = (E[]) new Object[max];
	}

	E enque(E x) throws EmptyGQueueException {
		if (num >= max)
			throw new EmptyGQueueException();
		que[rear++] = x;
		num++;
		if (rear == max)
			rear = 0;
		return x;
	}

	E deque() throws OverflowGQueueException {
		if (num <= 0)
			throw new OverflowGQueueException();
		E x = que[front++];
		num--;
		if (front == max)
			front = 0;
		return x;
	}

	E peek() throws EmptyGQueueException {
		if (num <= 0)
			throw new EmptyGQueueException();
		return que[front];
	}
	
	int indexOf(E x) {
		for (int i = 0; i < num; ++i) {
			int idx = (front + i) % max;
			if (que[idx].equals(x))
				return idx;
		}
		return -1;
	}

	public void clear() {
		num = front = rear = 0;
	}

	public int capacity() {
		return max;
	}

	public int size() {
		return num;
	}

	public boolean isEmpty() {
		return num <= 0;
	}
	
	public boolean isFull() {
		return num >= max;
	}
	
	// front -> rear
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else
			for (int i = 0 ; i < num; ++i)
				System.out.print(que[(i + front) % max] + " ");
		System.out.println();
	}
}

class Exercise04_06 {
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}