class Exercise {
	// Q1 네 값의 최댓값을 구하는 max4 메서드를 작성하세요.
	static int max4(int a, int b, int c, int d) {
		int max = a;
		if (max < b)
			max = b;
		if (max < c)
			max = c;
		if (max < d)
			max = d;
		return max;
	}

	// Q2 세 값의 최솟값을 구하는 min3 메서드를 작성하세요.
	static int min3(int a, int b, int c) {
		int min = a;
		if (min > b)
			min = b;
		if (min > c)
			min = c;
		return min;
	}

	// Q3 네 값의 최솟값을 구하는 min4 메서드를 작성하세요.
	static int min4(int a, int b, int c, int d) {
		int min = a;
		if (min > b) min = b;
		if (min > c) min = c;
		if (min > d) min = d;
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println("max4(23, 569, 8, 156) = " + max4(23, 569, 8, 156));
		System.out.println("min3(23, 569, 8) = " + min3(23, 569, 8));
		System.out.println("min4(23, 569, 8, 156) = " + min4(23, 569, 8, 156));
	}
}