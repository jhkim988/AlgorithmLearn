import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
	// shuffle �ϴ� ���
	// 1. ������ �̿��� shuffle
	// �־��� �迭�� ������ ���ҿ� ���Ƿ� ������ �����Ѵ�.
	// �� ������ ũ�� ������ �迭 ���Ҹ� �����Ѵ�.
	// ���� ������ �ؾ��ϴ°�?

	// 2. Knuth Shuffle
	// RandomWorld.java �������� ����� ���
	// i��° ī�带 ���� ��� - 1 ~ (i+1)������ ���� �� ���Ƿ� �ϳ��� �̴´�. �� �� 0 ~ i��° ī��� swap �Ѵ�.
	// ���� �ð��� �ɸ���.

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
		
		// Bug 1. r�� 52�� �� �� ����. �� ������ ī��� ��� ���� �ڸ��� �ִ�.
		// Bug 2. shuffle�� uniform���� �ʴ�.
		// Bug 3. ������ ������ ������ �ξ� ������, 32-bit �õ带 ����Ѵ�. ������ ���� 52!�� 2^32���� ũ��.
		// Bug 4. ���� �õ�� ���� ���� �и� �����带 ����ϱ� ������ ���� ������ ����� ���� �� �پ���.
	}
}
