class Exercise05_05 {
	// Q5 다음 recur3 메서드를 비재귀적으로 구현하세요
	static void recur3(int n) {
		if (n > 0) {
			System.out.println("시작 " + n);
			recur3(n - 1);
			recur3(n - 2);
			System.out.println(n);
		}
	}

	// recur(1) - recur(0) recur(-1) - 1 출력 == 1 출력
	// recur(2) - recur(1) recur(0) 2 출력 == 1 2 출력
	// recur(3) - recur(2) recur(1) 3 출력 == 1 2 1 3 출력
	// recur(4) - recur(3) recur(2) 4 출력 == 1 2 1 3 1 2 4 출력

	// 4 - 3 2 %4 - (2 1 %3) (1 0 %2) %4 - ((1 0 %2) (0 -1 %1) %3) ((0 -1 %1) () %2)
	// %4
	// 4를 저장 3을 저장 2 저장
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