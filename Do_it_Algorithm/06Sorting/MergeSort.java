import java.util.Scanner;

class MergeSort {
	static int[] buff; // �۾��� �迭

	static void __mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;

			__mergeSort(a, left, center); // �迭�� �պκ��� ��������
			__mergeSort(a, center + 1, right); // �迭�� �޺κ��� ��������

			for (i = left; i <= center; i++)
				buff[p++] = a[i]; // buff[] = a[left] ~ a[center]
			// p = center - left + 1, buff �迭�� ũ��
			// a[left] ... a[center] �� a[center + 1] ... a[right]�� ���������Ѵ�.
			while (i <= right && j < p)
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			while (j < p)
				a[k++] = buff[j++];
			// �� while���� �ϳ� �� �ʿ� ������?
			// buff �迭�� �ִ� ��ҵ��� ���� ���������� ������ �����ִ� ���� �״�� �ڱ� �ڸ��� �־ �ȴ�.
			// ���� ������ �ִ� ��ҵ��� ��ȯ�ϴ� ���� �ƴϹǷ� �������� ���Ĺ���̴�.
		}
	}

	static void mergeSort(int[] a, int n) {
		buff = new int[n];
		__mergeSort(a, 0, n - 1);
		buff = null;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("���� ����");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		mergeSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);

	}
}