import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
	// 1. 큰 트리와 작은 트리를 합칠 때, 큰 트리를 작은 트리 아래에 넣으면 트리의 길이가 점점 길어진다.
	// 2. 각 트리의 요소 수를 계속 기록해서 트리의 규모를 파악하고, 큰 트리 아래에 작은 트리를 넣도록 한다.

	// 추가적인 배열 sz[]가 필요하다.
	// sz[]의 각 요소 i에 대해서, i를 루트로 하여 만든 트리의 요소 수를 기록한다.
	// union 연산을 하면서 갱신된다.

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

	private int root(int i) { // 깊이에 비례하는데, 깊이의 최댓값이 lg(N)이다.
		while (id[i] != i)
			i = id[i];
		return i;
	}

	void union(int p, int q) { // lg(N)에 비례한다.
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
	
	boolean connected(int p, int q) { // lg(N)에 비례한다.
		return root(p) == root(q);
	}
	
	// 트리의 노드들이 루트와 얼마나 떨어져 있는지에 비례하여 시간이 걸린다.
	// 트리의 노드의 depth는 lg(N)보다 작다.
	// 1. 언제 노드의 깊이가 깊어지는가?
	// x는 트리 T1에 속한 노드라고 가정하자. x의 깊이가 늘어날 때는 T1이 다른 트리 T2에 합병될 때이다.
	// 이 때 작은 트리가 큰 트리에 합병되는 규칙을 따르면, T2의 트리가 더 크다.
	// 따라서 노드의 깊이가 1 늘어난다는 것은 트리의 노드 개수가 최소 2배가 됐다는 의미이다.
	// 노드 개수가 N개라는 것은, 트리가 두 배로 되는 과정을 많아야 lg(N)번 했다는 의미이다.
	public static void main(String[] args) {
		// 1. 객체의 개수를 입력 받는다. 여기서 UF 객체를 생성한다.
		// 2. 어떤 객체끼리 연결 돼 있는지 알기 위해 정수를 두 쌍씩 입력 받는다.
		// 3. 연결한 객체를 출력한다.
		
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
