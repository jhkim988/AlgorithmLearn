import edu.princeton.cs.algs4.StdRandom;
//Q3 Dutch national flag.
//Given an array of n buckets, each containing a red, white, or blue pebble,
//sort them by color. The allowed operations are:
//	swap(i,j):  swap the pebble in bucket i with the pebble in bucket j.
//	color(i): determine the color of the pebble in bucket i.
//The performance requirements are as follows:
//	At most n calls to color().
//	At most n calls to swap().
//	Constant extra space.

//sol)
// Use 3 pointer
// low, mid, high
// red, red, ..., red, white, white, ..., white, ???, ???, ???, ..., ???, blue, blue, blue 
//                      low                      mid                 high
// Check ??? and properly swap using mid-pointer.
// Be careful if you swap(mid, high), Don't advance mid pointer.
// Because we don't still know what is mid's and high elements

class BucketRetry { // array
	String[] pebbles; // only input - red white blue
	int length;

	private int callCountColor = 0;
	private int callCountSwap = 0;

	BucketRetry(int num) {
		length = num;
		pebbles = new String[num];
	}

	void swap(int i, int j) {
		callCountSwap++;
		String temp = pebbles[i];
		pebbles[i] = pebbles[j];
		pebbles[j] = temp;
	}

	String color(int i) {
		callCountColor++;
		return pebbles[i];
	}

	public String toString() {
		String result = "[";
		for (int i = 0; i < length - 1; i++) {
			result += pebbles[i] + ", ";
		}
		result += pebbles[length - 1] + "], \tCall Count color() : " + callCountColor + "  \tCall Count swap() : "
				+ callCountSwap;
		return result;
	}

	void dutchSort() {
		int lo = 0;
		int hi = length - 1;
		int mid = 0;

		while (mid <= hi) {
			System.out.println(toString());
			System.out.println("lo : " + lo + " \t mid : " + mid + " \thi :" + hi);
			String midColor = color(mid);
			if (midColor.equals("red")) {
				swap(lo++, mid++);
			} else if (midColor.equals("white")) {
				mid++;
			} else if (midColor.equals("blue")) {
				swap(mid, hi--);
			}
		}
	}

	boolean isSorted() {
		int ptr = 0;
		while (ptr < length - 1) {
			if (pebbles[ptr].equals("white") && pebbles[ptr + 1].equals("red"))
				return false;
			if (pebbles[ptr].equals("blue") && pebbles[ptr + 1].equals("red")
					|| pebbles[ptr].equals("blue") && pebbles[ptr + 1].equals("white"))
				return false;
			ptr++;
		}
		return true;
	}

	static void test(int size, int trials) {
		int num = size;
		boolean flag = true;
		int tryNum = 0;
		while (flag && tryNum < trials) {
			BucketRetry arrBucket = new BucketRetry(num);
			for (int i = 0; i < num; i++) {
				if (StdRandom.bernoulli(1.0 / 3.0)) {
					arrBucket.pebbles[i] = "red";
				} else if (StdRandom.bernoulli(1.0 / 3.0)) {
					arrBucket.pebbles[i] = "white";
				} else {
					arrBucket.pebbles[i] = "blue";
				}
			}
			System.out.println("----- Sort " + tryNum + " -----");
			System.out.println(arrBucket);
			arrBucket.dutchSort();
			System.out.println(arrBucket);
			flag = arrBucket.isSorted();
			System.out.println("isSorted : " + flag);
			tryNum++;
		}
	}
}

public class Interview04_03_Dutch_Flag_Retry {
	public static void main(String[] args) {
		BucketRetry.test(15, 50);
	}
}
