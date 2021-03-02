import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_14 {
	// 1.1.14
	// int값 N을 인수로 받아서 log_2(N) 이하의 가장 큰 정수를 리턴하는 static 메서드 lg()를 구현하라.
	// 단, 자바에서 제공되는 편의 클래스 Math를 이용하지 않고 직접 구현하라
	public static int lg(int N) {
		if (N <= 0)
			throw new IllegalArgumentException();
		int lgN = 0;
			for (int i = 2; i <= N; i *= 2)
				lgN++;
			return lgN;		
	}

	public static void main(String[] args) {
		int N = 280; // test
		StdOut.println(lg(N));
	}
}
