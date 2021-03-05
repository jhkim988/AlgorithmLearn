import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_07 {
	// 1.2.7
	// �Ʒ��� ��� �Լ��� � ���� �����ϴ°�?
	public static String mystery(String s) {
		int N = s.length();
		if (N <= 1)
			return s;
		String a = s.substring(0, N / 2);
		String b = s.substring(N / 2, N);
		return mystery(b) + mystery(a);
	} // ���ڿ��� �Ųٷ� �����´�.
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StdOut.println(mystery(test));
	}
}
