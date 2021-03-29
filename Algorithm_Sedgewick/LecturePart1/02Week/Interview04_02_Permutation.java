import java.util.Arrays;

public class Interview04_02_Permutation {
//	Q2 Permutation.
//	Given two integer arrays of size n,
//	design a subquadratic algorithm to determine whether one is a permutation of the other.
//	That is, do they contain exactly the same entries but, possibly, in a different order.

// 1. If length of two arrays are different, return false.
// 2. Sort two arrays.
// 3. Use pointer. If there is different array element in same pointer(a[ptr]!=b[ptr]) return false.
// 4. If the traverse is terminated without returning false, return true.
	public static boolean isPermute(int[] a, int[] b) {
		if(a.length != b.length) return false;
		int num = a.length;
		int temp1[] = Arrays.copyOf(a, num);
		int temp2[] = Arrays.copyOf(b, num);
		Arrays.sort(temp1);
		Arrays.sort(temp2);
		int ptr = 0;
		while(ptr < num) {
			if (temp1[ptr] != temp2[ptr])
				return false;
			ptr++;
		}
		return true;
	}

	public static void main(String[] args) {
		int a[] = {1, 2, 3, 4, 5};
		int b[] = {5, 4, 3, 2, 1};

		System.out.println(isPermute(a, b));
	}
}
