import java.util.Scanner;

class BinSearch {
	// Binary Search - 검색 대상(배열)이 오름차순(혹은 내림차순)으로 정렬돼 있음을 가정하고 찾는다.
	// 검색을 반복할 때마다 검색 범위가 절반이 되기 때문에 평균 log(n)회 비교한다.
	static int binSearch(int[] a, int n, int key) {
		int pl = 0;
		int pr = n - 1;

		do {
			int pc = (pl + pr) / 2;
			if (a[pc] < key) {
				pl = pc + 1;
			} else if (a[pc] > key) {
				pr = pc - 1;
			} else
				return pc;
		} while (pl <= pr);
		
		return -1;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];

		System.out.println("오름차순으로 입력하세요.");
		System.out.print("x[0] : ");
		x[0] = stdIn.nextInt();

		for (int i = 1; i < num; ++i) {
			do {
				System.out.print("x[" + i + "] : ");
				x[i] = stdIn.nextInt();
			} while (x[i] < x[i - 1]);
		}

		System.out.print("검색할 값 : ");
		int ky = stdIn.nextInt();
		stdIn.close();

		int idx = binSearch(x, num, ky);

		if (idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");

	}
}