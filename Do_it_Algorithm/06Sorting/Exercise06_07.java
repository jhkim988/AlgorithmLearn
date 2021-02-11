import java.util.Scanner;

class Exercise06_07 {
	// Q7 �ܼ� ���� ���Ŀ��� ù ��° ��� a[0]���� �������� �ʰ� a[1]���� �����ϸ�
	// a[0]�� ���ʷ� �Ͽ� ������ ��ġ�� ������ ���� �� �ֽ��ϴ�.
	// �� ���̵� �����Ͽ� �ܼ� ���� ���� �޼��带 �����ϼ���.

	// �Է��� x[1] ... x[nx]������ �ް�, �� ���� ����� ����(centinel)�� ����Ѵ�.
	static void insertionSort_v2(int[] a, int n) {
		for (int i = 2; i < n; ++i) {
			int temp = a[0] = a[i]; // a[0]�� a[i]�� �ִ´�.
			int j = i;
			for (; a[j - 1] > temp; --j) {
				a[j] = a[j - 1];
			}
			if (j > 0)
				a[j] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("�ܼ���������");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx + 1];

		for (int i = 1; i <= nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		insertionSort_v2(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 1; i <= nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}