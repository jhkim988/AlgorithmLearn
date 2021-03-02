import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Exercise01_01_21 {
	// 1.1.21
	// ǥ�� �Է����κ��� ���� ������ �����͸� �Է¹޴� �ڵ带 �ۼ��϶�
	// �� ������ �̸��� ��Ÿ���� ���ڿ� �ϳ��� �� ���� ������ �̷������.
	// �����͸� �Է� ���� ���� printf()�� �̿��Ͽ� �Է� ���� �̸��� �� ������ ����ϰ�
	// ���Ҿ ���� ������ ���� ������ ���� ���� �Ҽ��� ��° �ڸ����� �������ϰ� �ٹٲ� �ϸ� ����϶�.
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
