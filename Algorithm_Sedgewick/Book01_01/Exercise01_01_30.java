
public class Exercise01_01_30 {
	// 1.1.30 배열 연습
	// N * N 크기의 boolean 타입 배열 a[][]을 만들고,
	// a[i][j]의 값이 i와 j가 서로소일 때만 true이고, 나머지는 false가 되도록 값을 채워보라.
	public static int gcd(int a, int b) { // gcd testing
		int max = (a > b) ? a + 1 : b + 1;
		int[][] table = new int[max][max];
		return gcd(a, b, table);
	}
	public static int gcd(int a, int b, int[][] table) {
		if(a == 0 || b == 0)
			return -1;
		
		if (table[a][b] > 0)
			return table[a][b];
		
		int atmp = a;
		int btmp = b;
		int r = a % b;
		while (r != 0) { // get gcd
			atmp = btmp;
			btmp = r;
			r = atmp % btmp;
		}
		
		r = a % b;
		table[a][b] = btmp;
		table[b][a] = btmp;
 		while (r != 0) {
			table[a][b] = btmp;
			table[b][a] = btmp;
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}

	public static boolean[][] relativelyPrime(int N) {
		int[][] arr = new int[N][N];
		boolean[][] result = new boolean[N][N];
		// relatively prime with zero -> make no sense
		for(int i = 0; i < N; i++) {
			arr[0][i] = -1;
			arr[i][0] = -1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) { // 대칭이기 때문에 Upper triangle 부분만 구하고 복사한다.
				if (gcd(i,j, arr) == 1) {
					result[i][j] = true;
					result[j][i] = true;
				}
			}
		}
		doubleArrPrint(arr);
		return result;
	}

	public static void doubleArrPrint(int[][] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[ ");
			for(int j = 0; j < a[0].length; j++) {
				System.out.printf("%2d ", a[j][i]);
			}
			System.out.println("]");
		}
	}
	
	public static void doubleArrPrint(boolean[][] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[ ");
			for(int j = 0; j < a[0].length; j++) {
				System.out.printf("%6b ", a[j][i]);
			}
			System.out.println("]");
		}
	}
	public static void main(String[] args) {
		int N = 10;
		doubleArrPrint(relativelyPrime(N));
		
		int gcdtest1 = 5;
		int gcdtest2 = 8;
		System.out.println("gcd(" + gcdtest1 + ", " + gcdtest2 + ") = " + gcd(5, 8));
	}
}
