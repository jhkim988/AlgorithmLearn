import edu.princeton.cs.algs4.StdOut;

// 1.3.11
// ǥ�� �Է����κ��� ��ġ ���� ǥ������ �Է¹޾� ������ ����� �� �ᱣ���� ����ϴ� ���α׷� EvaluatePostfix�� �ۼ��϶�
// ���� ������������ �ۼ��� ���α׷��� ����� �������� ���� �� ���������� �Է����� �ϸ� Evaluate�� ���۰� �����ϰ� �ȴ�.
public class Exercise01_03_11 {
	static double EvaluatePostfix(String str) {
		String[] letters = str.split("\\s+");
		Stack<Double> vals = new Stack<Double>();
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals("+")) {
				vals.push(vals.pop() + vals.pop());
			} else if (letters[i].equals("-")) {
				double tmp = vals.pop();
				vals.push(vals.pop() - tmp);
			} else if (letters[i].equals("*")) {
				vals.push(vals.pop() * vals.pop());
			} else if (letters[i].equals("/")) {
				double tmp = vals.pop();
				vals.push(vals.pop() / tmp);
			} else { // number
				vals.push(Double.parseDouble(letters[i]));
			}
		}
		
		if (vals.size() != 1) {
			StdOut.println("Wrong");
		}
		return vals.pop();
	}
	
	public static void main(String[] args) {
		String test1 = "3 4 5 * 6 / +";
		String test2 = "2 3 4 * 5 6 / + +";
		StdOut.println(EvaluatePostfix(test1));
		StdOut.println(EvaluatePostfix(test2));
	}
}
