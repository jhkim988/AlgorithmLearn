import edu.princeton.cs.algs4.StdOut;

// Q1 Java autoboxing and equals().
// Consider  two double values a and b and their corresponding Double values x and y.
// Find values such that (a == b) is true but x.equals(y) is false.
// Find values such that (a == b) is false but x.equals(y) is true.

public class Interview08_01_JavaAutoboxingAndEquals {
	public static void main(String[] args) {
		double a = Double.NaN;
		double b = Double.NaN;
		Double x = new Double(a);
		Double y = new Double(b);
		
		StdOut.println("a == b : " + (a == b) + " - " + a + " == " + b);
		StdOut.println("x.equals(y) : " + x.equals(y) + " - " + x + ".equals(" + y + ")");
		
		a = 0.0;
		b = -0.0;
		x = new Double(a);
		y = new Double(b);
		
		StdOut.println("a == b : " + (a == b) + " - " + a + " == " + b);
		StdOut.println("x.equals(y) : " + x.equals(y) + " - " + x + ".equals(" + y + ")");
	}
}
