import java.util.Scanner;

class Partition {
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// �迭�� ����� �ִ� ��Ҹ� �ǹ����� ���ϰ�, �׷��� ������.
	static void partition(int[] a, int n) {
		int pl = 0;
		int pr = n - 1;
		int x = a[n / 2];

		do {
			while (x > a[pl])
				pl++;
			while (x < a[pr])
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);
		System.out.println("�ǹ��� ���� " + x + "�Դϴ�.");
		
		System.out.println("�ǹ� ������ �׷�");
		for(int i = 0; i <= pl - 1; i++)
			System.out.print(a[i] + " ");
		System.out.println();
		
		if (pr + 1 < pl) {
			System.out.println("�ǹ��� ��ġ�ϴ� �׷�");
			for(int i = pr + 1; i < pl; ++i)
				System.out.print(a[i] + " ");
			System.out.println();
		}
		System.out.println("�ǹ� �̻��� �׷�");
		for(int i = pl + 1; i < n; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("�迭�� �����ϴ�.");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		stdIn.close();
		partition(x ,nx);
	}
}