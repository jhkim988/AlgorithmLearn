class Exercise05_09 {
	// pos[]: 퀸의 위치를 나타내는 배열
	// i열에 있는 퀸이 j열에 있다면 pos[i] = j
	// set(): pos[i]에 0부터 7까지의 값을 순서대로 대입하여 i열에 퀸을 1개만 배치하는 8가지 조합을 만드는 재귀 메서드

	static int[] pos = new int[8];
	static boolean[] flag_a = new boolean[8]; // 각 행에 퀸이 있는지 체크한다.
	static boolean[] flag_b = new boolean[15]; // 대각선 / 방향으로 퀸을 배치했는지 체크
	static boolean[] flag_c = new boolean[15]; // 대각선 \ 방향으로 퀸을 배치했는지 체크

	// j행 i열은 (i + j) 번째 / 대각선에 있다.
	// j행 i열은 (i - j + 7) 번째 \ 대각선에 있다.

	static void print() {
		for (int i = 0; i < 8; ++i)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}

	static void print_visual() {
		for (int i = 0; i < 8; ++i) {
			for (int k = 0; k < 8; ++k) {
				if (k == pos[i])
					System.out.print("■");
				else
					System.out.print("□");
			}
			System.out.println();
		}
		System.out.println('\n');
	}

	static void set(int i) {
		IntStack jstk = new IntStack(8);
		int j;

		Start: while (true) {
			j = 0;
			while (true) {
				while (j < 8) {
					if (flag_a[j] == false && flag_b[i + j] == false && flag_c[i - j + 7] == false) {
						pos[i] = j;
						if (i == 7)
							print();
						else {
							flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
							jstk.push(j);
							i = i + 1;
							continue Start;
						}
					}
					j++;
				}

				if (--i == -1)
					return;
				j = jstk.pop();
				flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
				j++;
			}
		}
	}

	public static void main(String[] args) {
		set(0); // 0열에 퀸을 배치
	}
}