import edu.princeton.cs.algs4.Date;

public class HashTables {
	// Symbolic Table --Hash function--> Array
	
	// Java's Hash code convention
	// Requirement
	// if (x.equals(y)), then (x.hashCode == y.hashCode)
	// Highly Desirable
	// if (!x.equals(y)), then (x.hashCode != y.hashCode)
	
	// Java hashCode: 32bit integer
	// Default impletation: memory address of x
	
	// table position must be uniformly distritubed
	
	// User-Defined Types - Standard Recipe
	// 31*x + y rule
	// primitive type: Use Wrapper type hashCode()
	// field is null: 0
	// field is reference type: use hashCode()
	// field is an array, apply to each entry
	private final class Transaction implements Comparable<Transaction> {
		private final String who;
		private final Date when;
		private final double amount;
		public Transaction(String who, Date when, double amount) {
			this.who = who;
			this.when = when;
			this.amount = amount;
		}
		@Override
		public int compareTo(HashTables.Transaction o) {
			// TODO Auto-generated method stub
			return 0;
		}
		public boolean equals(Object y) {
			return true;
		}
		public int hashCode() {
			int hash = 17; // nonzero constant(small prime number)
			hash = 31 * hash + who.hashCode(); // Honer's method
			hash = 31*hash + when.hashCode();
			hash = 31*hash + ((Double) amount).hashCode();
			return hash; // 32-bit integer
		}
	}
	
	// universal hash functions exist
	// Need to use the whole key to compute hash code
	
	// Hash code: -2^31 ~ 2^31 - 1
	// Hash function: 0 ~ M-1 (for use as array index)
	// private int hash(Key key) { return key.hashCode() % M; } // Bug
	// private int hash(Key key) { return Math.abs(key.hashCode()) % M; } // 1-in-a-billion bug (-2^31)
	// private int hash(Key key) { return (key.hashCode() & 0x7fffffff) % M; } // correct
	
	// Uniform hashing assumption
	// each key is equally likely to hash to an integer between 0 and M - 1
	// Bins and balls - Throw balls uniformly at random into M bins
	// Expect two balls in the same bin after ~ sqrt(pi * M / 2) tosses.(birthday problem)
	// Expect every bin has >= 1 ball after M ln M tosses.(coupon collector problem)
	// After M tosses, expect most loaded bin has theta(log M / log(log M)) balls(load balancing)
}