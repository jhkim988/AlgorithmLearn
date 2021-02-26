import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Dijkstra2Stack {
	public static void main (String[] args) {
		LinkedStack<String> ops = new LinkedStack<String>();
		LinkedStack<Double> vals = new LinkedStack<Double>();
		
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("("))
				;
			else if (s.equals("+"))
				ops.push(s);
			else if (s.equals("*"))
				ops.push(s);
			else if (s.equals(")")) {
				String op = ops.pop();
				if (op.equals("+")) vals.push(vals.pop() + vals.pop());
				else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
			}
			else
				vals.push(Double.parseDouble(s));
		}
		StdOut.println(vals.pop());
	}
}
