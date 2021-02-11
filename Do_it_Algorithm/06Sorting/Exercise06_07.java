import java.util.Scanner;

class Exercise06_07 {
	// Q7 단순 삽입 정렬에서 첫 번째 요소 a[0]부터 저장하지 않고 a[1]부터 저장하면
	// a[0]을 보초로 하여 삽입을 마치는 조건을 줄일 수 있습니다.
	// 이 아이디어를 적용하여 단순 삽입 정렬 메서드를 수정하세요.

	// 입력을 x[1] ... x[nx]까지로 받고, 맨 앞을 비워서 보초(centinel)로 사용한다.
	static void insertionSort_v2(int[] a, int n) {
		for (int i = 2; i < n; ++i) {
			int temp = a[0] = a[i]; // a[0]에 a[i]를 넣는다.
			int j = i;
			for (; a[j - 1] > temp; --j) {
				a[j] = a[j - 1];
			}
			if (j > 0)
				a[j] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("단순삽입정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx + 1];

		for (int i = 1; i <= nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		insertionSort_v2(x, nx);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 1; i <= nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}