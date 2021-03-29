public class Interview02_01_3Sum {
	// 0. Sort array.
	// 1. For given x, find indices i and j such that a[i] + a[j] == - x by O(n).
	// 2. Traverse a[], for a[k], apply 1.
	// O(n^2)
	public static int count3Sum(int[] a) {
		int count = 0;
		for (int k = 0; k < a.length; k++) {
			int ptr1 = k + 1; // Careful for Not Doubly Count.
			int ptr2 = a.length - 1;
			while (ptr1 < ptr2) {
				if (a[ptr1] + a[ptr2] > -a[k])
					ptr2--;
				else if (a[ptr1] + a[ptr2] < -a[k])
					ptr1++;
				else {
					count++;
					System.out.println("3Sum : " + a[ptr1] + " "+ a[ptr2] + " " + a[k]);
					break;
				}
			}
		}
		System.out.println("The number of 3Sum-Combination : " + count);
		return count;
	}

	public static void main(String[] args) {
		int[] a = new int[] { -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7 };
		count3Sum(a);
	}
}
