import java.util.Random;
import java.util.Scanner;

class Exercise02 {
	// Q1 ��� Ű�Ӹ� �ƴ϶� ��� ���� ������ �����ϵ��� �ǽ� 2-5�� �����Ͽ� ���α׷��� �ۼ��ϼ���.
	static void q1() {
		Random rand = new Random();

		System.out.println("Ű�� �ִ��� ���մϴ�.");
		int num = rand.nextInt(10) + 5;
		System.out.println("��� �� : " + num);

		int[] height = new int[num];

		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1������ ������ �����ϰ� ��ȯ�Ѵ�.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("�ִ��� " + maxOf(height) + " �Դϴ�.");
	}

	static int maxOf(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i)
			if (a[i] > max)
				max = a[i];
		return max;
	}

	// Q2 �迭 ��Ҹ� �������� �����ϴ� ������ �ϳ��ϳ� ��Ÿ���� ���α׷��� �ۼ��ϼ���.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	static void reverse_print(int[] a) {
		for (int i = 0; i < a.length / 2; ++i) {
			System.out.println("a[" + i + "] �� a[" + (a.length - i - 1) + "]�� ��ȯ�մϴ�.");
			swap(a, i, a.length - 1 - i);
			
			for (int j = 0; j< a.length; ++j)
				System.out.print(a[j] + " ");
			
			System.out.println();
		}

	}

	static void q2() {
		Scanner stdIn = new Scanner(System.in);
		int num = stdIn.nextInt();
		int[] arr = new int[num];
		for (int i = 0; i < num; ++i)
			arr[i] = stdIn.nextInt();
		stdIn.close();
		reverse_print(arr);
		System.out.println("���� ������ ���ƽ��ϴ�.");
	}

	// Q3 �迭 a�� ��� ����� �հ踦 ���Ͽ� ��ȯ�ϴ� �޼��带 �ۼ��ϼ���.
	static int sumOf(int[] a) {
		int sum = a[0];
		for (int i = 1 ; i < a.length; ++i)
			sum += a[i];
		return sum;
	}
	public static void main(String[] args) {
//		q1();
//		q2();
//		int[] a = {1, 2, 3, 4, 7};
//		System.out.println(sumOf(a));
	}
}