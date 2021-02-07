import java.util.Scanner;

class Exercise03_05 {
	static int binSearchX(int[] a, int n, int key) {
		int pl = 0;
		int pr = n - 1;
		int pc;
		boolean flag = false;
		do {
			pc = (pl + pr) / 2;
			if (a[pc] > key)
				pr = pc - 1;
			else if (a[pc] < key)
				pl = pc + 1;
			else {
				flag = true;
				break;
			}
		} while (pl <= pr);
		if (!flag)
			return -1;
		while (pc > 0 && a[pc - 1] == key)
			pc--;
		return pc;

	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();

		int[] x = new int[num];

		System.out.print("x[0] : ");
		x[0] = stdIn.nextInt();

		for (int i = 1; i < num; ++i) {
			do {
				System.out.print("x[" + i + "] : ");
				x[i] = stdIn.nextInt();
			} while (x[i] < x[i - 1]);
		}
		System.out.print("검색할 값: ");
		int ky = stdIn.nextInt();
		stdIn.close();

		int idx = binSearchX(x, num, ky);

		if (idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
	}
}