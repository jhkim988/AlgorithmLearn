import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_18 {
	// 1.1.18
	// 아래 재귀함수에서 mystery(2, 25)와 mystery(3, 11)의 값은 무엇인가?
	// a, b가 양수라면 myster(a, b)는 어떤 값이 되는지 구하라
	// +를 *로 바꾸고 return 0을 return 1로 바꾼다면 mystery(a,b)는 어떤 값이 되는가?
	public static int mystery(int a, int b) {
		if (b == 0)
			return 0;
		if (b % 2 == 0)
			return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;
	} // b를 2진수로 바꾼다. ex) b = 0b10110, 1대신 a를 넣은 값이다. 즉 a * b

	public static int mystery2(int a, int b) {
		if (b == 0)
			return 1;
		if (b % 2 == 0)
			return mystery2(a * a, b / 2);
		return mystery2(a * a, b / 2) * a;
		// b를 2진수로 바꾼다. ex) b = 0b10110
		// return에 로그를 취해보면,
		// n을 0부터 재귀호출할 때마다 1씩 늘린다.
		// 뒤에서부터 1이 나올 때마다 nlog(a)을 더하는 것이다.
		// 즉 a ^ b
	}

	public static void main(String[] args) {
		StdOut.println(mystery(3, 26));
		StdOut.println(mystery2(3, 4));
	}
}
