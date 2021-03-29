import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
	private int[] id;
	
	QuickUnionUF(int N){
		id = new int[N];
		for (int i = 0; i < N; ++i) // �迭 �ʱ�ȭ
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
	// 1. �ڷᱸ�� - Ʈ���� �����迭�� ǥ���Ѵ�. p�� �θ� q��� ���� index p�� �迭 ��Ұ� q��� �ǹ��̴�. ��Ʈ�� �ڱ��ڽ��� ��ҷ� ���´�.
	// 2. ��� �ϳ��θ� ������ Ʈ���� �����θ��� ���� ����̹Ƿ� �ڱ��ڽ��� ����Ų��.
	// 3. �� ���� ��Ұ� ���� ��Ʈ�� ������ üũ�ϸ�, ����� �ִ��� �� �� �ִ�.
	// 4. root�� ã�°� ���ŷ�����, union ������ �ſ� ��������.
	// 5. union(p, q)��� p�� ��Ʈ id�� q�� ��Ʈ id�� �����ϸ� ���̴�.
	
	// Quick-Union�� ����
	// Ʈ�� ������ ����ġ�� ����� �� �ִ�. �� connected ������ ������ ���� �ɸ� �� �ִ�.
	// �־��� ��� union�� connected�� N�� ����Ͽ� �ð��� �ɸ���.(Ʈ���� ������� ��Ʈ�� ã�µ� �����ɸ�)
	
	public static void main(String[] args) {
		// 1. ��ü�� ������ �Է� �޴´�. ���⼭ UF ��ü�� �����Ѵ�.
		// 2. � ��ü���� ���� �� �ִ��� �˱� ���� ������ �� �־� �Է� �޴´�.
		// 3. ������ ��ü�� ����Ѵ�.
		
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
