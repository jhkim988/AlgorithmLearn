class IntDeque {
	int max;
	int front;
	int rear;
	int num;
	int que[];

	class EmptyIntDequeException extends RuntimeException {
		EmptyIntDequeException () {
			
		}
	}

	class OverflowIntDequeException extends RuntimeException {
		OverflowIntDequeException () {
			
		}
	}

	IntDeque(int capacity) {
		max = capacity;
		num = front = rear = 0;
		que = new int[max];
	}

	int addFirst(int x) throws OverflowIntDequeException {
		if (num >= max)
			throw new OverflowIntDequeException();
		if (--front < 0)
			front = max - 1;
		num++;
		return que[front] = x;
	}

	int addLast(int x) throws OverflowIntDequeException {
		if (num >= max)
			throw new OverflowIntDequeException();
		que[rear++] = x;
		if (rear == max)
			rear = 0;
		num++;
		return x;
	}

	int removeFirst() throws EmptyIntDequeException {
		if (num <= 0)
			throw new EmptyIntDequeException();
		int x = que[front++];
		if (front == max)
			front = 0;
		num--;
		return x;
	}

	int removeLast() throws EmptyIntDequeException {
		if (num <= 0)
			throw new EmptyIntDequeException();
		if (--rear < 0)
			rear = max - 1;
		num--;
		return que[rear];
	}

	int peekFirst() throws EmptyIntDequeException {
		if (num <= 0)
			throw new EmptyIntDequeException();
		return que[front];
	}

	int peekLast() throws EmptyIntDequeException {
		if (num <= 0)
			throw new EmptyIntDequeException();
//		if (rear == 0)
//			return que[max - 1];
//		return que[rear - 1];
		return que[rear == 0 ? max - 1 : rear - 1];
	}

	int indexOf(int key) {
		for (int i = 0; i < num; ++i)
			if (que[(front + i) % max] == key)
				return (i + front) % max;
		return -1;
	}
	
	int searchOf(int key) {
		for (int i = 0; i < num; ++i)
			if (que[(front + i) % max] == key)
				return i + 1;
		return -1;
	}

	void clear() {
		num = front = rear = 0;
	}

	int capacity() {
		return max;
	}

	int size() {
		return num;
	}

	boolean isEmpty() {
		return num <= 0;
	}

	boolean isFull() {
		return num >= max;
	}

	// front -> rear
	void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else
			for (int i = 0; i < num; ++i)
				System.out.print(que[(i + front) % max] + " ");
		System.out.println();
	}
}

class Exercise04_07 {
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}