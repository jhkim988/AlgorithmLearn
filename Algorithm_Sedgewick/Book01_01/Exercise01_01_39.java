import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_39 {
	// 1.1.39 ������ ¦����
	// BinarySearch�� ����� �μ��� int �� T�� �Է� �޴� �׽�Ʈ Ŭ���̾�Ʈ�� �����.
	// �� �׽�Ʈ Ŭ���̾�Ʈ�� N = 10^3, 10^4, 10^5, 10^6 ������
	// 6�ڸ� int ���� 2���� �迭�� �����ϰ� �� �迭�� ���� ���� �ִ��� �˻��Ѵ�.
	// �� �˻縦 T�� �����Ͽ�, ������ N�� ���ؼ� �� �迭�� ���ÿ� �����ϴ� ������ ��հ��� ����Ѵ�.
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
