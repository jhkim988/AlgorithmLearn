import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_19 {
	// 1.1.19
	// 아래의 코드를 컴파일하여 구동시켜보라
	// 1시간 이내에 F(N)을 구할 수 있는 N의 최댓값은 무엇인가?
	// 연산 시간이 줄어들 수 있도록 F(N)의 계산 결과를 배열에 저장해서 재활용하는 코드를 추가해보라.
	public static long F(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return F(N - 1) + F(N - 2);
		// F(0)과 F(1)을 구하는 시간이 t초라고 가정해보면
		// F(2)는 2t, F(3)은 3t F(4)는 5t, ..., F(N)은 F(N+1)t초가 걸린다.
		// F(N+1) < 3600/t
		// F(N)은 ((1+sqrt(5))/2)^N / sqrt(5)로 근사되기 때문에
		// N + 1 < lg(3600sqrt(5)/t) / lg((1+sqrt(5))/2)으로 최대 횟수를 구할 수 있다.
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