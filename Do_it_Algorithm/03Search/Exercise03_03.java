class Exercise03_03{
	// Q3 ��ڼ��� n�� �迭 a���� key�� ��ġ�ϴ� ��� ����� �ε����� �迭 idx�� �� �պ��� ������� �����ϰ�
	// ��ġ�� ��ڼ��� ��ȯ�ϴ� �޼��带 �ۼ��ϼ���.
	static int searchIdx(int[] a, int n, int key, int[] idx) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			if (a[i] == key) {
				idx[count++] = i;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int[] x = {1, 9, 2, 9, 4, 6, 7, 9};
		int num = 8;
		int key = 9;
		int[] idx = new int[num];
		
		System.out.println(searchIdx(x, num, key, idx));
		for (int i = 0; i < num; ++i)
			System.out.print(idx[i]+ " ");
	}
}