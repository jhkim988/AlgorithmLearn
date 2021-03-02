import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_19 {
	// 1.1.19
	// �Ʒ��� �ڵ带 �������Ͽ� �������Ѻ���
	// 1�ð� �̳��� F(N)�� ���� �� �ִ� N�� �ִ��� �����ΰ�?
	// ���� �ð��� �پ�� �� �ֵ��� F(N)�� ��� ����� �迭�� �����ؼ� ��Ȱ���ϴ� �ڵ带 �߰��غ���.
	public static long F(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return F(N - 1) + F(N - 2);
		// F(0)�� F(1)�� ���ϴ� �ð��� t�ʶ�� �����غ���
		// F(2)�� 2t, F(3)�� 3t F(4)�� 5t, ..., F(N)�� F(N+1)t�ʰ� �ɸ���.
		// F(N+1) < 3600/t
		// F(N)�� ((1+sqrt(5))/2)^N / sqrt(5)�� �ٻ�Ǳ� ������
		// N + 1 < lg(3600sqrt(5)/t) / lg((1+sqrt(5))/2)���� �ִ� Ƚ���� ���� �� �ִ�.
	}

	public static long FibonacciArr(int N) {
		long[] arr = new long[N + 2];
		arr[0] = 0;
		arr[1] = 1;
		for (int i = 2; i < N + 1; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[N];
	}

	public static long FibonacciRotation(int N) {
		if (N == 0)
			return 0;
		if (N == 1 || N == 2)
			return 1;
	
		int status = 0;
		
		long temp0 = 0;
		long temp1 = 1;
		long temp2 = 1;

		for (int i = 3; i <= N; i++) {
			switch (status % 3) {
			case 0:
				temp0 = temp1 + temp2;
				status++;
				break;
			case 1:
				temp1 = temp2 + temp0;
				status++;
				break;
			case 2:
				temp2 = temp0 + temp1;
				status++;
				break;
			}
		}

		if (status % 3 == 0)
			return temp2;
		else if (status % 3 == 1)
			return temp0;
		else
			return temp1;

	}

	public static void main(String[] args) {
		for (int N = 0; N < 90; N++) {
			StdOut.println(N + " " + FibonacciRotation(N));
		}
	}
}