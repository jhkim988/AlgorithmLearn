import edu.princeton.cs.algs4.In;

public class Exercise01_02_15 {
	// 1.2.15 파일 입력
	// In 클래스의 readAllInts() 메서드를 String의 split() 메서드를 이용하여 static 메서드로 만들어보라
	public static int[] readAllInts(String name) {
		In in = new In(name);
		String input = in.readAll();
		String[] splitResult = input.split("\\s+"); // \\s+ whitespace
		int[] result = new int[splitResult.length];
		for(int i = 0; i < splitResult.length; i++) {
			result[i] = Integer.parseInt(splitResult[i]);
		}
		return result;
	}
}
