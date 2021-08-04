import java.util.Scanner;

class MergeSort {
	static int[] buff; // 작업용 배열

	static void __mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;

			__mergeSort(a, left, center); // 배열의 앞부분을 병합정렬
			__mergeSort(a, center + 1, right); // 배열의 뒷부분을 병합정렬

			for (i = left; i <= center; i++)
				buff[p++] = a[i]; // buff[] = a[left] ~ a[center]
			// p = center - left + 1, buff 배열의 크기
			// a[left] ... a[center] 와 a[center + 1] ... a[right]를 병합정렬한다.
			while (i <= right && j < p)
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			while (j < p)
				a[k++] = buff[j++];
			// 왜 while문이 하나 더 필요 없는지?
			// buff 배열에 있는 요소들이 먼저 병합정렬이 끝나면 남아있는 쪽은 그대로 자기 자리에 있어도 된다.
			// 서로 떨어져 있는 요소들을 교환하는 것이 아니므로 안정적인 정렬방법이다.
		}
	}

	static void mergeSort(int[] a, int n) {
		buff = new int[n];
		__mergeSort(a, 0, n - 1);
		buff = null;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("병합 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		mergeSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);

	}
}