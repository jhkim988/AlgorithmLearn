import java.util.*;

public class BinSearch {
	public static void main(String[] args) {
		final int len = 15;
		final int lim = 10;
		int[] seq = new int[len];
		for (int i = 0; i < len; i++) {
			seq[i] = (int) (Math.random() * lim);
		}

		Arrays.sort(seq);
		System.out.println(Arrays.toString(seq));
		System.out.println(binSearch(seq, 5));
	}

	static int binSearch(int[] seq, int key) {
		// convention:

		// 1. set init
		// check(lo) != check(hi)가 되도록 구간을 설정한다.
		// 문제 답이 최대 n일 때, hi = n으로 설정해선 안된다. n + 1로 설정한다.
		// 문제 답이 최소 0일 때, lo = 0으로 설정해선 안된다. -1로 설정한다.
		int lo = -1;
		int hi = seq.length;

		// 2. lo + 1 < hi로 설정해서 항상 lo < mid < hi를 만족하게 한다.(while 문을 돌면서 lo와 hi 사이에 적어도 한
		// 칸 존재)
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			// check(lo) == check(mid), lo에서의 체크값과 mid에서의 체크값이 같다면 lo = mid, 아니라면 hi = mid
			if (seq[mid] < key) { // check(x) = seq[x] < key
				lo = mid;
			} else {
				hi = mid;
			}
		}
		// 반복문이 끝나면 항상 hi = lo + 1를 만족한다.
		// 답이 lo인지 hi인지 check 함수를 고려하여 정한다.
		return seq[hi] == key ? hi : -hi - 1;
	}
}
