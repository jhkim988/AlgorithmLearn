import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
	// shuffle 하는 방법
	// 1. 정렬을 이용한 shuffle
	// 주어진 배열의 각각의 원소에 임의로 난수를 지정한다.
	// 그 난수의 크기 순으로 배열 원소를 정렬한다.
	// 굳이 정렬을 해야하는가?

	// 2. Knuth Shuffle
	// RandomWorld.java 과제에서 사용한 방법
	// i번째 카드를 섞는 방법 - 1 ~ (i+1)까지의 정수 중 임의로 하나를 뽑는다. 그 후 0 ~ i번째 카드와 swap 한다.
	// 선형 시간이 걸린다.

	public static void shuffle(Object[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = StdRandom.uniform(i + 1);
			exch(a, i, r);
		}
	}

	private static void exch(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// bug - online poker
	public static void main(String[] args) {
		int[] card = new int[52];
		
		// initialize
		for(int i = 0; i < 52; i++) {
			card[i] = i;
		}
		
		// shuffle
		for (int i = 1; i <= 52; i++) {
			int r = StdRandom.uniform(51) + 1; // 1 ~ 51
			int swap = card[r];
			card[r] = card[i];
			card[i] = swap;
		}
		
		// Bug 1. r이 52가 될 수 없다. 즉 마지막 카드는 계속 같은 자리에 있다.
		// Bug 2. shuffle이 uniform하지 않다.
		// Bug 3. 가능한 셔플의 개수는 훨씬 많은데, 32-bit 시드를 사용한다. 셔플의 개수 52!는 2^32보다 크다.
		// Bug 4. 랜덤 시드로 자정 이후 밀리 세컨드를 사용하기 때문에 셔플 가능한 경우의 수는 더 줄어든다.
	}
}
