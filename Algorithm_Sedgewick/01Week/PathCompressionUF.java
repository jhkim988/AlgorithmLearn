
public class PathCompressionUF {
	// ��� ����
	// ��� p�� ��Ʈ�� ã�� ��, ��� �� �ִ� ��� ��带 ��Ʈ�� �����Ų��.
	
	private int[] id;
	private int[] sz;

	PathCompressionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; ++i) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) {
		while (id[i] != i) {
			id[id[i]] = id[i]; // Ʈ���� �����ϰ� �� �� �ִ�.
			i = id[i];
			}
		return i;
	}

	void union(int p, int q) {
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
	
	boolean connected(int p, int q) {
		return root(p) == root(q);
	}
}
