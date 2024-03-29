import java.io.*;
import java.util.*;

public class RabinKarp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] str = br.readLine().toCharArray();
		char[] pat = br.readLine().toCharArray();
		for (int idx : rabinKarp(str, pat)) {
			bw.write(Integer.toString(idx));
			bw.write(' ');
		}
		bw.flush();
	}

	static Iterable<Integer> rabinKarp(char[] str, char[] pat) {
		long key = 31;
		long exp = 1;
		long d = 1_000_000_007;
		long strHash = 0;
		long patHash = 0;
		for (int i = 0; i < pat.length; i++) {
			strHash += str[i] * exp % d;
			strHash %= d;
			patHash += pat[i] * exp % d;
			patHash %= d;
			exp = exp * key % d;
		}
		long rev = pow(key, d - 2, d);
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i + pat.length - 1 < str.length; i++) {
			if (strHash == patHash)
				que.add(i);
			if (i + pat.length == str.length)
				break;
			strHash += (d - str[i]) % d;
			strHash %= d;
			strHash += str[i + pat.length] * exp % d;
			strHash %= d;
			strHash = strHash * rev % d;
		}
		return que;
	}

	static long pow(long base, long exp, long mod) {
		if (exp == 0)
			return 1;
		if (exp == 1)
			return base % mod;
		long half = pow(base, exp / 2, mod);
		if (exp % 2 == 0)
			return half * half % mod;
		return half * half % mod * base % mod;
	}
}
