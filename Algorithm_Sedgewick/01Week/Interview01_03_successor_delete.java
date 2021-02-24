public class Interview01_03_successor_delete {
	private int num;
	private int[] id;
	private int[] sz;
	private int[] largest;
	private boolean[] deleted;

	Interview01_03_successor_delete(int N) {
		num = N;
		id = new int[N];
		sz = new int[N];
		largest = new int[N]; // largest[p]에는 p를 루트로 하는 서브트리의 최댓값을 저장한다.
		deleted = new boolean[N];
		for (int i = 0; i < N; ++i) {
			id[i] = i;
			sz[i] = 1;
			largest[i] = i;
			deleted[i] = false;
		}
	}

	private int root(int i) {
		while (id[i] != i) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}

	private void union(int p, int q) {
		int prt = root(p);
		int qrt = root(q);

		if (prt == qrt)
			return;
		if (sz[prt] < sz[qrt]) {
			id[prt] = qrt;
			sz[qrt] += sz[prt];
			largest[qrt] = (largest[qrt] < largest[prt]) ? largest[prt] : largest[qrt];
		} else {
			id[qrt] = prt;
			sz[prt] += sz[qrt];
			largest[prt] = (largest[qrt] < largest[prt]) ? largest[prt] : largest[qrt];
		}
	}

	private int find(int p) {
		return largest[root(p)];
	}

	public void delete(int p) {
		deleted[p] = true;
		if (p > 0 && deleted[p - 1])
			union(p, p - 1);
		if (p < num - 1 && deleted[p + 1])
			union(p , p + 1);
	}
	
	public int successorOf(int p) {
		if (!deleted[p]) return p;
		return find(p) + 1;
	}
}
