import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_21 {
	// 1.1.21
	// 표준 입력으로부터 여러 라인의 데이터를 입력받는 코드를 작성하라
	// 각 라인은 이름을 나타내는 문자열 하나와 두 개의 정수로 이루어진다.
	// 데이터를 입력 받은 다음 printf()를 이용하여 입력 받은 이름과 두 정수를 출력하고
	// 더불어서 앞의 정수를 뒤의 정수로 나눈 값을 소수점 셋째 자리까지 포매팅하고 줄바꿈 하며 출력하라.
	public static void main(String[] args) {
		String readAll = StdIn.readAll();
		String[] lines = readAll.split("\n");
		for (String line : lines) {
			String[] newLine = line.split(" ");
			String name = newLine[0];
			int integer1 = Integer.parseInt(newLine[1]);
			int integer2 = Integer.parseInt(newLine[2].substring(0, newLine[2].length() - 1));
			double divide = ((double) integer1 / (double) integer2);
			StdOut.printf("%s \t%d \t%d \t%.3f\n", name, integer1, integer2, divide);
		}

	}
}
