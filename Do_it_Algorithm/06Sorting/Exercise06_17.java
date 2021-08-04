import java.util.Scanner;

class Exercise06_17 {
	// Q17. downHeap �޼��尡 ȣ��� ������ Ʈ���� ����ϴ� ���α׷��� �ۼ��ϼ���
	static int[] leftTree(int[] a, int n) {
		// n = 1 3 4 5 6 7 8, ...
		// parent
		// child = parent * 2 + 1
		// child = parent * 2 + 2		
		return (new int[] {1, 2, 3});
	}
	static int getLcount(int[]a, int n) {
		int lcount = 0;
		if (n >= 2) lcount++;
		return 0;
	}
	static int findDepth(int n) {
		int expo = 0;
		int prod = 1;
		while (prod <= n) {
			prod *= 2;
			expo++;
		}
		return expo;
	}

	static String printSpace(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}

	static void printHeap(int a[], int n) {
		if (n == 1) {
			System.out.println(a[0]);
			return;
		}

		if (n == 2) {
			System.out.println(printSpace(2) + a[0]);
			System.out.println(printSpace(1) + "/" + printSpace(4));
			System.out.println(a[1] + printSpace(4));
		}

		if (n == 3) {
			System.out.println(printSpace(2) + a[0]);
			System.out.println(printSpace(1) + "/" + printSpace(2) + "\\");
			System.out.println(a[1] + printSpace(4) + a[2]);
		}
		// 2^k <= n < 2^(k+1)�� �����ϴ� k.
		// (k + 1)��
		
	}

	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// a[left] ~ a[right]�� ������ ����ϴ�.
	// a[left] �̿ܿ��� ��� ���̶�� �����ϰ� a[left]�� �˸��� ��ġ�� �Ű� �� ���·� �����.
	static void downHeap(int[] a, int left, int right) {
		int temp = a[left]; // root
		int child; // ū ���� ���� ���
		int parent; // �θ� ���

		// right�� �θ� = (right - 1) / 2
		// right�� �θ� + 1 = (right + 1) / 2
		// parent < (right + 1) / 2: right�� �θ�(����)���� loop...
		for (parent = left; parent < (right + 1) / 2; parent = child) {
			int cl = parent * 2 + 1; // ���� �ڽ�
			int cr = cl + 1; // ������ �ڽ�
			child = (cr <= right && a[cr] > a[cl]) ? cr : cl; // �� �� ū ���� ���� �ڽ�
			// cr <= right�� �ִ� ���� - �ڽ��� �ϳ��� ����� right�� cl�� �ǰ� cl + 1�� right���� Ŀ�� �� �ִ�.
			if (temp >= a[child])
				break;
			a[parent] = a[child]; // �ڽ��� �� ũ�ٸ�...
		}
		a[parent] = temp;
	}

	static void heapSort(int[] a, int n) {
		for (int i = (n - 1) / 2; i >= 0; --i) // i = (n - 1) / 2���� �ϴ� ����? ���� �ٷ� ���ܰ���� ������ ����� �Ǳ� ����
			downHeap(a, i, n - 1); // a[i] ~ a[n - 1]�� ������ �����

		for (int i = n - 1; i > 0; --i) {
			swap(a, 0, i);
			downHeap(a, 0, i - 1);
		}
	}

	public static void main(String[] args) {
//		Scanner stdIn = new Scanner(System.in);
//
//		System.out.println("Heap ����");
//		System.out.print("��ڼ� : ");
//		int nx = stdIn.nextInt();
//		int[] x = new int[nx];
//
//		for (int i = 0; i < nx; ++i) {
//			System.out.print("x[" + i + "] : ");
//			x[i] = stdIn.nextInt();
//		}
//
//		stdIn.close();
//
//		heapSort(x, nx);
//
//		System.out.println("������������ �����߽��ϴ�.");
//		for (int i = 0; i < nx; ++i)
//			System.out.println("x[" + i + "] = " + x[i]);

	}
}