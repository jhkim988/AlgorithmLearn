import java.util.Scanner;

class Exercise06_11 {
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static void quickSort(int[] a, int left, int right) {
		IntStack lstack = new IntStack(right - left + 1);
		IntStack rstack = new IntStack(right - left + 1);
		
		System.out.printf("a[%d]~a[%d] : {", left, right);
		for(int i = left; i < right; ++i) {
			System.out.printf("%d, ", a[i]);
		}
		System.out.printf("%d}\n",a[right]);
		
		lstack.push(left);
		rstack.push(right);
		System.out.printf("push : {%d, %d}\n", left, right);
		
		while (lstack.isEmpty() != true) {
			int pl = left = lstack.pop();
			int pr = right = rstack.pop();
			int x = a[(pl + pr) / 2];
			
			System.out.printf("a[%d]~a[%d] : {", left, right);
			for(int i = left; i < right; ++i) {
				System.out.printf("%d, ", a[i]);
			}
			System.out.printf("%d}\n",a[right]);
			
			System.out.printf("pop : {%d, %d}\n", left, right);
			
			do {
				while (a[pl] < x)
					pl++;
				while (x < a[pr])
					pr--;
				if (pl <= pr)
					swap(a, pl++, pr--);
			} while (pl <= pr);

			if (left < pr) {
				lstack.push(left);
				rstack.push(pr);
				System.out.printf("push : {%d, %d}\n", left, pr);
			}
			;
			if (pl < right) {
				lstack.push(pl);
				rstack.push(right);
				System.out.printf("push : {%d, %d}\n", pl, right);
			}
			;
		}

		// left < pr, pl < right ��� ��Ұ� 1���� �������� �ʴ´�. �� ��Ұ� 1���̸� ���ȣ���� �����.
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("�� ����(����� ���)");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		quickSort(x, 0, nx - 1);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}