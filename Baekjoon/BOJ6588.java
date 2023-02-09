import java.io.*;
import java.util.*;

public class BOJ6588 {
	private static class Prime {
		boolean[] isPrime;

		Prime(int n) {
			isPrime = new boolean[n];
			Arrays.fill(isPrime, true);
			isPrime[0] = isPrime[1] = false;
			for (int i = 2; i*i <= n; i++) {
				if (!isPrime[i]) continue;
				for (int j = i<<1; j < n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		boolean isPrime(int x) {
			return isPrime[x];
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Prime p = new Prime(1_000_001);

		StringBuilder sb = new StringBuilder();
		test: for (int num = Integer.parseInt(br.readLine()); num != 0; num = Integer.parseInt(br.readLine())) {
			int ptr1 = 3;
			int ptr2 = num-3;
			while (ptr1 <= ptr2) {
				if (p.isPrime(ptr1) && p.isPrime(ptr2)) {
					sb.append(num).append(" = ").append(ptr1).append(" + ").append(ptr2).append('\n');
					continue test;
				}
				ptr1 += 2;
				ptr2 -= 2;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
}
