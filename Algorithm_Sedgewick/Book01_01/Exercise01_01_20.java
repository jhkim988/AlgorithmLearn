import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_20 {
	// 1.1.20
	// ln(N!)을 계산하는 재귀함수를 작성하라
	
	static double logFactorial(int N) {
		if (N < 0)
			throw new IllegalArgumentException();
		if (N == 0)
			return 0;
		return Math.log(N) + logFactorial(N - 1);
	}
	
	public static void main(String[] args) {
		StdOut.println(logFactorial(10));
	}
	
}
