import edu.princeton.cs.algs4.StdRandom;

public class Exercise01_01_11 {
	// 1.1.11
	// boolean 타입 2차원 배열의 항목들을 출력하는 코드를 작성하라.
	// 항목의 값이 true면 *를, false면 공백을 출력한다.
	// 이때 행과 열의 번호도 출력한다.
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
