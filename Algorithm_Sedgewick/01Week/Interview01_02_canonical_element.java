import edu.princeton.cs.algs4.StdOut;

public class Interview01_02_canonical_element {
	private int[] id;
	private int[] sz;
	private int[] largest;
	Interview01_02_canonical_element(int N) {
		id = new int[N];
		sz = new int[N];
		largest = new int[N]; // largest[p]에는 p를 루트로 하는 서브트리의 최댓값을 저장한다.
		for (int i = 0; i < N; ++i) {
			id[i] = i;
			sz[i] = 1;
			largest[i] = i;
		}
	}

	private int root(int i) {
		while (id[i] != i) {
			id[i] = id[id[i]];
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
			largest[qrt] = (largest[qrt] < largest[prt]) ? largest[prt] : largest[qrt];
		} else {
			id[qrt] = prt;
			sz[prt] += sz[qrt];
			largest[prt] = (largest[qrt] < largest[prt]) ? largest[prt] : largest[qrt];
		}
	}
	
	boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	int find(int p) {
		return largest[root(p)];
	}
	public static void main(String[] args) {
		int num = 10;
		int p = 0;
		Interview01_02_canonical_element obj = new Interview01_02_canonical_element(num);
		obj.union(0, 1);
		obj.union(1, 3);
		obj.union(1, 4);
		obj.union(2, 5);
		StdOut.println("largest number of connected component containg " + p + " : " + obj.find(p));
	}
}
