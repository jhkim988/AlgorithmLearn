import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_05 {
	// 1.2.5
	// �Ʒ��� �ڵ�� � ���� ����ϴ°�?
	public static void main(String[] args) {
		String s = "Hello World";
		s.toUpperCase();
		s.substring(6, 11);
		StdOut.println(s); // Hello World
		// toUpperCase(), substring() ���� �޼���� �ν��Ͻ��� �ٲ��� �ʰ� ��ȯ�� string�� �����Ѵ�.
		// String ��ü�� �Һ��Ӽ��� ���ϰ� �ִ�.
	}
}
