import java.util.*;

public class KMP {
	// time complexity: O(N + M)
	// N: text length
	// M: pattern length

	// T: A B C D A B C D A B D E
	// P: A B C D A B D
	// O O O O O O X get information, P[0...5] same, P[6] different
	// A B C D A B D next find, start with pattern[3]
	// preprocess: fail -> jump pos
	public static void main(String[] args) {
		String text = "ILoveAlgorithmILoveJava";
		String pattern = "Love";
		ArrayList<Integer> indices = kmpMatch(text, pattern);
		for (int idx : indices) {
			System.out.println(idx);
		}
	}

	static ArrayList<Integer> kmpMatch(String text, String pattern) {
		ArrayList<Integer> indices = new ArrayList<>();
		int[] skip = skip(pattern);
		char[] textArr = text.toCharArray();
		char[] patternArr = pattern.toCharArray();

		// pointer of text, pattern
		int pt = 0;
		int pp = 0;
		while (pt < textArr.length) {
			if (textArr[pt] == patternArr[pp]) {
				pt++;
				pp++;
			}
			if (pp == patternArr.length) {
				indices.add(pt - pp + 1);
				pp = skip[pp - 1];
			} else if (pt < textArr.length && textArr[pt] != patternArr[pp]) {
				if (pp != 0) {
					pp = skip[pp - 1];
				} else {
					pt++;
				}
			}
		}
		return indices;
	}

	static int[] skip(String pattern) {
		char[] patternArr = pattern.toCharArray();
		int[] memo = new int[patternArr.length];
		int pt = 1;
		int pp = 0;
		while (pt < patternArr.length) {
			if (patternArr[pt] == patternArr[pp]) {
				pp++;
				memo[pt] = pp;
				pt++;
			} else if (pp == 0) {
				memo[pt] = 0;
				pt++;
			} else {
				pp = memo[pp - 1];
			}
		}
		return memo;
	}

	static int[] pi(char[] pattern) {
		int n = pattern.length;
		int[] pi = new int[n];

		// pi[i]: max len prefix = suffix in pattern[0...i] (prefix != pattern[0...i])
		pi[0] = 0;
		int j = 0;
		for (int i = 1; i < n; i++) {
			while (j > 0 && pattern[i] != pattern[j])
				j = pi[j - 1];
			if (pattern[i] == pattern[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	static ArrayList<Integer> kmp(char[] str, char[] pat) {
		ArrayList<Integer> ret = new ArrayList<>();
		int[] pi = pi(pat);
		int j = 0;
		for (int i = 0; i < str.length; i++) {
			while (j > 0 && str[i] != pat[j])
				j = pi[j - 1];
			if (str[i] == pat[j]) {
				if (j == pat.length - 1) {
					ret.add(i - pat.length + 1);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
		return ret;
	}
}