class Exercise05_06 {
	static char numToChar(int n) {
		if (n == 1)
			return 'A';
		else if (n == 2)
			return 'B';
		else if (n == 3)
			return 'C';
		else
			return '\0';
	}

	static void move(int n, int x, int y) {
		if (n > 1)
			move(n - 1, x, 6 - x - y);
		System.out.println("���� [" + n + "]�� " + numToChar(x) + " ��տ��� " + numToChar(6 - x - y) + " ������� �Ű���ϴ�.");
		if (n > 1)
			move(n - 1, 6 - x - y, y);
	}

	public static void main(String[] args) {
		move(4, 1, 3);
	}
}