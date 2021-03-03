import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_27 {
	// 1.1.27 이항분포
	// 아래의 코드에서 binomial(100, 50, 0.25)를 계산할 때 재귀호출이 몇 번 발생하는지 추산하라
	// 중간 계산값들을 배열에 저장하여 재활용하는 방식으로 연산 시간을 개선해보라.

	// sol
	// case N < k : 2^(N+2) - 1
	// case N = k : B(N, N, p) = 2^(N + 1) + B(N-1, N-1, p), B(0, 0, p) = 1.0 =>
	// B(N, N, p) = 2^(N+2)-3
	// case N > k :
	// N - k = 1 => 2^(N+2) - 2N - 3
	// N - k = 2 => 2^(N+2) - N^2 - N - 5
	// N - k = 3 => 2^(N+2) - N^3/3 - 11N/3
	// ...
	// 대략 ~ 2*2^N

	public static double binomial(int N, int k, double p) {
		if (N == 0 && k == 0)
			return 1.0;
		if (N < 0 || k < 0)
			return 0.0;
		return (1 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
	}

	public static double binomialArr(int N, int k, double p) {
		double[][] arr = new double[N + 1][k + 1];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < k; j++)
				arr[i][j] = -1.0;
		arr[0][0] = 1.0;
		return binomialArr(N, k, p, arr);
	}

	public static double binomialArr(int N, int k, double p, double[][] arr) {
		if (N == 0 && k == 0) {
			return 1.0;
		}
		if (N < 0 || k < 0)
			return 0.0;
		
		if(arr[N][k] > 0)
			return arr[N][k];
		
		if (binomialArr(N - 1, k, p, arr) < 0 || binomialArr(N - 1, k - 1, p, arr) < 0) {
			binomialArr(N - 1, k - 1, p, arr);
			binomialArr(N - 1, k, p, arr);
		}
		arr[N][k] = p * binomialArr(N - 1, k - 1, p, arr) + (1 - p) * binomialArr(N - 1, k, p, arr);
		return arr[N][k];
	}

	public static void main(String[] args) {
		StdOut.println(binomial(35, 10, 0.5));
		StdOut.println(binomialArr(35, 10, 0.5));
	}
}
