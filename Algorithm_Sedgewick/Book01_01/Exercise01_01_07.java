import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_07 {
	// 1.1.7 �Ʒ��� �� �ڵ尡 � ���� ����ϴ��� ���϶�
	public static void main(String[] args) {
		// a - sqrt() Numerically
		double t = 9.0;
		while (Math.abs(t - 9.0 / t) > .001) {
			t = (9.0 / t + t) / 2.0;
		}
		StdOut.printf("%.5f\n", t);

		// b - 0 ~ 999������ ��
		int sum = 0;
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < i; j++)
				sum++;
		StdOut.println(sum);

		// c - i�� lg(1000 - 1) + 1ȸ �ݺ�, j�� 1000ȸ �ݺ�, ���� 1000(lg(999) + 1) = 1000
		sum = 0;
		for (int i = 1; i < 1000; i *= 2)
			for (int j = 0; j < 1000; j++)
				sum++;
		StdOut.println(sum);
	}
}
