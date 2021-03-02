import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_24 {
	// 1.1.24
	// 최대공약수를 유클리드 알고리즘을 통해서 구해보자.
	public static int gcd(int a, int b) {
		int q = b;
		int r = a % b;
		StdOut.println(a + " = " + (a/b) + " * " + q + " + " + r);
		if (r == 0)
			return q;
		return gcd(b, r);
	}
	
	public static void main(String[] args) {
		StdOut.println(gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
	}
}
