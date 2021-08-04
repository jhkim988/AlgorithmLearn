import java.util.Scanner;

class IntAryQue {
	int max;
	int rear;
	int[] que;

	class EmptyIntAryQueException extends RuntimeException {
		EmptyIntAryQueException() {

		}
	}

	class OverflowIntAryQueException extends RuntimeException {
		OverflowIntAryQueException() {

		}
	}

	IntAryQue(int capacity) {
		max = capacity;
		rear = 0;
		que = new int[max];
	}

	int enque(int x) throws OverflowIntAryQueException {
		if (rear >= max)
			throw new OverflowIntAryQueException();
		return que[rear++] = x;
	}

	int deque() throws EmptyIntAryQueException {
		if (rear <= 0)
			throw new EmptyIntAryQueException();
		int x = que[0];
		for (int i = 1; i < rear; ++i)
			que[i - 1] = que[i];
		rear--;
		return x;
	}

	int peek() throws EmptyIntAryQueException {
		if (rear <= 0)
			throw new EmptyIntAryQueException();
		return que[rear - 1];
	}
	
	int indexOf(int key) {
		for (int i = 0; i < rear; ++i)
			if (que[i] == key)
				return i;
		return -1;
	}
	
	void clear() {
		rear = 0;
	}

	int capacity() {
		return max;
	}

	int size() {
		return rear;
	}

	public boolean isEmpty() {
		return rear <= 0;
	}
	
	public boolean isFull() {
		return rear >= max;
	}
	
	public void dump() {
		if (rear <= 0)
			System.out.println("ť�� ����ֽ��ϴ�.");
		else
			for (int i = 0 ; i < rear; ++i)
				System.out.print(que[i] + " ");
		System.out.println();
	}
}

class Exercise04_04 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntAryQue s = new IntAryQue(64);
		
		while (true) {
			System.out.println("���� ������ �� : " + s.size() + " / " + s.capacity());
			System.out.print("(1) ��ť (2) ��ť (3) ��ũ (4) ���� (0) ���� : ");
			int menu = stdIn.nextInt();
			
			if (menu == 0) break;
			switch (menu) {
			case 1:
				System.out.print("������ : ");
				int x = stdIn.nextInt();
				try {
					s.enque(x);
				} catch (IntAryQue.OverflowIntAryQueException e) {
					System.out.println("ť�� ���� á���ϴ�.");
				}
				break;
			case 2:
				try {
					x = s.deque();
					System.out.println("��ť�� �����ʹ� " + x + " �Դϴ�.");
				} catch (IntAryQue.EmptyIntAryQueException e) {
					System.out.println("ť�� ����ֽ��ϴ�.");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("��ũ�� �����ʹ� " + x +" �Դϴ�.");
				} catch (IntAryQue.EmptyIntAryQueException e) {
					System.out.println("ť�� ����ֽ��ϴ�.");
				}
				break;
			case 4:
				s.dump();
				break;
			}
		}
		stdIn.close();
	}
}