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
		System.out.println("¿øÆÇ [" + n + "]À» " + numToChar(x) + " ±âµÕ¿¡¼­ " + numToChar(6 - x - y) + " ±âµÕÀ¸·Î ¿Å°å½À´Ï´Ù.");
		if (n > 1)
			move(n - 1, 6 - x - y, y);
	}

	public static void main(String[] args) {
		move(4, 1, 3);
	}
}