import java.util.Scanner;

class Exercise03_02 {
	// 선형 검색의 스캐닝 과정을 상세하게 출력하는 프로그램을 작성하세요.
	// 각 행의 맨 왼쪽에 현재 검색하는 요소의 인덱스를 출력하고,
	// 현재 검색하고 있는 요소 위에 별표 기호를 출력하세요.

	static void seqSearch_print(int[] a, int n, int key) {
		System.out.print("  |");
		for (int i = 0; i < n; ++i)
			System.out.printf("%-4d", i);

		System.out.print("\n--+");
		for (int i = 0; i < n; ++i)
			System.out.print("-----");
		System.out.println();

		String space = "    ";
		String chart2 = " |";
		for (int i = 0; i < n; ++i) {
			String chart1 = "  |";
			for (int j = 0; j < i; ++j)
				chart1 += space;
			System.out.println(chart1 + "*");
			
			System.out.print(i + chart2);
			for (int j = 0; j < n; ++j)
				System.out.printf("%-4d", a[j]);
			System.out.println();

			if (a[i] == key) {
				System.out.println(key + "은(는) x[" + i + "]에 있습니다.");
				return;
			}

		}
		System.out.println("그 값의 요소가 없습니다.");
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];

		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		System.out.print("검색할 값 : ");
		int ky = stdIn.nextInt();
		stdIn.close();

		seqSearch_print(x, num, ky);
	}
}