import edu.princeton.cs.algs4.StdOut;

// 1.3.10
// ���� ������ ����� ��� ǥ������ �Է¹޾� ��ġ ������ ������� ��ȯ�ϴ� ���� ���α׷� InfixToPostfix�� �ۼ��϶�
// ��) 3 + 4 * 5 / 6 => 3 4 5 * 6 / +
public class Exercise01_03_10 {
	static String InfixPostfix(String str) {
		String[] letters = str.split("\\s+");
		Stack<String> vals = new Stack<String>();
		Stack<String> ops = new Stack<String>();

		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals("*") || letters[i].equals("/")) {
				String sum = letters[i + 1] + " " + letters[i] + " ";
				sum = vals.pop() + sum;
				vals.push(sum);
				i++;
			} else if (letters[i].equals("+") || letters[i].equals("-")) {
				ops.push(letters[i]+ " ");
			} else {
				vals.push(letters[i] + " ");
			}
		}

		while (!ops.isEmpty()) {
			String sum = vals.pop() + ops.pop();
			sum = vals.pop() + sum;
			vals.push(sum);
		}

		String result = vals.pop();
		if (!vals.isEmpty())
			StdOut.println("Stack vals is Not Empty.");
		return result;
	}

	public static void main(String[] args) {
		String test1 = "3 + 4 * 5 / 6";
		String test2 = "2 + 3 * 4 + 5 / 6";
		StdOut.println(InfixPostfix(test1));
		StdOut.println(InfixPostfix(test2));
	}
}
