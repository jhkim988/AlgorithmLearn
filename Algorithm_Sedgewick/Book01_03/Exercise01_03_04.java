import edu.princeton.cs.algs4.StdOut;

// 1.3.4
// 표준입력으로부터 문자열을 입력받아 문자열에 포함된 괄호 짝이 맞는지 확인하는 테스트 클라이언트 Parentheses를 작성하라
// 예를 들어 [()]{}{[()()]()} 에 대해서는 짝이 맞으므로 true라고 출력하고
// [(])에 대해서는 짝이 맞지 않으므로 false라고 출력해야한다.
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
