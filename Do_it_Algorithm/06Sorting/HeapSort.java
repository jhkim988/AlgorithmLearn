import java.util.Scanner;

class HeapSort {
	// ���̶�?
	// �θ� �ڽİ��� ��Ұ��谡 ������ ��������Ʈ��
	// ����? - �ڽ��� �߰��� �� ���ʺ��� �߰��ϴ� Ʈ��
	// ����? - �ڽ��� �ִ� ������ 2�� Ʈ��

	// ���� �迭�� �����ϴ� ���
	// ��Ʈ(�� ��)�� a[0]�� �ְ� �� �ܰ� �Ʒ� ��Ҹ� ���ʿ��� ���������� ���󰣴�.
	// �ε����� ���� 1�� �ø��鼭 �迭�� �� ��ҿ� ���� ��Ҹ� �����Ѵ�.

	// �θ�� a[(i - 1) / 2]
	// ���� �ڽ��� a[2 * i + 1]
	// ������ �ڽ��� a[2 * i + 2]

	// �� ������ "���� ū ���� ��Ʈ�� ��ġ"�ϴ� Ư¡�� �̿��� ���� �˰���
	// ������ ���� ū ���� ���� �Ŀ� ������ ��Ҹ� �ٽ� ������ ����� ������ �ݺ��Ͽ� �����Ѵ�.
	// ���� ū ��Ҹ� ������ ���� �ð����⵵�� O(1)
	// �ٽ� ������ ����� ���� �ð����⵵�� O(logn)
	// ���� �� ������ �ð����⵵�� O(nlogn)�̴�.

	// ��Ʈ�� ���ְ� �� ���� �����ϱ�
	// 1. ��Ʈ�� ���ش�.
	// 2. ���� ���� ������ ���(������ �Ʒ� ���� �ִ� �ڽĿ��)�� ��Ʈ�� �ű��.
	// 3. �� �ڽ� �� ū �ʰ� swap �ϴ� ���� �ݺ��Ѵ�.
	// 4. �ڽ��� ���� �۰ų�, �ٿ� �����ϸ� �۾��� ����ȴ�.

	// �ʱ� ������ �迭�� ���� �ƴ϶�� �� ���·� �������Ѵ�.
	// ��Ʈ�� ������ ������ ����� ������ �κ� Ʈ�������� ����ȴ�. ���� �Ʒ��������� ���� �ö󰡸鼭 ������ ���� �� �ִ�.

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
		
		for(int i = n - 1; i > 0; --i) {
			swap(a, 0, i);
			downHeap(a, 0, i - 1);
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Heap ����");
		System.out.print("��ڼ� : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		heapSort(x, nx);

		System.out.println("������������ �����߽��ϴ�.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}