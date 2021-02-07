class IntStack {
	int max; // ������ �뷮, stk�� ��ڼ��� ����.
	int ptr; // ���ÿ� �׿��ִ� ������ ���� ��Ÿ���� �ʵ�, ������
	int[] stk; // ������ ��ü

	// ���� �� ���� : ������ �������
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}
	
	// ���� �� ���� : ������ ���� ��
	public class OverflowIntStackException extends RuntimeException {
		OverflowIntStackException() {
		}
	}
	
	// ������
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max]; // ���� ��ü�� �迭�� ����
		} catch (OutOfMemoryError e) { // ������ �� ����
			max = 0;
		}
	}
	
	// ���ÿ� x�� push
	public int push(int x) throws OverflowIntStackException {
		if (ptr >= max)
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}
	
	// ���ÿ��� �����͸� pop
	public int pop() throws EmptyIntStackException {
		if (ptr <= 0)
			throw new EmptyIntStackException();
		return stk[--ptr];
	}
	
	// ���ÿ��� �����͸� ��ũ(������ �ʰ� ���⸸ ��)
	public int peek() throws EmptyIntStackException {
		if (ptr <=0)
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}
	
	// indexOf: skt�� x�� ���� ���� �����Ͱ� ���Ե� �ִ���, ������ �迭�� ��� �ִ����� �����ϴ� �޼���
	// ����� �ʿ��� �ٴ� ������ �����˻��� �����Ѵ�. �������� �ϴ� ������, ���� pop�Ǵ� �����͸� ã�� ���ؼ���.
	public int indexOf(int x) {
		for (int i = ptr - 1; i >=0; --i)
			if (stk[i] == x) return i;
		return -1;
	}
	
	// clear: ������ ��� ��Ҹ� �����ϴ� �޼���
	public void clear() {
		ptr = 0;
	}
	
	// capacity: ������ �뷮�� ��ȯ�ϴ� �޼���
	public int capacity() {
		return max;
	}
	
	// size: ���ÿ� ���� �׿� �ִ� ������ ���� ��ȯ�ϴ� �޼���
	public int size() {
		return ptr;
	}
	
	// isEmpty: ���� ������ ����ִ��� �˻��ϴ� �޼���
	public boolean isEmpty() {
		return ptr <= 0;
	}
	
	// isFull: ���� ������ ���� á���� �˻��ϴ� �޼���
	public boolean isFull() {
		return ptr >= max;
	}
	
	// dump: ���� �ȿ� �ִ� ��� �����͸� ǥ���ϴ� �޼���, �ٴں��� ����� ������ ǥ���Ѵ�.
	public void dump() {
		if (ptr <= 0)
			System.out.println("������ ����ֽ��ϴ�.");
		else {
			for (int i = 0; i < ptr; ++i)
				System.out.print(stk[i] + " ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}