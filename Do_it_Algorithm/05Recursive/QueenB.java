class QueenB {
	// pos[]: ���� ��ġ�� ��Ÿ���� �迭
	// i���� �ִ� ���� j���� �ִٸ� pos[i] = j
	// set(): pos[i]�� 0���� 7������ ���� ������� �����Ͽ� i���� ���� 1���� ��ġ�ϴ� 8���� ������ ����� ��� �޼���

	static int[] pos = new int[8];
	static boolean[] flag_a = new boolean[8]; // �� �࿡ ���� �ִ��� üũ�Ѵ�.
	static boolean[] flag_b = new boolean[15]; // �밢�� / �������� ���� ��ġ�ߴ��� üũ
	static boolean[] flag_c = new boolean[15]; // �밢�� \ �������� ���� ��ġ�ߴ��� üũ

	// j�� i���� (i + j) ��° / �밢���� �ִ�.
	// j�� i���� (i - j + 7) ��° \ �밢���� �ִ�.

	static void print() {
		for (int i = 0; i < 8; ++i)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}

	static void set(int i) {
		for (int j = 0; j < 8; ++j) {
			if (flag_a[j] == false && flag_b[i + j] == false && flag_c[i - j + 7] == false) {// j�࿡ ���� ��ġ���� �ʾҴٸ�
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
		set(0); // 0���� ���� ��ġ
	}
}