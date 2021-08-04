import java.util.Scanner;

class FSort{
	// 도수정렬 알고리즘은 4단계로 이루어진다.
	// 1. 도수분포표 작성
	// 2. 누적도수분포표 작성
	// 3. 목적 배열 만들기
	// 4. 배열의 복사
	
	static void fSort(int[] a, int n, int max) {
		int[] f = new int[max + 1]; // 누적도수
		int[] b = new int[n]; // 작업용 목적 배열
		
		// MyTry
//		for(int i = 0; i < n; ++i) f[a[i]]++;
//		for(int i = 0; i < max; ++i) f[i + 1] += f[i];
//		for(int i = 0; i < n; ++i) b[--f[a[i]]] = a[i]; // 역순으로 하는 이유가 있는지?
//		for(int i = 0; i < n; ++i) a[i] = b[i];
		
		for(int i = 0; i < n; ++i) f[a[i]]++;
		for(int i = 1; i <= max; ++i) f[i] += f[i - 1];
		for(int i = n - 1; i >= 0; --i) b[--f[a[i]]] = a[i]; // 역순으로 하는 이유가 있는지?
		for(int i = 0; i < n; ++i) a[i] = b[i];
		// 역순으로 하지 않고 처음부터 스캔하면 안정적이지 않게 된다.
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
		for (int i = 1; i <nx; ++i)
			if (max < x[i])
				max = x[i];
		
		fSort(x, nx, max);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; ++i)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}