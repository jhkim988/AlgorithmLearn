import java.util.Scanner;

class Exercise06_19{
	// Q.19 요솟값의 범위가 min 이상 max 이하이고 요소의 개수가 n개인 배열 a를 도수정렬하는 메서드를 작성하세요
	static void fSort(int[] a, int n, int min, int max) {
		int[] f = new int[max - min + 1]; // 누적도수
		int[] b = new int[n]; // 작업용 목적 배열
		
		
		for(int i = 0; i < n; ++i) f[a[i] - min]++;
		for(int i = 1; i <= max - min ; ++i) f[i] += f[i - 1];
		for(int i = n - 1; i >= 0; --i) b[--f[a[i] - min]] = a[i]; 
		for(int i = 0; i < n; ++i) a[i] = b[i];


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
		
		int min = x[0];
		for (int i = 1; i < nx; ++i) {
			if (min > x[i])
				min = x[i];
		}
		
		int max = x[0];
		for (int i = 1; i <nx; ++i)
			if (max < x[i])
				max = x[i];
		
		fSort(x, nx, min, max);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}