class Exercise05_09 {
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

	static void print_visual() {
		for (int i = 0; i < 8; ++i) {
			for (int k = 0; k < 8; ++k) {
				if (k == pos[i])
					System.out.print("��");
				else
					System.out.print("��");
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
		set(0); // 0���� ���� ��ġ
	}
}