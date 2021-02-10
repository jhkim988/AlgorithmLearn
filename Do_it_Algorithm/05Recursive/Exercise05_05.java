class Exercise05_05 {
	// Q5 다음 recur3 메서드를 비재귀적으로 구현하세요
	static void recur3(int n) {
		if (n > 0) {
			recur3(n - 1);
			recur3(n - 2);
			System.out.println(n);
		}
	}

	static void recur3_while(int n) {
		IntStack s1 = new IntStack(n);
		IntStack s2 = new IntStack(n); // s2에는 실행상태를 넣는 값을 넣어준다.
		// 0이면 n - 1로 recur, 1이면 n - 2로 recur, 2면 print하도록 한다.
		int sw = 0;
		
		while (true) {
			if (n > 0) {
				s1.push(n);
				s2.push(sw);
				
				if (s2.peek() == 0) {
					n = n - 1;
					continue;
				} else if (s2.peek() == 1){
					n = n - 2;
					sw = 0;
					continue;
				}
			} do {
				n = s1.pop();
				sw = s2.pop() + 1;
				
				if (sw == 2) {
					System.out.println(n);
					if (s2.isEmpty() == true) {
						return;
					}
				}
			} while (sw == 2);
		}
	}

	static void recur3_solution(int n) {
		int[] nstk = new int[100];
		int[] sstk = new int[100];
		int ptr = -1;
		int sw = 0;

		while (true) {
			if (n > 0) {
				ptr++;
				nstk[ptr] = n;
				sstk[ptr] = sw;

				if (sw == 0)
					n = n - 1;
				else if (sw == 1) {
					n = n - 2;
					sw = 0;
				}
				continue;
			}
			do {
				n = nstk[ptr];
				sw = sstk[ptr--] + 1;

				if (sw == 2) {
					System.out.println(n);
					if (ptr < 0)
						return;
				}
			} while (sw == 2);
		}
	}

	public static void main(String[] args) {
//		recur3(4);
		recur3_while(4);
	}
}