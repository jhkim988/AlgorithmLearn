import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_35 {
	// 1.1.35 주사위 시뮬레이션
	// 아래의 코드는 두 개의 주사위를 던질 때 나올 수 있는 위면 숫자의 합을 정확한 확률 분푸로 계산해낸다.
	// disk[k]의 값은 주사위 윗면 숫자의 합이 k가 될 확률을 의미한다.
	// 주사위를 N번 던지는 시뮬레이션을 수행하여 각 합계 값이 어떤 빈도로 나타나는지 기록하여 disk[k]의 값이 맞는지 확인해보라.
	// 시뮬레이션은 1과 6사이의 랜덤 정숫값 두 개를 생성하여 더하는 방식으로 수행할 수 있다.
	// N이 얼마나 커야 실험치와 계산치가 소수점 이하 셋째 자리를 기준으로 같아지는 알아보라.
	static double[] diceThrow(int N) {
		int SIDES = 6;
		double[] table = new double[2 * SIDES + 1];

		for (int i = 0; i < N; i++) {
			int val1 = StdRandom.uniform(6) + 1;
			int val2 = StdRandom.uniform(6) + 1;
			table[val1 + val2] += 1.0;
		}

		for (int i = 2; i <= 2 * SIDES; i++)
			table[i] /= 36.0;

		return table;
	}

	static double[] diceThrowProb() {
		int SIDES = 6;
		double[] dist = new double[2 * SIDES + 1];
		for (int i = 1; i <= SIDES; i++)
			for (int j = 1; j <= SIDES; j++)
				dist[i + j] += 1.0;

		for (int k = 2; k <= 2 * SIDES; k++)
			dist[k] /= 36.0;

		return dist;
	}

	static double arrayMax(double[] table) {
		double max = table[0];
		for (int i = 1; i < table.length; i++)
			if (max < table[i])
				max = table[i];
		return max;
	}

	static void printArr(double[] a) {
		System.out.print("[ ");
		for(int i = 0; i < a.length; i++)
			System.out.printf("%5.5f ", a[i] );
		System.out.println("]");
	}
	public static void main(String[] args) {
		int N = 1;
		int SIDES = 6;
		double error = 0.1;
		double[] dice = new double[2 * SIDES + 1];
		double[] diceProb = diceThrowProb();
		double[] substraction = new double[2 * SIDES + 1];
		do {
			System.out.println("Process : " + N);
			dice = diceThrow(N);
			for (int i = 2; i <= 2 * SIDES; i++)
				substraction[i] = Math.abs(dice[i] - diceProb[i]);
			N++;
		} while (arrayMax(substraction) > error);

		printArr(dice);
		printArr(diceProb);
		printArr(substraction);
	}
}
