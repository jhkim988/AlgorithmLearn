import java.util.Scanner;

class Exercise04_01 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack s = new IntStack(64);

		while (true) {
			System.out.println("���� ������ �� : " + s.size() + " / " + s.capacity());
			System.out.print("(1) Ǫ�� (2) �� (3) ��ũ" + "(4) ���� (5) �˻� (6) �ʱ�ȭ (7) ��ü�뷮 (8) ����뷮"
					+ "(9) ������ ������ϱ�? (10) ������ ����á���ϱ�? (0) ���� : ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {
			case 1:
				System.out.print("������ : ");
				x = stdIn.nextInt();
				try {
					s.push(x);
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("������ ����á���ϴ�.");
				}
				break;
			case 2:
				try {
					x = s.pop();
					System.out.println("���� �����ʹ� " + x + "�Դϴ�.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("������ ����ֽ��ϴ�.");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("��ũ�� �����ʹ� " + x + "�Դϴ�.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("������ ����ֽ��ϴ�.");
				}
				break;
			case 4:
				s.dump();
				break;
			case 5:
				if (s.isEmpty()) {
					System.out.println("������ ����ֽ��ϴ�.");
					break;
				}
				System.out.print("�˻��� ������ : ");
				int key = stdIn.nextInt();
				int idx = s.indexOf(key);
				System.out.println(key + "��(��) x[" + idx + "]�� �ֽ��ϴ�.");
				break;
			case 6:
				s.clear();
				break;
			case 7:
				System.out.println("��ü �뷮�� " + s.capacity() + "�Դϴ�.");
				break;
			case 8:
				System.out.println("���� �뷮�� " + s.size() + "�Դϴ�.");
				break;
			case 9:
				if (s.isEmpty())
					System.out.println("������ ����ֽ��ϴ�.");
				else
					System.out.println("������ ������� �ʽ��ϴ�.");
				break;
			case 10:
				if (s.isFull())
					System.out.println("������ ����á���ϴ�.");
				else
					System.out.println("������ �������� �ʾҽ��ϴ�.");
				break;
			}
		}
		stdIn.close();
	}
}