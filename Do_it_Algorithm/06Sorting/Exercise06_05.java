import java.util.Scanner;

class Exercise06_05 {
	// Q5. 9 1 3 4 6 7 8
	// 이 배열은 두 번째 요소부터는 정렬이 돼 있지만, 버전 3의 정렬 알고리즘을 사용해도 빠른 시간 안에 정렬 작업을 마칠 수는 없습니다.
	// 패스의 스캔 방향을 교대로 바꾸면 이런 정렬을 더 적은 횟수로 비교할 수 있습니다.
	// 양방향 버블 정렬(칵테일 정렬, 셰이커 정렬)을 수행하는 프로그램을 작성하세요
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	static int right_bubble(int[] a, int n, int end) {
		// 우측에서 패스를 1회 end까지 진행한다.
		int last = n - 1;
		for (int i = n - 1; i > end; --i)
			if (a[i - 1] > a[i]) {
				swap(a, i - 1, i);
				last = i;
			}
		System.out.println("right_bubble " + last);
		return last;

	}

	static int left_bubble(int[] a, int n, int end) {
		// 좌측에서 패스를 1회 end까지 진행한다.
		int last = 0;
		for (int i = 0; i < end; ++i)
			if (a[i] > a[i + 1]) {
				swap(a, i, i + 1);
				last = i + 1;
			}
		System.out.println("left_bubble " + last);
		return last;
	}

	static void bidirection_bubbleSort(int[] a, int n) {
		boolean flag = true;

		int left_end = n - 1;
		int right_end = 0;

		while (left_end != 0 && right_end != n - 1) {
			if (flag)
				right_end = right_bubble(a, n, right_end);
			else
				left_end = left_bubble(a, n, left_end);
			flag = !flag;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("양방향 버블정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		bidirection_bubbleSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}