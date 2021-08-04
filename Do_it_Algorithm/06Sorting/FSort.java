import java.util.Scanner;

class FSort{
	// �������� �˰����� 4�ܰ�� �̷������.
	// 1. ��������ǥ �ۼ�
	// 2. ������������ǥ �ۼ�
	// 3. ���� �迭 �����
	// 4. �迭�� ����
	
	static void fSort(int[] a, int n, int max) {
		int[] f = new int[max + 1]; // ��������
		int[] b = new int[n]; // �۾��� ���� �迭
		
		// MyTry
//		for(int i = 0; i < n; ++i) f[a[i]]++;
//		for(int i = 0; i < max; ++i) f[i + 1] += f[i];
//		for(int i = 0; i < n; ++i) b[--f[a[i]]] = a[i]; // �������� �ϴ� ������ �ִ���?
//		for(int i = 0; i < n; ++i) a[i] = b[i];
		
		for(int i = 0; i < n; ++i) f[a[i]]++;
		for(int i = 1; i <= max; ++i) f[i] += f[i - 1];
		for(int i = n - 1; i >= 0; --i) b[--f[a[i]]] = a[i]; // �������� �ϴ� ������ �ִ���?
		for(int i = 0; i < n; ++i) a[i] = b[i];
		// �������� ���� �ʰ� ó������ ��ĵ�ϸ� ���������� �ʰ� �ȴ�.
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

		int max = x[0];
		for (int i = 1; i <nx; ++i)
			if (max < x[i])
				max = x[i];
		
		fSort(x, nx, max);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}