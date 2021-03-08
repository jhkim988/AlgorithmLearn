import edu.princeton.cs.algs4.StdOut;

// 1.3.5
// 아래의 코드에서 N이 50일 때 어떤 값이 출력되는가?
// N의 값으로 양의 정수가 주어질 때 아래 코드가 무슨 일을 하는지 상위 수준에서 설명하라
public class Exercise01_03_05 {
	public static void mainS(String[] args) {
		int N = 100;
		Stack<Integer> stack = new Stack<Integer>();
		while(N > 0) {
			stack.push(N % 2);
			N = N / 2;
		}
		for(int d : stack)
			StdOut.print(d); // N의 이진수 출력
		StdOut.println(); 
	}
}
