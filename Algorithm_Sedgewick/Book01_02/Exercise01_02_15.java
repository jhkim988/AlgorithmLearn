import edu.princeton.cs.algs4.In;

public class Exercise01_02_15 {
	// 1.2.15 ���� �Է�
	// In Ŭ������ readAllInts() �޼��带 String�� split() �޼��带 �̿��Ͽ� static �޼���� ������
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
