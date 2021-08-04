import java.util.Scanner;

class IntQueueTester {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue s = new IntQueue(64);
		
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
				} catch (IntQueue.OverflowIntQueueException e) {
					System.out.println("ť�� ���� á���ϴ�.");
				}
				break;
			case 2:
				try {
					x = s.deque();
					System.out.println("��ť�� �����ʹ� " + x + " �Դϴ�.");
				} catch (IntQueue.EmptyIntQueueException e) {
					System.out.println("ť�� ����ֽ��ϴ�.");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("��ũ�� �����ʹ� " + x +" �Դϴ�.");
				} catch (IntQueue.EmptyIntQueueException e) {
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