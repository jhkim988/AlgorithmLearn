import java.util.Arrays;
import java.util.Scanner;

class ArraySortTester {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];

		for (int i = 0; i < num; ++i) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		stdIn.close();

		Arrays.sort(x); // quick sort를 개선한 것으로, 안정적이지 않다.

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < num; ++i)
			System.out.println("x[" + i + "] = " + x[i]);

	}
}