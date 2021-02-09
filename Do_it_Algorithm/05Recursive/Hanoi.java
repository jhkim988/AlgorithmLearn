class Hanoi {
	// MyTry
//	static void move(int no, int x, int y) { // n = ¿ø¹Ý °³¼ö, x = ½ÃÀÛ±âµÕ, y = ¸ñÇ¥±âµÕ
//		if (no == 1)
//			System.out.println("¿ø¹Ý[1]À» " + x + " ±âµÕ¿¡¼­ " + y +" ±âµÕÀ¸·Î ¿Å±è");
//		else {
//			// x = 1, y = 3 -> 2
//			// x = 1, y = 2 -> 3
//			// x = 2, y = 1 -> 3
//			// x = 2, y = 3 -> 1
//			move(no - 1, x, 6 - x - y);
//			System.out.println("¿ø¹Ý[" + no + "]¸¦ " + x + " ±âµÕ¿¡¼­ " + y + " ±âµÕÀ¸·Î ¿Å±è");
//			move(no - 1, 6 - x - y, y);
//		}
//
//	}

	// Solution
	static void move(int no, int x, int y) {
		if (no > 1)
			move(no - 1, x, 6 - x - y);
		System.out.println("¿ø¹Ý [" + no + "]À» " + x + " ±âµÕ¿¡¼­ " + y + " ±âµÕÀ¸·Î ¿Å±è");
		if (no > 1)
			move(no - 1, 6 - x - y, y);
	}
	public static void main(String[] args) {
		move(4, 1, 3);
	}
}