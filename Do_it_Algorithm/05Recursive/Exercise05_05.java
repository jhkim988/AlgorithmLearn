class Exercise05_05 {
	// Q5 ���� recur3 �޼��带 ����������� �����ϼ���
	static void recur3(int n) {
		if (n > 0) {
			System.out.println("���� " + n);
			recur3(n - 1);
			recur3(n - 2);
			System.out.println(n);
		}
	}

	// recur(1) - recur(0) recur(-1) - 1 ��� == 1 ���
	// recur(2) - recur(1) recur(0) 2 ��� == 1 2 ���
	// recur(3) - recur(2) recur(1) 3 ��� == 1 2 1 3 ���
	// recur(4) - recur(3) recur(2) 4 ��� == 1 2 1 3 1 2 4 ���

	// 4 - 3 2 %4 - (2 1 %3) (1 0 %2) %4 - ((1 0 %2) (0 -1 %1) %3) ((0 -1 %1) () %2)
	// %4
	// 4�� ���� 3�� ���� 2 ����
	static void recur3_while(int n) {
		IntStack s1 = new IntStack(n * n);
		IntStack s2 = new IntStack(n * n);
		
		while (true) {
			if (n > 0) {
				s1.push(n);
				n = n - 1;
				continue;
			}
			if (s1.isEmpty() != true) {
				n = s1.pop();
				s2.push(n);
				
				System.out.println(n);
			}
		}
	}

	public static void main(String[] args) {
//		recur3(4);
		recur3_while(4);
	}
}