import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_09 {
	// 1.1.9
	// 양의 정수 N을 이진수로 표현한 String 타입 변수 s로 변환하는 코드를 작성하라
	public static void main(String[] args) {
		int N = 11; // testing
		String s = "";
		for (int i = N; i > 0; i /= 2) {
			s = (i % 2) + s;
		}
		StdOut.println(s);
		// Integer.toBinaryString(N);
	}
}
