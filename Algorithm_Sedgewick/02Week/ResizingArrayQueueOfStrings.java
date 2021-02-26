
public class ResizingArrayQueueOfStrings {
	private String s[];
	int head = 0;
	int tail = 0;

	public ResizingArrayQueueOfStrings() {
		s = new String[2];
	}

	private ResizingArrayQueueOfStrings(String[] strs, int head, int tail) { // create test case
		s = strs;
		this.head = head;
		this.tail = tail;
	}

	private void resize(int capacity) {
		String[] copy = new String[capacity];
		// double
		if (head < tail) {
			for (int i = head; i < tail; i++)
				copy[i] = s[i];
		} else {
			for (int i = head; i < s.length; i++)
				copy[i - head] = s[i];
			for (int i = 0; i < tail; i++)
				copy[s.length - head + i] = s[i];
		}
		head = 0;
		tail = size();
		s = copy;
	}

	private int size() {
		return (head <= tail) ? (tail - head) : (s.length - head + tail);
	}

	public boolean isEmpty() {
		// if array has fixed size, "head == tail" means that empty or full.
		// But we will resizing. if "head == tail" and "!isEmpty()", we halve array.
		return head == tail;
	}

	public String dequeue() {
		int size = size();
		if (size != 0 && s.length == size / 4)
			resize(2 * s.length);
		String item = s[head];
		s[head++] = null;
		if (head == s.length)
			head = 0;
//		dumpTest();
		return item;
	}

	public void enqueue(String item) {
		if (size() == s.length - 1)
			resize(2 * s.length);
		s[tail] = item;
		tail = (tail == s.length - 1) ? 0 : tail + 1;
//		dumpTest();
	}

	private void dumpTest() {
		System.out.print("head :" + head + " \ttail : " + tail + " \t");
		for (int i = 0; i < s.length; i++)
			System.out.print(s[i] + " ");
		System.out.println();
	}

	public void dump() {
		if (head < tail) {
			for (int i = head; i < tail; i++) {
				System.out.print(s[i] + " ");
			}
		} else {
			for (int i = head; i < s.length; i++)
				System.out.print(s[i] + " ");
			for (int i = 0; i < tail; i++)
				System.out.print(s[i] + " ");
		}
	}

	public static void main(String[] args) {
		// resizing test
		String[] s1 = { "hello", "world", "I", "am", "here", null, null, null, null, null, null, null, null, null };
		String[] s2 = { "hello", "world", null, null, null, null, null, null, null, null, null, "I", "am", "here" };
		ResizingArrayQueueOfStrings que1 = new ResizingArrayQueueOfStrings(s1, 0, 5);
		ResizingArrayQueueOfStrings que2 = new ResizingArrayQueueOfStrings(s2, 11, 2);
//		que1.resize(10);
//		que1.dump();
//		que2.resize(10);
//		que2.dump();

		// enqueue/dequeue test
		ResizingArrayQueueOfStrings que3 = new ResizingArrayQueueOfStrings();
		que3.dumpTest();
		for (int i = 0; i < 10; i++) { // Activate dumpTest in enqueue/dequeue function
			que3.enqueue("hello");
			que3.enqueue("world");
			que3.enqueue("my");
			que3.enqueue("name");
			que3.enqueue("is");
			que3.enqueue("JH");
			que3.enqueue("kim");
			que3.dequeue();
			que3.dequeue();
			que3.dequeue();
			que3.dequeue();
		}
	}
}
