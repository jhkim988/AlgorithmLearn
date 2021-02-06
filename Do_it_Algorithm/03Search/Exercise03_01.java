import java.util.Scanner;

class Exercise03_01{
	// Q1. seqSearchSen �޼��带 while���� �ƴ϶� for���� ����Ͽ� ����
	static int seqSearchSen(int[] a, int n, int key) {
		int i;
		a[n] = key;
		for (i = 0; i < n; ++i) {
			if (a[i] == key)
				break;
		}
		return i == n ? -1 : i;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("��ڼ� : ");
		int num = stdIn.nextInt();
		int[] x = new int[num + 1]; // ���ʸ� ���� �ڸ�, �ϳ� �� ũ�� �����Ѵ�.
		
		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		};
		
		System.out.print("�˻��� �� : ");
		int ky = stdIn.nextInt();
		stdIn.close();
		int idx = seqSearchSen(x, num, ky);
		
		if (idx == -1)
			System.out.println("�� ���� ��Ұ� �����ϴ�.");
		else
			System.out.println(ky + "��(��) x[" + idx + "]�� �ֽ��ϴ�.");
	}
}