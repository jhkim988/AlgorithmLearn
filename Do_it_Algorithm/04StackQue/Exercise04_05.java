class Exercise04_05 {
	private int max;
	private int front;
	private int rear;
	private int num; // 현재 데이터 수, que가 비었는지 가득찼는지 구분할 수 없기 때문에 필요하다.
	private int[] que;

	// 예외처리
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() {

		}
	}

	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() {
			
		}
	}

	public Exercise04_05 (int capacity) {
		max = capacity;
		front = 0;
		rear = 0;
		num = 0;
		try {
			que = new int[max];
		} catch (OutOfMemoryError e) {
			max = 0;
		}
	}

	public int enque(int x) throws OverflowIntQueueException {
		if (num >= max)
			throw new OverflowIntQueueException();
		que[rear++] = x;
		num++;
		if (rear == max)
			rear = 0;
		return x;
	}

	public int deque() throws EmptyIntQueueException {
		if (num <= 0)
			throw new EmptyIntQueueException();
		int x = que[front++];
		num--;
		if (front == max)
			front = 0;
		return x;
	}

	public int peek() throws EmptyIntQueueException {
		if (num == 0)
			throw new EmptyIntQueueException();
		return que[front];
	}

	public int indexOf(int x) {
		for (int i = 0; i < num; ++i) {
			int idx = (front + i) % max;
			if (que[idx] == x)
				return idx;
		}
		return -1;
	}
	
	// Q5. 클래스 IntQueue에 임의의 데이터를 검색하는 아래의 메서드를 추가하세요
	// 메서드 indexOf()처럼 찾은 위치의 배열 인덱스를 반환하는 것이 아니라
	// 큐 안에서 몇 번째에 있는가를 양수(큐의 프런트에 있는 경우는 1)로 반환합니다. 검색에 실패할 경우에는 0을 반환합니다.
	public int search(int x) {
		for (int i = 0; i < num; ++i)
			if (que[(i + front) % max] == x)
				return i + 1;
		return 0;
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
	
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}