import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_36 {
	static void knuthShuffle(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = i + StdRandom.uniform(N - i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	static double[][] shuffleTest(int M, int N) {
		double[][] result = new double[M][M];
		int[] arr = new int[M];

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < M; i++)
				arr[i] = i;
			knuthShuffle(arr);
			for (int i = 0; i < M; i++)
				result[i][arr[i]]++;
		}

		for (int i = 0; i < M; i++)
			for (int j = 0; j < M; j++)
				result[i][j] /= M;
		return result;
	}

	static double maxErr(double[][] arr, double value) {
		double[][] errMat = new double[arr[0].length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				errMat[i][j] = Math.abs(arr[i][j] - value);
			}
		}
		double max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (max < errMat[i][j])
					max = errMat[i][j];
			}
		}
		return max;
	}

	public static void main(String[] args) {
//		int M = Integer.parseInt(args[0]);
//		int N = Integer.parseInt(args[1]);
		int M = 100;
		int N = 1000;
		StdOut.println(maxErr(shuffleTest(M, N), ((double) N) / ((double) M)));
	}
}
