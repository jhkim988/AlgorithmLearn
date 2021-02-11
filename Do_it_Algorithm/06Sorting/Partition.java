import java.util.Scanner;

class Partition {
	static void swap(int[] a, int idx1, int idx2) {
		int temp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = temp;
	}

	// 배열의 가운데에 있는 요소를 피벗으로 정하고, 그룹을 나눈다.
	static void partition(int[] a, int n) {
		int pl = 0;
		int pr = n - 1;
		int x = a[n / 2];

		do {
			while (x > a[pl])
				pl++;
			while (x < a[pr])
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);
		System.out.println("피벗의 값은 " + x + "입니다.");
		
		System.out.println("피벗 이하의 그룹");
		for(int i = 0; i <= pl - 1; i++)
			System.out.print(a[i] + " ");
		System.out.println();
		
		if (pr + 1 < pl) {
			System.out.println("피벗과 일치하는 그룹");
			for(int i = pr + 1; i < pl; ++i)
				System.out.print(a[i] + " ");
			System.out.println();
		}
		System.out.println("피벗 이상의 그룹");
		for(int i = pl + 1; i < n; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("배열을 나눕니다.");
		System.out.print("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		stdIn.close();
		partition(x ,nx);
	}
}