import edu.princeton.cs.algs4.StdOut;

// 1.3.5
// �Ʒ��� �ڵ忡�� N�� 50�� �� � ���� ��µǴ°�?
// N�� ������ ���� ������ �־��� �� �Ʒ� �ڵ尡 ���� ���� �ϴ��� ���� ���ؿ��� �����϶�
public class Exercise01_03_05 {
	public static void mainS(String[] args) {
		int N = 100;
		Stack<Integer> stack = new Stack<Integer>();
		while(N > 0) {
			stack.push(N % 2);
			N = N / 2;
		}
		for(int d : stack)
			StdOut.print(d); // N�� ������ ���
		StdOut.println(); 
	}
}
