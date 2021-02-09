class Exercise05_04 {
	static void recur2(int n) {
		if (n > 0) {
			recur2(n - 2);
			System.out.println(n);
			recur2(n - 1);
		}
	}
	
	// 하향식 분석
	// 4 전달 : recur2(2) 4 출력 recur2(3)
	// 2 전달 : recur2(0) 2 출력 recur2(1)
	// 1 전달 : recur2(-1) 1 출력 recur2(0)
	// 3 전달 : recur2(1) 3 출력 recur2(2)
	// 2 1 4 1 3 2 1
	
	// 상향식 분석
	// 1 전달 : recur2(-1) 1 출력 recur2(0) - 1
	// 2 전달 : recur2(0) 2 출력 recur2(1) - 2 1
	// 3 전달 : recur2(1) 3 출력 recur2(2) - 1 3 2 1
	// 4 전달 : recur2(2) 4 출력 recur2(3) - 2 1 4 1 3 2 1
	
	public static void main(String[] args) {
		recur2(4);
	}
}