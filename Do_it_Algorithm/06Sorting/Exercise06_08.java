import java.util.Scanner;

class Exercise06_08 {
	// Q8 이미 정렬된 부분에선 이진 검색을 사용하여 삽입할 위치를 찾으세요.
	static int binary_Search(int[] a, int range_idx, int key) {
		// 배열 a에서 a[0] ~ a[range_idx]까지 binary search를 수행합니다.
		// a[i] <= key < a[i+1]을 만족하는 i를 반환합니다.
		int left_ptr = 0;
		int right_ptr = range_idx;

		if (a[left_ptr] > key)
			return -1;
		if (a[right_ptr] <= key)
			return range_idx;

		do {
			int center = (left_ptr + right_ptr) / 2;
			if (a[center] < key) {
				left_ptr = center;
			} else if (a[center] > key) {
				right_ptr = center;
			} else {
				return center;
			}
		} while (right_ptr - left_ptr > 1);

		return left_ptr;
	}

	static void binary_insertionSort(int[] a, int n) {
		for (int i = 1; i < n; ++i) {
			// a[0], ..., a[i-1]에 a[i]를 삽입한다.
			int idx = binary_Search(a, i - 1, a[i]);
			int temp = a[i];
			
			// a[0] < ... < a[idx] <= a[i] < a[idx+1] < ... a[i-1]
			// idx+1 ~ i-1을 오른쪽으로 한 칸씩 옮겨야한다.
			for (int j = i - 1; j > idx; --j)
				a[j + 1] = a[j];
			a[idx + 1] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("이진삽입정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		binary_insertionSort(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}