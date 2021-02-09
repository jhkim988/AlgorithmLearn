class Hanoi {
	// MyTry
//	static void move(int no, int x, int y) { // n = ���� ����, x = ���۱��, y = ��ǥ���
//		if (no == 1)
//			System.out.println("����[1]�� " + x + " ��տ��� " + y +" ������� �ű�");
//		else {
//			// x = 1, y = 3 -> 2
//			// x = 1, y = 2 -> 3
//			// x = 2, y = 1 -> 3
//			// x = 2, y = 3 -> 1
//			move(no - 1, x, 6 - x - y);
//			System.out.println("����[" + no + "]�� " + x + " ��տ��� " + y + " ������� �ű�");
//			move(no - 1, 6 - x - y, y);
//		}
//
//	}

	// Solution
	static void move(int no, int x, int y) {
		if (no > 1)
			move(no - 1, x, 6 - x - y);
		System.out.println("���� [" + no + "]�� " + x + " ��տ��� " + y + " ������� �ű�");
		if (no > 1)
			move(no - 1, 6 - x - y, y);
	}
	public static void main(String[] args) {
		move(4, 1, 3);
	}
}