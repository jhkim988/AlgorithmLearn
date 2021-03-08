import edu.princeton.cs.algs4.StdOut;

// 1.3.9
// ǥ�� �Է����κ��� ���� ��ȣ�� ���� ������ �Է¹޾�,
// ���� ���� ������ ��ȣ �ֿ� �°� ���� ��ȣ�� �߰��� ������ ������ ����ϴ� ���α׷��� �ۼ��϶�
// ���� ��� �Ʒ��� ���� �Է¿� ����,
// 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
// �Ʒ��� ���� ����ؾ� �Ѵ�.
// ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
public class Exercise01_03_09 {
	public static String mathExpression(String str) {
		String[] letters = str.split("\\s+");
		Stack<String> numbers = new Stack<String>();
		Stack<String> operators = new Stack<String>();
		
		for(int i = 0; i < letters.length; i++) {
			if (letters[i].equals("+") || letters[i].equals("-") || letters[i].equals("*") || letters[i].equals("/")) { // operator
				operators.push(letters[i]);
			} else if (letters[i].equals(")")) { // parthenses
				String sum = operators.pop() + " " + numbers.pop();
				sum = "( " + numbers.pop() + " " + sum + ") ";
				numbers.push(sum);
			} else { // numbers
				numbers.push(letters[i] + " ");
			}
		}
		return numbers.peak();
	}
	
	public static void main(String[] args) {
		String test = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
		StdOut.println(mathExpression(test));
	}
}
