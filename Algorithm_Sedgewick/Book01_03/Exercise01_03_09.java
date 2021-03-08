import edu.princeton.cs.algs4.StdOut;

// 1.3.9
// 표준 입력으로부터 우측 괄호가 빠진 수식을 입력받아,
// 이항 연산 단위로 괄호 쌍에 맞게 우측 괄호가 추가된 동등한 수식을 출력하는 프로그램을 작성하라
// 예를 들어 아래와 같은 입력에 대해,
// 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
// 아래와 같이 출력해야 한다.
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
