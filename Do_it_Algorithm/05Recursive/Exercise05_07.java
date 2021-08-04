class Exercise05_07 {
	static void move(int n, int x, int y) {
		if (n > 1)
			move(n - 1, x, 6 - x - y);
		System.out.println("¿øÆÇ [" + n + "]À» " + x + " ±âµÕ¿¡¼­ " + y + " ±âµÕÀ¸·Î ¿Å°å½À´Ï´Ù.");
		if (n > 1)
			move(n - 1, 6 - x - y, y);
	}

	static void move_while(int n, int x, int y) {
		IntStack ns = new IntStack(n);
		IntStack xs = new IntStack(n);
		IntStack ys = new IntStack(n);
		
		while (true) {
			if (n >= 1) {
				ns.push(n);
				xs.push(x);
				ys.push(y);
				n = n - 1;
				y = 6 - x - y;
				continue;
			}
			if (xs.isEmpty() != true) {
				n = ns.pop();
				x = xs.pop();
				y = ys.pop();
				System.out.println("¿øÆÇ[" + n + "]À» " + x + " ±âµÕ¿¡¼­ " + y + " ±âµÕÀ¸·Î ¿Å°å½À´Ï´Ù.");
				
				if (n >= 1) {
					n = n - 1;
					x = 6 - x - y;
					continue;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		move_while(3, 1, 3);
	}
}