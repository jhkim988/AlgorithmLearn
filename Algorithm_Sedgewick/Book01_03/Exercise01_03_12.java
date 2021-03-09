import edu.princeton.cs.algs4.StdOut;

public class Exercise01_03_12 {
	static Stack<String> copy(Stack<String> stk) {
		Stack<String> tmp = new Stack<String>();
		Stack<String> cpy = new Stack<String>();
		for(String el : stk)
			tmp.push(el);
		for(String el : tmp)
			cpy.push(el);
		return cpy;
	}
	
	public static void main(String[] args) {
		Stack<String>stk1 = new Stack<String>();
		for(int i = 0; i < 10; i++)
			stk1.push("Hello " + i + " World!");
		Stack<String> stk2 = copy(stk1);
		
		for(String el : stk1)
			StdOut.println("stk1 " + el);
		
		for(String el : stk2)
			StdOut.println("stk2 " + el);
	}
}
