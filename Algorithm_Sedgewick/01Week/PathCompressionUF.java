
public class PathCompressionUF {
	// 경로 압축
	// 노드 p가 루트를 찾은 뒤, 경로 상에 있는 모든 노드를 루트로 연결시킨다.
	
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
			id[id[i]] = id[i]; // 트리를 평평하게 할 수 있다.
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
