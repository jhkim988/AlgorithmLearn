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

// sol) <!!! This method call TOO MANY color() !!!> - wrong answer
// Use Two pointer: - left end and right end
// The left pointer approaches to the right and the right pointer approaches to the left to sort the array.
// if (arr[left], arr[right]) == (red, *), process left pointer.(left++)
// if (arr[left], arr[right]) == (*, blue), process right pointer.(right--)
// if (arr[left], arr[right]) == (blue, *), swap(left, right)
// if (arr[left], arr[right]) == (*, red), swqap(left, right)
// if (arr[left], arr[right]) == (white, white), use new pointer started left, and name it temp.
// traverse temp from left to right.
// if arr[temp] == red, swap(left, temp) and left++, temp++.
// if arr[temp] == blue, swap(temp, right) and right--, temp++.
// if arr[temp] == white, do nothing, temp++;

class Bucket { // array
	String[] pebbles; // only input - red white blue
	int length;

	private int callCountColor = 0;
	private int callCountSwap = 0;

	Bucket(int num) {
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

	void sort1(int[] ptrs, String[] words) { // both side, swap
		// ptrs = [lptr, rptr, done];
		words[0] = color(ptrs[0]);
		words[1] = color(ptrs[1]);
		while (ptrs[0] < ptrs[1]) {
			System.out.println(toString());			
			while (words[0].equals("red")) {
				words[0] = color(++ptrs[0]);
			}
			while (words[1].equals("blue")) {
				words[1] = color(--ptrs[1]);
			}
			if (ptrs[0] >= ptrs[1]) { // sort complete
				ptrs[2] = 1;
				return;
			}
			if (words[0].equals("blue")) {
				swap(ptrs[0], ptrs[1]);
				words[0] = words[1];
				words[1] = color(--ptrs[1]);
			} else if (words[1].equals("red")) {
				swap(ptrs[0], ptrs[1]);
				words[1] = words[0];
				words[0] = color(++ptrs[0]);
			} else { // both whites.
				break;
			}
		}
		if (ptrs[0] >= ptrs[1]) {// sort complete
			ptrs[2] = 1;
			return;
		}
	}

	void sort2(int[] ptrs) { // In middle, double white
		int tptr = ptrs[0] + 1;
		String temp = color(tptr);
		while (tptr < ptrs[1]) {
			System.out.println(toString());
			if (temp.equals("red")) {
				swap(ptrs[0], tptr);
				break;
			} else if (temp.equals("blue")) {
				swap(tptr, ptrs[1]);
				break;
			} else {
				temp = color(++tptr);
			}
		}
		if (tptr >= ptrs[1]) {
			ptrs[2] = 1;
		}
	}

	void dutchSort() {
		// Create Red, White, Blue arrays and Merge it. -> Not Constant extra space.
		// This method call too many color().. worst ~3n?
		int[] ptrs = new int[3];
		ptrs[0] = 0;
		ptrs[1] = length - 1;
		ptrs[2] = 0;

		String[] words = new String[2];

		while (ptrs[2] != 1) {
			sort1(ptrs, words);
			System.out.println(toString());
			sort2(ptrs);
			System.out.println(toString());
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
			Bucket arrBucket = new Bucket(num);
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

public class Interview04_03_Dutch_Flag {
	public static void main(String[] args) {
		Bucket.test(15, 50);
	}
}
