import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_35 {
	// 1.1.35 �ֻ��� �ùķ��̼�
	// �Ʒ��� �ڵ�� �� ���� �ֻ����� ���� �� ���� �� �ִ� ���� ������ ���� ��Ȯ�� Ȯ�� ��Ǫ�� ����س���.
	// disk[k]�� ���� �ֻ��� ���� ������ ���� k�� �� Ȯ���� �ǹ��Ѵ�.
	// �ֻ����� N�� ������ �ùķ��̼��� �����Ͽ� �� �հ� ���� � �󵵷� ��Ÿ������ ����Ͽ� disk[k]�� ���� �´��� Ȯ���غ���.
	// �ùķ��̼��� 1�� 6������ ���� ������ �� ���� �����Ͽ� ���ϴ� ������� ������ �� �ִ�.
	// N�� �󸶳� Ŀ�� ����ġ�� ���ġ�� �Ҽ��� ���� ��° �ڸ��� �������� �������� �˾ƺ���.
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
