class QueenB {
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

	static void set(int i) {
		for (int j = 0; j < 8; ++j) {
			if (flag_a[j] == false && flag_b[i + j] == false && flag_c[i - j + 7] == false) {// j행에 퀸을 배치하지 않았다면
				pos[i] = j;
				if (i == 7)
					print();
				else {
					flag_c[i - j + 7] = flag_b[i + j] = flag_a[j] = true;
					set(i + 1);
					flag_c[i - j + 7] = flag_b[i + j] = flag_a[j] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		set(0); // 0열에 퀸을 배치
	}
}