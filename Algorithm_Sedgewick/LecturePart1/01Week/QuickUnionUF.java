import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
	private int[] id;
	
	QuickUnionUF(int N){
		id = new int[N];
		for (int i = 0; i < N; ++i) // 배열 초기화
			id[i] = i;
	}
	private int root(int i) {
		while (id[i] != i) {
			i = id[i];
		}
		return i;
	}
	void union(int p, int q) {
		id[root(p)] = root(q);
	}
	boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	// Quick-Union
	// 1. 자료구조 - 트리를 정수배열로 표현한다. p의 부모가 q라는 것은 index p의 배열 요소가 q라는 의미이다. 루트는 자기자신을 요소로 갖는다.
	// 2. 요소 하나로만 구성된 트리는 스스로만이 연결 요소이므로 자기자신을 가리킨다.
	// 3. 두 개의 요소가 같은 루트를 갖는지 체크하면, 연결돼 있는지 알 수 있다.
	// 4. root를 찾는게 번거롭지만, union 연산은 매우 쉬워진다.
	// 5. union(p, q)라면 p의 루트 id를 q의 루트 id로 설정하면 끝이다.
	
	// Quick-Union의 결점
	// 트리 구조가 지나치게 길어질 수 있다. 즉 connected 연산의 실행이 오래 걸릴 수 있다.
	// 최악의 경우 union과 connected가 N에 비례하여 시간이 걸린다.(트리가 길어져서 루트를 찾는데 오래걸림)
	
	public static void main(String[] args) {
		// 1. 객체의 개수를 입력 받는다. 여기서 UF 객체를 생성한다.
		// 2. 어떤 객체끼리 연결 돼 있는지 알기 위해 정수를 두 쌍씩 입력 받는다.
		// 3. 연결한 객체를 출력한다.
		
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " " + q);
			}
		}
	}
}
