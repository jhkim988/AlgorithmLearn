import java.util.Scanner;

class Exercise03_02 {
	// ���� �˻��� ��ĳ�� ������ ���ϰ� ����ϴ� ���α׷��� �ۼ��ϼ���.
	// �� ���� �� ���ʿ� ���� �˻��ϴ� ����� �ε����� ����ϰ�,
	// ���� �˻��ϰ� �ִ� ��� ���� ��ǥ ��ȣ�� ����ϼ���.

	static void seqSearch_print(int[] a, int n, int key) {
		System.out.print("  |");
		for (int i = 0; i < n; ++i)
			System.out.printf("%-4d", i);

		System.out.print("\n--+");
		for (int i = 0; i < n; ++i)
			System.out.print("-----");
		System.out.println();

		String space = "    ";
		String chart2 = " |";
		for (int i = 0; i < n; ++i) {
			String chart1 = "  |";
			for (int j = 0; j < i; ++j)
				chart1 += space;
			System.out.println(chart1 + "*");
			
			System.out.print(i + chart2);
			for (int j = 0; j < n; ++j)
				System.out.printf("%-4d", a[j]);
			System.out.println();

			if (a[i] == key) {
				System.out.println(key + "��(��) x[" + i + "]�� �ֽ��ϴ�.");
				return;
			}

		}
		System.out.println("�� ���� ��Ұ� �����ϴ�.");
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("��ڼ� : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];

		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		System.out.print("�˻��� �� : ");
		int ky = stdIn.nextInt();
		stdIn.close();

		seqSearch_print(x, num, ky);
	}
}