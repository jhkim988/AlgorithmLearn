import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_07 {
	// 1.1.7 아래의 각 코드가 어떤 값을 출력하는지 구하라
	public static void main(String[] args) {
		// a - sqrt() Numerically
		double t = 9.0;
		while (Math.abs(t - 9.0 / t) > .001) {
			t = (9.0 / t + t) / 2.0;
		}
		StdOut.printf("%.5f\n", t);

		// b - 0 ~ 999까지의 합
		int sum = 0;
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < i; j++)
				sum++;
		StdOut.println(sum);

		// c - i는 lg(1000 - 1) + 1회 반복, j는 1000회 반복, 따라서 1000(lg(999) + 1) = 1000
		sum = 0;
		for (int i = 1; i < 1000; i *= 2)
			for (int j = 0; j < 1000; j++)
				sum++;
		StdOut.println(sum);
	}
}
