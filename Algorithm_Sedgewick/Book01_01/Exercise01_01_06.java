import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_06 {
	// 1.1.6 아래의 코드는 어떤 값을 출력하는가?
	
	public static void main(String [] args) {
		int f = 0;
		int g = 1;
		for (int i = 0; i <= 15; i++) {
			StdOut.println(f);
			f = f + g;
			g = f - g;
		}
	}
	
	// sol)
	// f : 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610
 	// g : 1 0 1 1 2 3 5 8  13 21 34 55 89  144 233 377
	
	// why?
	// f(n+1) = f(n) + g(n);
	// g(n+1) = f(n + 1) - g(n);

	// g(n) = f(n+1)-f(n)
	// f(n+2)-f(n+1) = f(n+1)-f(n+1)+f(n)
	// f(n+1) = f(n+1) + f(n)
	// f : Fibonacci sequence
}
