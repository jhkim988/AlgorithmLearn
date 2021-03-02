import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_20 {
	// 1.1.20
	// ln(N!)�� ����ϴ� ����Լ��� �ۼ��϶�
	
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
