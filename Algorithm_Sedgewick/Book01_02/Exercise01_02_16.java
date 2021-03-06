import edu.princeton.cs.algs4.StdOut;

class Rational {
	private int numerator;
	private int denominator;

	private static int getGCD(int a, int b) {
		if (a % b == 0)
			return b;
		return getGCD(b, a % b);
	}

	Rational(int numerator, int denominator) {
		if(denominator == 0) {
			throw new ArithmeticException();
		}
		int gcd = getGCD(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	Rational plus(Rational b) {
		int common = this.denominator * b.denominator;
		int thisNumerator = this.numerator * b.denominator;
		int thatNumerator = b.numerator * this.denominator;
		int sum = thisNumerator + thatNumerator;
		return new Rational(sum, common);
	}

	Rational minus(Rational b) {
		int common = this.denominator * b.denominator;
		int thisNumerator = this.numerator * b.denominator;
		int thatNumerator = b.numerator * this.denominator;
		int substraction = thisNumerator - thatNumerator;
		return new Rational(substraction, common);
	}

	Rational times(Rational b) {
		int common = this.denominator * b.denominator;
		int mult = this.numerator * b.numerator;
		return new Rational(mult, common);
	}

	Rational divideBy(Rational b) {
		int common = this.denominator * b.numerator;
		int mult = this.numerator * b.denominator;
		return new Rational(mult, common);
	}

	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		if (this.getClass() != that.getClass())
			return false;
		Rational it = (Rational) that;
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

public class Exercise01_02_16 {
	// 1.2.16 À¯¸®¼ö
	// À¯¸®¼ö¿¡ ´ëÇÑ µ¡¼À, »¬¼À, °ö¼À, ³ª´°¼ÀÀ» Áö¿øÇÏ´Â ºÒº¯ µ¥ÀÌÅÍ Å¸ÀÔ RationalÀ» ±¸ÇöÇÏ¶ó
	public static void main(String[] args) {
		Rational half = new Rational(1, 2);
		Rational quarter = new Rational(1, 4);
		StdOut.println(half.plus(quarter));
		StdOut.println(half.minus(quarter));
		StdOut.println(half.times(quarter));
		StdOut.println(half.divideBy(quarter));
		StdOut.println(half.equals(quarter));
		StdOut.println(half.equals(new Rational(4, 8)));
	}
}
