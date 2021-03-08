import edu.princeton.cs.algs4.StdOut;

// 1.3.4
// ǥ���Է����κ��� ���ڿ��� �Է¹޾� ���ڿ��� ���Ե� ��ȣ ¦�� �´��� Ȯ���ϴ� �׽�Ʈ Ŭ���̾�Ʈ Parentheses�� �ۼ��϶�
// ���� ��� [()]{}{[()()]()} �� ���ؼ��� ¦�� �����Ƿ� true��� ����ϰ�
// [(])�� ���ؼ��� ¦�� ���� �����Ƿ� false��� ����ؾ��Ѵ�.
public class Exercise01_03_04 {
	static boolean parentheses(String str) {
		Stack<Character> prnthss = new Stack<Character>();

		for (int i = 0; i < str.length(); i++) {
			char input = str.charAt(i);
			if (input == '[' || input == '(' || input == '{') {
				prnthss.push(str.charAt(i));
			} else if (input == ']' || input == ')' || input == '}') {
				if (prnthss.size() == 0)
					return false;
				char popped = prnthss.pop();
				if (input == ']') {
					if (popped != '[')
						return false;
				} else if (input == ')') {
					if (popped != '(')
						return false;
				} else { // (input == '}')
					if (popped != '{') {
						return false;
					}
				}
			} else {
				StdOut.println("Not Parentheses!");
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String test1 = "[()]{}{[()()]()}"; 
		String test2 = "[(])";
		
		StdOut.println("test1 : " + parentheses(test1));
		StdOut.println("test2 : " + parentheses(test2));
	}
}
