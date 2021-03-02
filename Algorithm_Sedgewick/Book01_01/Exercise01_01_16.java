import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_16 {
	// 1.1.16
	// 아래의 코드를 보고 exR1(6)의 리턴 값을 구하라
	public static String exR1(int n) {
		if (n <= 0)
			return "";
		return exR1(n - 3) + n + exR1(n - 2) + n;
	}
	
	public static void main(String[] args) {
		StdOut.println(exR1(6));
		
		// exR1(1) - 11
		// exR1(2) - 22
		// exR1(3) - 3113
		// exR1(4) - 114224
		// exR1(5) - 22531135
		// exR1(6) - 311361142246
	}
}
