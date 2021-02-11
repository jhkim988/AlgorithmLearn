import java.util.Scanner;

class ShellSort {
	// �� ������ �ܼ����������� ������ �츮��, ������ �����Ͽ� �� �� ������ �����ϴ� �˰����Դϴ�.
	// �ܼ����������� ���� - ������ ���ưų�, ������ ��ģ ���¿� ������ ���� �ӵ��� �ſ� ������.
	// �ܼ����������� ���� - ������ ��ġ�� �ָ� ������ ������ �̵�(����)�ؾ��ϴ� Ƚ���� ��������.

	static void shellSort(int[] a, int n) {
		for (int h = n / 2; h > 0; h /= 2) {
			for (int i = h; i < n; ++i) { // �ܼ��������Ŀ��� a[1] ~ a[n-1]���� �ݺ��ϴ� �Ͱ� ����.
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h) // a[i]�� a[0] ~ a[i-1] �� ������ ���� �����Ѵ�.(������ h��)
					a[j + h] = a[j];
				a[j + h] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Shell ����");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		shellSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}