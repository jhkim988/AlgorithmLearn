import java.util.Scanner;

class Exercise06_18 {
	// Q18 도수 정렬의 각 단계에서 배열 a b f의 요솟값 변화를 출력하는 프로그램을 작성하세요
	static void abfPrint(int a[], int b[], int f[]) {
		System.out.print("a : ");
		for (int i : a)
			System.out.print(i+ " ");
		System.out.println();
		
		System.out.print("b : ");
		for (int i : b)
			System.out.print(i+ " ");
		System.out.println();
		
		System.out.print("f : ");
		for (int i : f)
			System.out.print(i+ " ");
		System.out.println("\n");
	}
	static void fSort(int[] a, int n, int max) {
		int[] f = new int[max + 1]; // 누적도수
		int[] b = new int[n]; // 작업용 목적 배열

		// 1단계
		for (int i = 0; i < n; ++i)
			f[a[i]]++;
		abfPrint(a, b, f);
		
		// 2단계
		for (int i = 1; i <= max; ++i)
			f[i] += f[i - 1];
		abfPrint(a, b, f);
		
		// 3단계
		for (int i = n - 1; i >= 0; --i)
			b[--f[a[i]]] = a[i];		
		abfPrint(a, b, f);
		
		// 4단계
		for (int i = 0; i < n; ++i)
			a[i] = b[i];
		abfPrint(a, b, f);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("도수 정렬");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		int max = x[0];
		for (int i = 1; i < nx; ++i)
			if (max < x[i])
				max = x[i];

		fSort(x, nx, max);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}