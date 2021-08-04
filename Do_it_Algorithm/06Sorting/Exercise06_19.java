import java.util.Scanner;

class Exercise06_19{
	// Q.19 ��ڰ��� ������ min �̻� max �����̰� ����� ������ n���� �迭 a�� ���������ϴ� �޼��带 �ۼ��ϼ���
	static void fSort(int[] a, int n, int min, int max) {
		int[] f = new int[max - min + 1]; // ��������
		int[] b = new int[n]; // �۾��� ���� �迭
		
		
		for(int i = 0; i < n; ++i) f[a[i] - min]++;
		for(int i = 1; i <= max - min ; ++i) f[i] += f[i - 1];
		for(int i = n - 1; i >= 0; --i) b[--f[a[i] - min]] = a[i]; 
		for(int i = 0; i < n; ++i) a[i] = b[i];


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
		
		int min = x[0];
		for (int i = 1; i < nx; ++i) {
			if (min > x[i])
				min = x[i];
		}
		
		int max = x[0];
		for (int i = 1; i <nx; ++i)
			if (max < x[i])
				max = x[i];
		
		fSort(x, nx, min, max);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}