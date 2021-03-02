import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_14 {
	// 1.1.14
	// int�� N�� �μ��� �޾Ƽ� log_2(N) ������ ���� ū ������ �����ϴ� static �޼��� lg()�� �����϶�.
	// ��, �ڹٿ��� �����Ǵ� ���� Ŭ���� Math�� �̿����� �ʰ� ���� �����϶�
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
