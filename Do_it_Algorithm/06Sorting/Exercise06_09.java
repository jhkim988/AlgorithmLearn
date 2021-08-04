import java.util.Scanner;

class Exercise06_09 {

	static int shellSort_v1(int[] a, int n) {
		int move = 0;
		for (int h = n / 2; h > 0; h /= 2) {
			for (int i = h; i < n; ++i) {
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h) {
					a[j + h] = a[j];
					move++;
				}
				a[j + h] = temp;
				move++;
			}
		}
		return move;
	}

	static int shellSort_v2(int[] a, int n) {
		int move = 0;

		int h;
		for (h = 1; h < n / 9; h = 3 * h + 1)
			;

		for (; h > 0; h /= 3) {
			for (int i = h; i < n; ++i) {
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h) {
					a[j + h] = a[j];
					move++;
				}
				a[j + h] = temp;
				move++;
			}
		}
		return move;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("Shell 정렬(이동 횟수 출력)");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		int[] y = new int[nx]; // 정렬 전 배열 복제
		for (int i = 0; i < nx; ++i) {
			y[i] = x[i];
		}

		stdIn.close();

		int move1 = shellSort_v1(x, nx);
		int move2 = shellSort_v2(y, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);

		System.out.println("버전 1의 이동횟수 : " + move1);
		System.out.println("버전 2의 이동횟수 : " + move2);
	}
}