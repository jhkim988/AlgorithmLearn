import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_39 {
	// 1.1.39 무작위 짝짓기
	// BinarySearch에 명령줄 인수로 int 값 T를 입력 받는 테스트 클라이언트를 만든다.
	// 이 테스트 클라이언트는 N = 10^3, 10^4, 10^5, 10^6 각각에
	// 6자리 int 값을 2개의 배열로 생성하고 두 배열에 같은 같이 있는지 검사한다.
	// 이 검사를 T번 수행하여, 각각의 N에 대해서 두 배열에 동시에 존재하는 값들의 평균값을 출력한다.
	static double randomMatch(int N, int T) {
		double sum = 0.0;
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		
		int trial = 0;
		while (trial < T) {
			// initialize
			for (int i = 0; i < N; i++) {
				int r1 = 100000 + StdRandom.uniform(900000);
				int r2 = 100000 + StdRandom.uniform(900000);
				arr1[i] = r1;
				arr2[i] = r2;
			}

			// count match
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			int pointer1 = 0;
			int pointer2 = 0;
			int counter = 0;

			while (pointer1 < N && pointer2 < N) {
				if (arr1[pointer1] < arr2[pointer2])
					pointer1++;
				else if (arr1[pointer1] > arr2[pointer2])
					pointer2++;
				else {
					pointer1++;
					pointer2++;
					counter++;
				}
			}
			sum += (double) counter;
			trial++;
		}
		return sum / ((double) T);
	}

	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
//		int T = 100;
		int N1 = 10 * 10 * 10;
		int N2 = N1 * 10;
		int N3 = N2 * 10;
		int N4 = N3 * 10;

		StdOut.println(randomMatch(N1, T));
		StdOut.println(randomMatch(N2, T));
		StdOut.println(randomMatch(N3, T));
		StdOut.println(randomMatch(N4, T));
	}
}
