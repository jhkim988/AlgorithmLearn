import java.util.Random;
import java.util.Scanner;

class Exercise02 {
	// Q1 사람 키뿐만 아니라 사람 수도 난수로 생성하도록 실습 2-5를 수정하여 프로그램을 작성하세요.
	static void q1() {
		Random rand = new Random();

		System.out.println("키의 최댓값을 구합니다.");
		int num = rand.nextInt(10) + 5;
		System.out.println("사람 수 : " + num);

		int[] height = new int[num];

		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1까지의 정수를 랜덤하게 반환한다.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("최댓값은 " + maxOf(height) + " 입니다.");
	}

	static int maxOf(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i)
			if (a[i] > max)
				max = a[i];
		return max;
	}

	// Q2 배열 요소를 역순으로 정렬하는 과정을 하나하나 나타내는 프로그램을 작성하세요.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	static void reverse_print(int[] a) {
		for (int i = 0; i < a.length / 2; ++i) {
			System.out.println("a[" + i + "] 와 a[" + (a.length - i - 1) + "]을 교환합니다.");
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
		System.out.println("역순 정렬을 마쳤습니다.");
	}

	// Q3 배열 a의 모든 요소의 합계를 구하여 반환하는 메서드를 작성하세요.
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