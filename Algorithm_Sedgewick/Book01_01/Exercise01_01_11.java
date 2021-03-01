import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_11 {
	// 1.1.11
	// boolean Ÿ�� 2���� �迭�� �׸���� ����ϴ� �ڵ带 �ۼ��϶�.
	// �׸��� ���� true�� *��, false�� ������ ����Ѵ�.
	// �̶� ��� ���� ��ȣ�� ����Ѵ�.
	public static void main(String[] args) {
		int rowNum = 10;
		int colNum = 8;
		boolean[][] boolArr = new boolean[rowNum][colNum];
		
		// randomly initialize
		for(int i = 0; i < rowNum; i++)
			for(int j = 0; j <colNum; j++)
				if(StdRandom.bernoulli())
					boolArr[i][j] = true;
		
		// print
		for(int i = 0; i < rowNum; i++) {
			for(int j = 0; j < colNum; j++) {
				System.out.print("\t(" + i + ", " + j + ") : ");
				if (boolArr[i][j])
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
}
