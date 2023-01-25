import java.util.*;

public class LIS {
	// LIS: Longest Increasing Sequence
	public static void main(String[] args) {
		final int len = 10;
		final int valLim = 30;
		int[] seq = new int[len];
		for (int i = 0; i < len; i++) {
			seq[i] = (int) (Math.random() * valLim);
		}
		System.out.println(Arrays.toString(seq));
	}

	static int useDoubleLoop(int[] seq) {
		int len = seq.length;
		int[] table = new int[len]; // table[i]: length of LIS seq[0 ... i] with seq[i] as last element.
		int max = 0;
		for (int i = 0; i < len; i++) {
			table[i] = 1; // itself
			for (int j = 0; j < i; j++) {
				if (seq[j] < seq[i]) { // [seq[j], seq[i]]: increasing subsequence
					if (table[i] < table[j] + 1)
						table[i] = table[j] + 1; // [... seq[j]] append seq[i]
				}
			}
			if (max < table[i])
				max = table[i];
		}
		return max;
	}

	static int useBinSearch(int[] seq) {
		// Get Length of LIS
		// length of LIS: ptr - 1;
		// table[k]: minimum of last element for all length k LIS.
		int len = seq.length;
		int[] table = new int[len + 1];
		int ptr = 1;
		for (int i = 0; i < len; i++) {
			if (table[ptr - 1] < seq[i]) {
				table[ptr++] = seq[i];
			} else {
				int find = Arrays.binarySearch(table, 0, ptr, seq[i]);
				if (find < 0) {
					find = -(find + 1);
				}
				table[find] = seq[i];
			}
		}
		return ptr - 1;
	}
}