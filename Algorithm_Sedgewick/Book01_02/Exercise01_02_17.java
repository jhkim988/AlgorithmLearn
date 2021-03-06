import edu.princeton.cs.algs4.StdOut;

class ValidRational {
	private int numerator;
	private int denominator;

	private static int getGCD(int a, int b) {
		if (a % b == 0)
			return b;
		return getGCD(b, a % b);
	}

	ValidRational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new ArithmeticException();
		}
		int gcd = getGCD(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	static boolean overflowOccur(int a, String operator, int b) {
		if (operator.equals("+")) {
			if (b > 0) {
				if (a > Integer.MAX_VALUE - b)
					return true;
				else
					return false;
			} else {
				if (a < Integer.MIN_VALUE - b) {
					return true;
				} else
					return false;
			}
		} else if (operator.equals("-")) {
			if (b > 0) {
				if (a < Integer.MIN_VALUE + b)
					return true;
				else
					return false;
			} else {
				if (a > Integer.MAX_VALUE + b) {
					return true;
				} else
					return false;
			}
		} else if (operator.equals("*")) {
			if ((a > 0 && b > 0) || (a < 0 && b < 0) ) {
				if (a > Integer.MAX_VALUE / b)
					return true;
				else
					return false;
			} else {
				if (a < Integer.MAX_VALUE / b) {
					return true;
				} else
					return false;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	ValidRational plus(ValidRational b) {
		int common = this.denominator * b.denominator;
		assert (!overflowOccur(this.denominator, "*", b.denominator));
		
		int thisNumerator = this.numerator * b.denominator;
		assert(!overflowOccur(this.numerator, "*", b.denominator));
		
		int thatNumerator = b.numerator * this.denominator;
		assert(!overflowOccur(b.numerator, "*", this.denominator));
		
		int sum = thisNumerator + thatNumerator;
		assert(!overflowOccur(thisNumerator, "+", thatNumerator));
		
		return new ValidRational(sum, common);
	}

	ValidRational minus(ValidRational b) {
		int common = this.denominator * b.denominator;
		assert(!overflowOccur(this.denominator, "*", b.denominator));
		
		int thisNumerator = this.numerator * b.denominator;
		assert(!overflowOccur(this.numerator, "*", b.denominator));
		
		int thatNumerator = b.numerator * this.denominator;
		assert(!overflowOccur(b.numerator, "*", this.denominator));
		
		int substraction = thisNumerator - thatNumerator;
		assert(!overflowOccur(thisNumerator, "-", thatNumerator));
		return new ValidRational(substraction, common);
	}

	ValidRational times(ValidRational b) {
		int common = this.denominator * b.denominator;
		assert(!overflowOccur(this.denominator, "*", b.denominator));
		int mult = this.numerator * b.numerator;
		assert(!overflowOccur(this.numerator, "*", b.numerator));
		return new ValidRational(mult, common);
	}

	ValidRational divideBy(ValidRational b) {
		int common = this.denominator * b.numerator;
		assert(!overflowOccur(this.denominator, "*", b.numerator));
		int mult = this.numerator * b.denominator;
		assert(!overflowOccur(this.numerator, "*", b.denominator));
		return new ValidRational(mult, common);
	}

	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		if (this.getClass() != that.getClass())
			return false;
		ValidRational it = (ValidRational) that;
		if (this.numerator != it.numerator)
			return false;
		if (this.denominator != it.denominator)
			return false;
		return true;
	}

	public String toString() {
		return numerator + "/" + denominator;
	}
}

public class Exercise01_02_17 {
	// 1.2.16 À¯¸®¼ö
	// À¯¸®¼ö¿¡ ´ëÇÑ µ¡¼À, »¬¼À, °ö¼À, ³ª´°¼ÀÀ» Áö¿øÇÏ´Â ºÒº¯ µ¥ÀÌÅÍ Å¸ÀÔ RationalÀ» ±¸ÇöÇÏ¶ó
	public static void main(String[] args) {
		ValidRational half = new ValidRational(1, 2);
		ValidRational quarter = new ValidRational(1, 4);
		StdOut.println(half.plus(quarter));
		StdOut.println(half.minus(quarter));
		StdOut.println(half.times(quarter));
		StdOut.println(half.divideBy(quarter));
		StdOut.println(half.equals(quarter));
		StdOut.println(half.equals(new ValidRational(4, 8)));
	}
}
