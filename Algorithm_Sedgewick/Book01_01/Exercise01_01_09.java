import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_09 {
	// 1.1.9
	// ���� ���� N�� �������� ǥ���� String Ÿ�� ���� s�� ��ȯ�ϴ� �ڵ带 �ۼ��϶�
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
