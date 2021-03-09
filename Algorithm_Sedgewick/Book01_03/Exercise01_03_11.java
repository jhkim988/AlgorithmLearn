import edu.princeton.cs.algs4.StdOut;

// 1.3.11
// 표준 입력으로부터 후치 연산 표현식을 입력받아 수식을 계산한 후 결괏값을 출력하는 프로그램 EvaluatePostfix를 작성하라
// 앞의 연습문제에서 작성한 프로그램의 출력을 파이프를 통해 이 연습문제의 입력으로 하면 Evaluate의 동작과 동일하게 된다.
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
