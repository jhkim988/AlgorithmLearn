class Exercise05_04 {
	static void recur2(int n) {
		if (n > 0) {
			recur2(n - 2);
			System.out.println(n);
			recur2(n - 1);
		}
	}
	
	// ����� �м�
	// 4 ���� : recur2(2) 4 ��� recur2(3)
	// 2 ���� : recur2(0) 2 ��� recur2(1)
	// 1 ���� : recur2(-1) 1 ��� recur2(0)
	// 3 ���� : recur2(1) 3 ��� recur2(2)
	// 2 1 4 1 3 2 1
	
	// ����� �м�
	// 1 ���� : recur2(-1) 1 ��� recur2(0) - 1
	// 2 ���� : recur2(0) 2 ��� recur2(1) - 2 1
	// 3 ���� : recur2(1) 3 ��� recur2(2) - 1 3 2 1
	// 4 ���� : recur2(2) 4 ��� recur2(3) - 2 1 4 1 3 2 1
	
	public static void main(String[] args) {
		recur2(4);
	}
}