import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
	// 1. ū Ʈ���� ���� Ʈ���� ��ĥ ��, ū Ʈ���� ���� Ʈ�� �Ʒ��� ������ Ʈ���� ���̰� ���� �������.
	// 2. �� Ʈ���� ��� ���� ��� ����ؼ� Ʈ���� �Ը� �ľ��ϰ�, ū Ʈ�� �Ʒ��� ���� Ʈ���� �ֵ��� �Ѵ�.

	// �߰����� �迭 sz[]�� �ʿ��ϴ�.
	// sz[]�� �� ��� i�� ���ؼ�, i�� ��Ʈ�� �Ͽ� ���� Ʈ���� ��� ���� ����Ѵ�.
	// union ������ �ϸ鼭 ���ŵȴ�.

	public int[] id;
	private int[] sz;

	WeightedQuickUnionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; ++i) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) { // ���̿� ����ϴµ�, ������ �ִ��� lg(N)�̴�.
		while (id[i] != i)
			i = id[i];
		return i;
	}

	void union(int p, int q) { // lg(N)�� ����Ѵ�.
		int prt = root(p);
		int qrt = root(q);
		
		if (prt == qrt) return;
		if (sz[prt] < sz[qrt]) {
			id[prt] = qrt;
			sz[qrt] += sz[prt];
		} else {
			id[qrt] = prt;
			sz[prt] += sz[qrt];
		}
	}
	
	boolean connected(int p, int q) { // lg(N)�� ����Ѵ�.
		return root(p) == root(q);
	}
	
	// Ʈ���� ������ ��Ʈ�� �󸶳� ������ �ִ����� ����Ͽ� �ð��� �ɸ���.
	// Ʈ���� ����� depth�� lg(N)���� �۴�.
	// 1. ���� ����� ���̰� ������°�?
	// x�� Ʈ�� T1�� ���� ����� ��������. x�� ���̰� �þ ���� T1�� �ٸ� Ʈ�� T2�� �պ��� ���̴�.
	// �� �� ���� Ʈ���� ū Ʈ���� �պ��Ǵ� ��Ģ�� ������, T2�� Ʈ���� �� ũ��.
	// ���� ����� ���̰� 1 �þ�ٴ� ���� Ʈ���� ��� ������ �ּ� 2�谡 �ƴٴ� �ǹ��̴�.
	// ��� ������ N����� ����, Ʈ���� �� ��� �Ǵ� ������ ���ƾ� lg(N)�� �ߴٴ� �ǹ��̴�.
	public static void main(String[] args) {
		// 1. ��ü�� ������ �Է� �޴´�. ���⼭ UF ��ü�� �����Ѵ�.
		// 2. � ��ü���� ���� �� �ִ��� �˱� ���� ������ �� �־� �Է� �޴´�.
		// 3. ������ ��ü�� ����Ѵ�.
		
		int N = StdIn.readInt();
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " " + q);
			}
		}
		
		for (int i = 0; i < N; ++i)
			System.out.print(uf.id[i] + " ");
	}
}
