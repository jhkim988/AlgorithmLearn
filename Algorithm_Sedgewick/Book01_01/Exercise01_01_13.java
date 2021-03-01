import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_13 {
	// 1.1.13
	// 2���� �迭�� ��� ���� �ٲٴ�(90�� ȸ��) �ڵ带 �ۼ��϶�.
	// �� ������ M�� N���̾��ٸ� �ٲ� ����� N�� M���̾�� �Ѵ�.
	static int[][] rotation(int[][] a) {
		int rowNum = a.length;
		int colNum = a[0].length;

		int[][] rot = new int[colNum][rowNum];
		for (int i = 0; i < colNum; i++)
			for (int j = 0; j < rowNum; j++)
				rot[i][j] = a[rowNum - 1 - j][i];
		return rot;
	}

	static void printDoubleArr(int[][] a) {
		int rowNum = a.length;
		int colNum = a[0].length;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowNum = 10;
		int colNum = 8;
		int[][] arr = new int[rowNum][colNum];
		for(int i = 0; i < rowNum; i++) {
			for(int j = 0; j < colNum; j++) {
				arr[i][j] = StdRandom.uniform(10);
			}
		}
		printDoubleArr(arr);
		System.out.println("----- rotation -----");
		printDoubleArr(rotation(arr));
	}
}
