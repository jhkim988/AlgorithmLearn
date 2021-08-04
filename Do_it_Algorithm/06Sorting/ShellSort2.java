import java.util.Scanner;

class ShellSort2 {
	// h를 선택하는 방법에 대해서.
	// n = 8이고, h를 4, 2, 1로 선택한다고 가정한다.
	// x = [8, 1, 4, 2, 7, 6, 3, 5]
	// h = 4일 때는 [8, 7], {1, 6}, [4, 3], {2, 5}끼리 정렬한다.
	// h = 2일 때는 [8, 4, 7, 3], {1, 2, 6, 5}끼리 정렬한다.
	// 즉, []와 {}는 섞이지 않기 때문에 기껏 그룹을 나눴어도 h = 1일 때 동일한 상태가 돼 버린다.
	// 이를 해결하기 위해서는 h가 서로 배수가 되지 않도록 해야한다.
	// 또한 h의 초깃값이 너무 크면 효과가 없기 때문에 h < n / 9로 설정한다.

	// 셸 정렬의 시간 복잡도는 O(n^1.25)로, 버블/단순선택/단순삽입의 시간복잡도인 O(n^2)보다 빠르다.
	// 멀리 떨어져 있는 요소를 교환하기 때문에 안정적이지 않다.
	
	static void shellSort(int[] a, int n) {
		int h;
		for (h = 1; h < n / 9; h = 3 * h + 1)
			;

		for (; h > 0; h /= 3) {
			for (int i = h; i < n; ++i) {
				int j;
				int temp = a[i];
				for (j = i - h; j >= 0 && a[j] > temp; j -= h)
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