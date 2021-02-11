import java.util.Scanner;

class ShellSort {
	// 셸 정렬은 단순삽입정렬의 장점은 살리고, 단점은 보완하여 좀 더 빠르게 정렬하는 알고리즘입니다.
	// 단순삽입정렬의 장점 - 정렬을 마쳤거나, 정렬을 마친 상태에 가까우면 정렬 속도가 매우 빠르다.
	// 단순삽입정렬의 단점 - 삽입할 위치가 멀리 떨어져 있으면 이동(대입)해야하는 횟수가 많아진다.

	static void shellSort(int[] a, int n) {
		for (int h = n / 2; h > 0; h /= 2) {
			for (int i = h; i < n; ++i) { // 단순삽입정렬에서 a[1] ~ a[n-1]까지 반복하는 것과 같다.
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h) // a[i]를 a[0] ~ a[i-1] 의 적당한 곳에 삽입한다.(간격이 h임)
					a[j + h] = a[j];
				a[j + h] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Shell 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		shellSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}