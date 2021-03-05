import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_07 {
	// 1.2.7
	// 아래의 재귀 함수는 어떤 값을 리턴하는가?
	public static String mystery(String s) {
		int N = s.length();
		if (N <= 1)
			return s;
		String a = s.substring(0, N / 2);
		String b = s.substring(N / 2, N);
		return mystery(b) + mystery(a);
	} // 문자열을 거꾸로 뒤집는다.
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StdOut.println(mystery(test));
	}
}
