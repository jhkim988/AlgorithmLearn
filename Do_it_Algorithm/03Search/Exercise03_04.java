import java.util.Scanner;

class Exercise03_04 {
	static void arrowMaker(int length, int x_pos, int pl_pos) {
		String arrow = "";
		for (int i = 0; i < pl_pos; ++i)
			arrow += "    ";
		
		if (length <= 2) {
			System.out.println(arrow + "<-x->");
			return;
		}
		
		arrow += "<-  ";
		for (int i = 1; i < length; ++i) {
			if ((i + pl_pos) == x_pos)
				arrow += "x   ";
			else
				arrow += "    ";
		}
		arrow += "->";

		System.out.println(arrow);
	}

	static int binarySearch_print(int[] a, int n, int key) {
		int pl = 0;
		int pr = n - 1;
		
		String chart1 = "  |";

		System.out.print(" " + chart1);
		for (int i = 0; i < n; ++i)
			System.out.printf("%-4d", i);

		System.out.print("\n---+");
		for (int i = 0; i < n; ++i)
			System.out.print("----");
		System.out.println();
		
		do {
		int pc = (pl + pr) / 2;
		System.out.print(" " + chart1);
		arrowMaker(pr - pl, pc, pl);
		
		System.out.printf("%3d", pc);
		System.out.print("|");
		for (int i = 0; i < n; ++i)
			System.out.printf("%-4d", a[i]);
		System.out.println();
		
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

		int idx = binarySearch_print(x, num, ky);
		
		if (idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
	}
}