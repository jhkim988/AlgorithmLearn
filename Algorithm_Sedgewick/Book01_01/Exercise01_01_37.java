import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_37 {
	// 1.1.37 잘못된 뒤섞기
	// shuffleTest 함수에서 i에서 N-1 사이의 정수가 아니라 0에서 N-1사이의 정수를 선택했다고 하자.
	// 이럴 경우 결과의 순서가 공정하게 N!개의 확률로 나오지 않음을 앞서의 ShuffleTest를 활용하여 확인하라
	static void knuthShuffle(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = i + StdRandom.uniform(N - i);
			// a[i], a[r] swap
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	static void wrongShuffle(int []a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int r = StdRandom.uniform(N);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	
	static double[][] shuffleTest(int M, int N) {
		double[][] result = new double[M][M];
		int[] arr = new int[M];

		for (int j = 0; j < N; j++) {
			// initialize
			for (int i = 0; i < M; i++)
				arr[i] = i;
			// shuffle
//			knuthShuffle(arr);
			wrongShuffle(arr);
			// i -> arr[i]
			for (int i = 0; i < M; i++)
				result[i][arr[i]]++;
		}

		printDoubleArr(result);
		return result;
	}

	static double maxErr(double[][] arr, double value) {
		double[][] errMat = new double[arr[0].length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				errMat[i][j] = Math.abs(arr[j][i] - value);
			}
		}
		double max = errMat[0][0];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (max < errMat[j][i])
					max = errMat[j][i];
			}
		}
		return max;
	}

	static void printDoubleArr(double[][] a) {
		for (int i = 0; i < a[0].length; i++) {
			StdOut.print("[ ");
			for (int j = 0; j < a.length; j++) {
				StdOut.print(a[i][j] + " ");
			}
			StdOut.println("]");
		}
	}

	public static void main(String[] args) {
//		int M = Integer.parseInt(args[0]);
//		int N = Integer.parseInt(args[1]);
		int M = 10;
		int N = 10000000;
		double esti = ((double) N) / ((double) M);
		double[][] result = shuffleTest(M, N);
		printDoubleArr(result);
		StdOut.println(maxErr(result, esti) / esti);
	}
}
