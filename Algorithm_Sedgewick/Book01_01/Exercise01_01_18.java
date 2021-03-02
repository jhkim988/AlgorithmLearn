import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_18 {
	// 1.1.18
	// �Ʒ� ����Լ����� mystery(2, 25)�� mystery(3, 11)�� ���� �����ΰ�?
	// a, b�� ������ myster(a, b)�� � ���� �Ǵ��� ���϶�
	// +�� *�� �ٲٰ� return 0�� return 1�� �ٲ۴ٸ� mystery(a,b)�� � ���� �Ǵ°�?
	public static int mystery(int a, int b) {
		if (b == 0)
			return 0;
		if (b % 2 == 0)
			return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;
	} // b�� 2������ �ٲ۴�. ex) b = 0b10110, 1��� a�� ���� ���̴�. �� a * b

	public static int mystery2(int a, int b) {
		if (b == 0)
			return 1;
		if (b % 2 == 0)
			return mystery2(a * a, b / 2);
		return mystery2(a * a, b / 2) * a;
		// b�� 2������ �ٲ۴�. ex) b = 0b10110
		// return�� �α׸� ���غ���,
		// n�� 0���� ���ȣ���� ������ 1�� �ø���.
		// �ڿ������� 1�� ���� ������ nlog(a)�� ���ϴ� ���̴�.
		// �� a ^ b
	}

	public static void main(String[] args) {
		StdOut.println(mystery(3, 26));
		StdOut.println(mystery2(3, 4));
	}
}
